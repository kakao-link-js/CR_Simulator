package Controller;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.*;

import Model.ClassManager;
import Model.LectureDTO;
import Model.TimeDTO;
import Model.UserDTO;
import View.LectureListView;
import common.Constants;

public class LectureListController implements ActionListener {

	LectureListView LLV;

	//생성자
	public LectureListController(LectureListView LLV) {
		this.LLV = LLV;
	}
	
	//Renderer를 연결하는 메소드
	public CellRenderer connectCellRenderer() {
		return new CellRenderer();
	} //public CellRenderer connectCellRenderer() 
	
	//검색된 값을 LectureListView에 뿌리는 메소드
	public void setSearchListAtLectureListView(ArrayList<LectureDTO> searchList) {
		LLV.changeMyLectureDTM(searchList); //JTable 값을 초기화 한다.
		LLV.changeSearchDTM();
		for(int i = 0 ; i < searchList.size(); i++) //검색 된 값을 JTable에 추가한다.
			LLV.getSearchListDTM().addRow(searchList.get(i).makeStringArray());
		SetScore();
	} //public setSearchListatlectureListView(ArrayList<LectureVO> searchList;
	
	//신청 학점을 설정하는 메소드
	public void SetScore() {
		LLV.setScore(Integer.toString(countScore()));
	} //SetScore()
	
	//수강하는 학점을 계산하는 메소드
	public int countScore() {
		int sums = 0;
		UserDTO user = ClassManager.getInstance().getMainMenuView().getUser();
		ArrayList<LectureDTO> myLecture = ClassManager.getInstance().getDAO().getMyLecture(user);
		for(int i = 0 ; i < myLecture.size(); i++)
			sums += (int)myLecture.get(i).getScore();
		return sums;
	} //public int countScore()

	//강의를 들을 수 있는 강의인지 확인하는 메소드 Object로 넣을 강의정보를 받는다.
	public boolean canInsertLecture(Object[] inserted,boolean isPopUp) {
		UserDTO user = ClassManager.getInstance().getMainMenuView().getUser();
		ArrayList<LectureDTO> myLecture = ClassManager.getInstance().getDAO().getMyLecture(user);
		return isCanInsert(myLecture,inserted,isPopUp);
	} //public boolean CanInsertLecture(Object[] inserted,boolean isPopUp)
	
	//리스트로받아 비교한다.
	public boolean isCanInsert(ArrayList<LectureDTO> myData, Object[] inserted, boolean isPopUp) {
		for(int i = 0 ; i < myData.size();i++) { //내 사이즈 만큼 비교한다.
			if(myData.get(i).getCourseNum() == inserted[3]) { //학수번호가 같다면.
				if(isPopUp) //팝업창을 띄울지 아닐지 구분
					JOptionPane.showMessageDialog(null, "이미 신청한 과목입니다.");
				return false;
			}
			if(isClassOverLap(myData.get(i).getTime(),inserted[9].toString())) { //시간을 비교한다.
				if(isPopUp)
					JOptionPane.showMessageDialog(null, "시간이 겹치는 과목이 있습니다.");
				return false;
			}
			if(countScore() + Double.parseDouble(inserted[7].toString()) > 24 && isPopUp) { //24학점 이상인지 아닌지.
				JOptionPane.showMessageDialog(null, "24학점 이상 신청할 수 없습니다..");
				return false;
			}
		}
		return true;
	} //public boolean isCanInsert(ArrayList<LectureVO> myData,Object[] inserted)
	
	
	//시간이 겹치지 않는지 확인한다.    
    public boolean isClassOverLap(String data1,String data2){
    	ArrayList<TimeDTO> times1 = getTimeList(data2); //DTO 형태로 바꾼다.
		ArrayList<TimeDTO> times2 = getTimeList(data1);
        for (int index1 = 0; index1 < times1.size(); index1++){
            TimeDTO time1 = times1.get(index1);
            for (int index2 = 0; index2 < times2.size(); index2++){
                TimeDTO time2 = times2.get(index2);
                boolean isDayOverLap = time1.Day.equals(time2.Day);
                if (isDayOverLap && isTimeOverlap(time1, time2)){
                    return true;  // 요일과 시간이 모두 겹치는 경우 true 반환
                }
            }
        }
        return false;
    } //public boolean isClassOverLap(ArrayList<TimeDTO> times1, ArrayList<TimeDTO> times2)
	
    //시간이 겹치는지 비교하는 메소드
    public boolean isTimeOverlap(TimeDTO class1, TimeDTO class2){
        boolean isStartTime = true;    
        int class1StartTime = class1.ToMinute(isStartTime);
        int class2StartTime = class2.ToMinute(isStartTime);

        // 이전 수업의 시작 시간 <= 다음 수업의 시작 시간 < 이전 수업의 종료시간
        // 위의 식을 만족할 경우 겹치는 수업임
        if(class1StartTime < class2StartTime)  { 
            // class1이 먼저 시작하는 경우
            int class1EndTime = class1.ToMinute(!isStartTime);
            return (class1StartTime <= class2StartTime) && (class2StartTime < class1EndTime);
        }
        // class2가 먼저 시작하는 경우
        int class2EndTime = class2.ToMinute(!isStartTime);
        return (class2StartTime <= class1StartTime) && (class1StartTime < class2EndTime);
    } //public boolean isTimeOverlap(TimeDTO class1, TimeDTO class2)
	
	// 요일및 시간이 적힌 문자열을 Time 객체들의 리스트로 만들어 반환
	public ArrayList<TimeDTO> getTimeList(String timeString){
		ArrayList<TimeDTO> times = new ArrayList<TimeDTO>();
		String[] splitComma = timeString.split(",");    // 콤마로 분리
		int timeIndex = 0; // 시간 정보를 넣기 시작해야하는 객체의 index
		for(String text : splitComma){
			String[] splitSpace = text.split(" "); // 스페이스로 분리해 단어 단위로 저장
			timeIndex = splitDay(times, splitSpace, timeIndex);
		}
		return times;
	} //public ArrayList<TimeDTO> getTimeList(String timeString)
	
	
    // 요일 및 시간 string에서 요일을 분리해냄
    public int splitDay(ArrayList<TimeDTO> times, String[] splitSpace, int timeIndex){
        for(String word : splitSpace){
            if (word.length() == 0)
                continue;   // 빈 문자열이면 통과
            if (Pattern.matches("^[가-힣]*$", word)){ //한글인지 아닌지.
                TimeDTO time = new TimeDTO();
                time.Day = word;
                times.add(time);    // time 객체를 리스트에 추가
            }
            else{
                timeIndex = splitTime(times, word, timeIndex);
            }
        }
        return timeIndex;
    } //public int splitDay(ArrayList<TimeDTO> times, String[] splitSpace, int timeIndex)
    
	
    // 요일 및 시간 string에서 시간을 분리해냄
    public int splitTime(ArrayList<TimeDTO> times, String word, int timeIndex){
        String[] splitTime = word.split("~");   // 시작 시간, 끝시간 분리
       
        for (int index = timeIndex; index < times.size(); index++){
            TimeDTO time = times.get(index);
            // 시작 시간 저장
            String[] startTime = splitTime[0].split(":");    // 시와 분 분리
            times.get(index).startHour = Integer.parseInt(startTime[0]);
            time.startMinute = Integer.parseInt(startTime[1]);

            // 끝 시간 저장
            String[] endTime = splitTime[1].split(":");    // 시와 분 분리
            time.endHour = Integer.parseInt(endTime[0]);
            time.endMinute = Integer.parseInt(endTime[1]);
        }
        return times.size();
    }//public int splitTime(List<TimeDTO> times, String word, int timeIndex)

	@Override
	public void actionPerformed(ActionEvent e) {
		String operator = ((JButton)e.getSource()).getText();
		switch (operator){
			case Constants.APPLY_TXT:
				if(isGoodinSearchTable()) {
					Object news [] = new Object[12];
					for(int i = 0 ; i < 12; i++)
						news[i] = LLV.getSearchListDTM().getValueAt(LLV.getSearchListTable().getSelectedRow(), i);
					if(!canInsertLecture(news,true))//신청이 불가능한 경우
						return;
					/* //신청부분
					LLV.getMyLectureDTM().addRow(news);
					//ClassManager.getInstance().getReal().add(new LectureDTO(news));
					SetScore();
					LLV.getSearchListTable().repaint();
					*/
				}
				break;
			case Constants.DELETE_TXT:
				if(isGoodinMyTable()) {
					/*
//            			for(int i = 0; i < ClassManager.getInstance().getReal().size(); i++)
//            				if(ClassManager.getInstance().getReal().get(i).courseNum  //학수번호를 들고온다.
//									== getLLV().getMyLectureDTM().getValueAt(getLLV().getMyLectureTable().getSelectedRow(),3))
//            					ClassManager.getInstance().getReal().remove(i); //배열에서 지운다.
					LLV.getMyLectureDTM().removeRow(LLV.getMyLectureTable().getSelectedRow()); //테이블에서 지운다.
					SetScore();
					LLV.getSearchListTable().repaint();
					*/
				}
				break;
			case Constants.BACK_TXT:
				ClassManager.getInstance().getMain().comeToMain();
				break;
		}
	}

	private boolean isGoodinSearchTable(){ //table 내에서 선택이 잘 되었나 확인
		if(LLV.getSearchListTable().getSelectedRow() >= 0
				&& LLV.getSearchListTable().getSelectedRow() < LLV.getSearchListTable().getRowCount())
			return true;
		return false;
	}
	private boolean isGoodinMyTable(){ //table 내에서 선택이 잘 되었나 확인.
		if(LLV.getMyLectureTable().getSelectedRow() >= 0 //잘 선택이 되었다면.
				&& LLV.getMyLectureTable().getSelectedRow() < LLV.getMyLectureTable().getRowCount())
			return true;
		return false;
	}

	//CellRenderer를 통해 빨간글씨로 바꿀 수 있게 한다.
	public class CellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            JLabel cell = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            if(table == LLV.getMyLectureTable())
            	return cell;
            if(col == 5) {
            	Object news [] = new Object[12];
            	for(int j = 0 ; j < 12; j++)
    				news[j] = LLV.getSearchListDTM().getValueAt(row, j);
        		if(canInsertLecture(news,false)) //겹치는 과목이 아니라면
        			 cell.setForeground(Color.black);
        		else
        			cell.setForeground(Color.red); // 겹치는 과목이면 Red색상으로변경한다.
            }
            else
            	cell.setForeground(Color.black);

            return cell;
        } // getTableCellRendererComponent()
    } // customCellRenderer Class
}
