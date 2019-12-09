package Controller;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.table.*;

import Model.ClassManager;
import Model.LectureVO;
import Model.TimeDTO;
import View.LectureListView;

public class LectureListController {
	
	public LectureListController() {
	}
	
	//LectureListView를 반환하는 함수.
	public LectureListView getLLV() {
		return ClassManager.getInstance().getLectureListView();
	} //public LectureListView getLLV();
	
	public TableCell connectTableCell(String text,int flag) {
		return new TableCell(text,flag);
	} // public TabelCell connectTableCell(String text,int flag);
	
	//검색된 값을 LectureListView에 뿌리는 메소드
	public void setSearchListAtLectureListView(ArrayList<LectureVO> searchList) {
		getLLV().changeMyLectureDTM();
		getLLV().changeSearchDTM();
		for(int i = 0 ; i < searchList.size(); i++)
			getLLV().getSearchListDTM().addRow(searchList.get(i).makeStringArray());
		SetScore();
	} //public setSearchListatlectureListView(ArrayList<LectureVO> searchList;
	
	//신청 학점을 설정하는 메소드
	public void SetScore() {
		int sums = 0;
		if(getLLV().isFavorite)
			for(int i = 0 ; i < ClassManager.getInstance().getInterested().size(); i++)
				sums += (int)ClassManager.getInstance().getInterested().get(i).score;
		else
			for(int i = 0 ; i < ClassManager.getInstance().getReal().size(); i++)
				sums += (int)ClassManager.getInstance().getReal().get(i).score;
		getLLV().setScore(Integer.toString(sums));
	} //SetScore()
	
	
	//강의를 들을 수 있는 강의인지 확인하는 메소드 Object로 넣을 강의정보를 받는다. flag = false 은 진짜 수강신청  flag = true 는 관심과목 신청
	public boolean canInsertLecture(Object[] inserted,boolean flag) {
		ArrayList<LectureVO> myData;
		if(flag) {
			myData = ClassManager.getInstance().getInterested();
			return isCanInsert(myData,inserted);
		}
		else {
			myData = ClassManager.getInstance().getReal();
			return isCanInsert(myData,inserted);
		}
	} //public boolean CanInsertLecture(Object[] inserted)
	
	//리스트로받아 비교한다.
	public boolean isCanInsert(ArrayList<LectureVO> myData,Object[] inserted) {
		System.out.println(inserted[5]+ " : My Data size : "+myData.size());
		for(int i = 0 ; i < myData.size();i++) {
			if(myData.get(i).courseNum == inserted[3]) { //학수번호가 같다면.
				System.out.println(inserted[5]+ " already inserted CourseNum");
				JOptionPane.showMessageDialog(null, "이미 신청한 과목입니다.");
				return false;
			}
			if(isClassOverLap(myData.get(i).time,inserted[9].toString())) {
				System.out.println(inserted[5]+ " already inserted time");
				JOptionPane.showMessageDialog(null, "시간이 겹치는 과목이 있습니다.");
				return false;
			}
		}
		return true;
	} //public boolean isCanInsert(ArrayList<LectureVO> myData,Object[] inserted)
	
	
	//시간이 겹치지 않는지 확인한다.    
    public boolean isClassOverLap(String data1,String data2){
    	ArrayList<TimeDTO> times1 = getTimeList(data2);
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

    public ActionListener getActionListener() {
    	ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClassManager.getInstance().getMainMenuController().comeToMain();
			}
    	};
    	return listener;
    } //public ActionListener getActionListener();
	
	
	//View를 조종할 리스너.
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{
		 
        JButton jb;
         
        //flag 1은 신청 2는 취소
        public TableCell(String text,int flag) {
            jb = new JButton(text);
            jb.addActionListener(e -> {
            	if(flag == 2) {
            		System.out.println("취소 " + getLLV().getMyLectureTable().getSelectedRow() + "  " + getLLV().getMyLectureTable().getRowCount());
            		if(getLLV().getMyLectureTable().getSelectedRow() >= 0 //잘 선택이 되었다면.
            				&& getLLV().getMyLectureTable().getSelectedRow() < getLLV().getMyLectureTable().getRowCount()) {
            			if(getLLV().isFavorite) { //관심과목인 경우
            				for(int i = 0; i < ClassManager.getInstance().getInterested().size(); i++)
            					if(ClassManager.getInstance().getInterested().get(i).courseNum  //학수번호를 들고온다.
            							== getLLV().getMyLectureDTM().getValueAt(getLLV().getMyLectureTable().getSelectedRow(),3))
            						ClassManager.getInstance().getInterested().remove(i); //배열에서 지운다.
            			}
            			else { //관심과목이 아닌경우
            				for(int i = 0; i < ClassManager.getInstance().getReal().size(); i++)
            					if(ClassManager.getInstance().getReal().get(i).courseNum  //학수번호를 들고온다.
            							== getLLV().getMyLectureDTM().getValueAt(getLLV().getMyLectureTable().getSelectedRow(),3))
            						ClassManager.getInstance().getReal().remove(i); //배열에서 지운다.
            			}
            			getLLV().getMyLectureDTM().removeRow(getLLV().getMyLectureTable().getSelectedRow()); //테이블에서 지운다.
        				SetScore();
            		}
            	}
            	else { //신청인 경우
            		System.out.println("신청 " + getLLV().getSearchListTable().getSelectedRow() + "  " + getLLV().getSearchListTable().getRowCount());
            		if(getLLV().getSearchListTable().getSelectedRow() >= 0 
            				&& getLLV().getSearchListTable().getSelectedRow() < getLLV().getSearchListTable().getRowCount()) {
            			Object news [] = new Object[12];
            			for(int i = 0 ; i < 12; i++)
            				news[i] = getLLV().getSearchListDTM().getValueAt(getLLV().getSearchListTable().getSelectedRow(), i);
            			if(!canInsertLecture(news,getLLV().isFavorite))//신청이 불가능한 경우
            				return;
            			System.out.println("신청완료. " + news[5]);
            			getLLV().getMyLectureDTM().addRow(news);
            			if(getLLV().isFavorite)  //관심과목인 경우
            				ClassManager.getInstance().getInterested().add(new LectureVO(news));
            			else
            				ClassManager.getInstance().getReal().add(new LectureVO(news));
            			SetScore();
            		}
            	}
            	
            });
        }
         
        @Override
        public Object getCellEditorValue() { return null;}
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {return jb;}
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {return jb;}  
    }
}
