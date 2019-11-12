package View;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import Model.*;

public class LectureListView extends JPanel {
	JPanel searchListPanel; //필터링 한 출력 Panel
	JPanel myLecturePanel; //내 수강신청 과목 Panel
	JTable searchListTable;
	JTable myLectureTable;
	JScrollPane searchListPane;
	JScrollPane myLecturePane;
	
	private int width = 1500; //동적 사이즈 조절을 위한 사이즈 변수
	private int height = 1000;
	private int margin = 10;
	
	String header[] = {"개설대학","개설학과전공","학수번호","분반","교과목명","이수구분","학년","학점","요일 및 강의시간","강의실","교수"};
	
	ArrayList<LectureVO> testData = new ArrayList<LectureVO>(); //test데이터 저장용.
	
	public LectureListView(boolean flag) {
		initPrimaryPanel();
		
		setLectureListPanel();
		
		setMyLecturePanel();
	}
	
	//테스트 데이터 생성용
	public void addTestData() {
		LectureVO new1 = new LectureVO("0","0","0","0","0","0","0","0","0","0","0");
		testData.add(new1);
		LectureVO new2 = new LectureVO("0","0","0","0","0","0","0","0","0","0","0");
		testData.add(new2);
		LectureVO new3 = new LectureVO("0","0","0","0","0","0","0","0","0","0","0");
		testData.add(new3);
		LectureVO new4 = new LectureVO("0","0","0","0","0","0","0","0","0","0","0");
		testData.add(new4);
		LectureVO new5 = new LectureVO("0","0","0","0","0","0","0","0","0","0","0");
		testData.add(new5);
		LectureVO new6 = new LectureVO("0","0","0","0","0","0","0","0","0","0","0");
		testData.add(new6);
		LectureVO new7 = new LectureVO("0","0","0","0","0","0","0","0","0","0","0");
		testData.add(new7);
		LectureVO new8 = new LectureVO("0","0","0","0","0","0","0","0","0","0","0");
		testData.add(new8);
		LectureVO new9 = new LectureVO("0","0","0","0","0","0","0","0","0","0","0");
		testData.add(new9);
		LectureVO new10 = new LectureVO("0","0","0","0","0","0","0","0","0","0","0");
		testData.add(new10);
	}
	
	//PrimaryPanel 초기화
	public void initPrimaryPanel() {
		setPreferredSize(new Dimension(width,height)); 
		setBackground(Color.white);
		setLayout(null);
	}
	
	//LectureListPanel 초기화
	public void setLectureListPanel() {
		searchListPanel = new JPanel();
		searchListPanel.setBounds(margin,margin,width-margin*2,(int) (height*0.7-margin*1.5));
		searchListPanel.setBackground(Color.WHITE);
		searchListPanel.setLayout(null);
		add(searchListPanel);
		
		addTestData(); //테스트용
		
		searchListTable = new JTable(makeInsertData(testData),header);
		searchListPane = new JScrollPane(searchListTable);
		searchListPane.setBounds(0,0,searchListPanel.getWidth(),searchListPanel.getHeight());
		searchListPanel.add(searchListPane);
	}
	
	//MyLecturePanel 초기화
	public void setMyLecturePanel() {
		myLecturePanel = new JPanel();
		myLecturePanel.setBounds(margin,(int) (height*0.7+margin*0.5),width-margin*2,(int) (height*0.3-margin*1.5));
		myLecturePanel.setBackground(Color.CYAN);
		myLecturePanel.setLayout(null);
		add(myLecturePanel);
		
		addTestData(); //테스트용
		
		myLectureTable = new JTable(makeInsertData(testData),header);
		myLecturePane = new JScrollPane(myLectureTable);
		myLecturePane.setBounds(0,0,myLecturePanel.getWidth(),myLecturePanel.getHeight());
		myLecturePanel.add(myLecturePane);
	}
	
	//문장 2차원 배열을 돌려주는 메소드
	public String[][] makeInsertData(ArrayList<LectureVO> lists){
		int sizes = lists.size();
		String output[][] = new String[sizes][12];
		for(int i = 0 ; i < lists.size();i++)
			output[i] = lists.get(i).makeStringArray();
		return output;
	}
	
}
