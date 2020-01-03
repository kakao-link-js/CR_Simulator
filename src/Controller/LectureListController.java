package Controller;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.*;

import common.ClassManager;
import Model.LectureDTO;
import Model.TimeDTO;
import View.LectureListView;
import common.Constants;
import org.json.simple.JSONObject;

/**
 * LectureListController Class
 * 수강신청을 담당하는 클래스
 */

public class LectureListController implements ActionListener {

	LectureListView LLV;
	ArrayList<LectureDTO> myLecture ;

	//생성자
	public LectureListController(LectureListView LLV) {
		this.LLV = LLV;
	} //public LectureListController(LectureListView LLV)
	
	//Renderer를 연결하는 메소드
	public CellRenderer connectCellRenderer() {
		return new CellRenderer();
	} //public CellRenderer connectCellRenderer()
	
	//신청 학점을 설정하는 메소드
	public void setScore() {
		LLV.setScore(Integer.toString(countScore()));
	} //SetScore()
	
	//수강하는 학점을 계산하는 메소드
	private int countScore() {
		int sums = 0;
		for(int i = 0 ; i < myLecture.size(); i++)
			sums += (int)myLecture.get(i).getScore();
		return sums;
	} //public int countScore()

	//리스트로받아 비교한다.
	private boolean compareData(Object[] inserted, boolean isPopUp) {
		for(int i = 0 ; i < myLecture.size();i++) { //내 사이즈 만큼 비교한다.
			if(myLecture.get(i).getCourseNum().equals(inserted[3])) { //학수번호가 같다면.
				if(isPopUp) //팝업창을 띄울지 아닐지 구분
					JOptionPane.showMessageDialog(null, "이미 신청한 과목입니다.");
				return false;
			}
			if(myLecture.get(i).getTime() != null &&inserted[9] != null && isClassOverLap(myLecture.get(i).getTime(),inserted[9].toString())) { //시간을 비교한다.
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
    private boolean isClassOverLap(String data1,String data2){
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
    private boolean isTimeOverlap(TimeDTO class1, TimeDTO class2){
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
	private ArrayList<TimeDTO> getTimeList(String timeString){
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
    private int splitDay(ArrayList<TimeDTO> times, String[] splitSpace, int timeIndex){
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
    private int splitTime(ArrayList<TimeDTO> times, String word, int timeIndex){
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
					if(!compareData(news,true))//신청이 불가능한 경우
						return;
					//신청부분
					LLV.getMyLectureDTM().addRow(news);
					ClassManager.getInstance().getDAO().applyLecture(ClassManager.getInstance().getMainMenuView().getUser(),new LectureDTO(news));
					myLecture = ClassManager.getInstance().getDAO().getMyLecture(ClassManager.getInstance().getMainMenuView().getUser());
					LLV.changeMyLectureDTM(myLecture);
					setScore();
					LLV.getSearchListTable().repaint();
				}
				break;
			case Constants.CANCEL_TXT:
				if(isGoodinMyTable()) {
					for(int i = 0; i < myLecture.size(); i++)
						if(myLecture.get(i).getCourseNum()  //학수번호를 들고온다.
								== LLV.getMyLectureDTM().getValueAt(LLV.getMyLectureTable().getSelectedRow(),3)) {
							ClassManager.getInstance().getDAO().cancelLecture(ClassManager.getInstance().getMainMenuView().getUser(),myLecture.get(i));
							myLecture.remove(i); //배열에서 지운다.
							LLV.getMyLectureDTM().removeRow(LLV.getMyLectureTable().getSelectedRow()); //테이블에서 지운다.
						}
					setScore();
					LLV.getSearchListTable().repaint();
				}
				break;
			case Constants.EXIT_TXT:
				ClassManager.getInstance().getMain().comeToMain();
				break;
			case Constants.REFRESH_TXT:
				LLV.changeSearchDTM(ClassManager.getInstance().getDAO().getFilterLecture(new JSONObject()));
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
	public void setMyLecture(ArrayList<LectureDTO> list){
		this.myLecture = list;
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
        		if(compareData(news,false)) //겹치는 과목이 아니라면
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
