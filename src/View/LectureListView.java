package View;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import Model.*;

public class LectureListView extends JPanel {
	JPanel searchListPanel; //필터링 한 출력 Panel
	JPanel statusPanel; //정보표현용 Panel
	JPanel myLecturePanel; //내 수강신청 과목 Panel
	
	//표 출력을 위한 Instance
	JTable searchListTable;
	JTable myLectureTable;
	JScrollPane searchListPane;
	JScrollPane myLecturePane;
	
	JLabel lblScore;
	
	
	private int width = 1000; //동적 사이즈 조절을 위한 사이즈 변수
	private int height = 700;
	private int margin = 10;
	
	String header[] = {"개설대학","개설학과전공","학수번호","분반","교과목명","이수구분","학년","학점","요일 및 강의시간","강의실","교수"};
	
	public LectureListView(boolean flag) {
		initPrimaryPanel();
		
		setLectureListPanel();
		
		setMyLecturePanel();
		
		setStatusPanel();
	} //LectureListView(boolean flag)
	
	//PrimaryPanel 초기화
	public void initPrimaryPanel() {
		setPreferredSize(new Dimension(width,height)); 
		setBackground(Color.white);
		setLayout(null);
	}//initPrimaryPanel()
	
	//LectureListPanel 초기화
	public void setLectureListPanel() {
		searchListPanel = new JPanel();
		searchListPanel.setBounds(margin,margin,width-margin*2,(int) (height*0.6-margin*1.5));
		searchListPanel.setBackground(Color.WHITE);
		searchListPanel.setLayout(null);
		add(searchListPanel);
		
		searchListTable = new JTable(makeInsertData(ClassManagement.getInstance().getInterested()),header);
		searchListPane = new JScrollPane(searchListTable);
		searchListPane.setBounds(0,0,searchListPanel.getWidth(),searchListPanel.getHeight());
		searchListPanel.add(searchListPane);
	} //setLectureListPanel()
	
	//MyLecturePanel 초기화
	public void setMyLecturePanel() {
		myLecturePanel = new JPanel();
		myLecturePanel.setBounds(margin,(int) (height*0.65+margin*0.5),width-margin*2,(int) (height*0.35-margin*1.5));
		myLecturePanel.setBackground(Color.CYAN);
		myLecturePanel.setLayout(null);
		add(myLecturePanel);
		
		myLectureTable = new JTable(makeInsertData(ClassManagement.getInstance().getInterested()),header);
		myLecturePane = new JScrollPane(myLectureTable);
		myLecturePane.setBounds(0,0,myLecturePanel.getWidth(),myLecturePanel.getHeight());
		myLecturePanel.add(myLecturePane);
	} //setMyLecturePanel()
	
	public void setStatusPanel() {
		statusPanel = new JPanel();
		statusPanel.setBounds(margin,(int)(height*0.6),width-margin*2,(int)(height*0.05));
		statusPanel.setBackground(Color.white);
		statusPanel.setLayout(null);
		add(statusPanel);
		
		lblScore = new JLabel("My Score : ");
		lblScore.setBounds(10, statusPanel.getHeight() / 2, width-margin*2, (int)(statusPanel.getHeight()*0.5));
		lblScore.setFont(new Font("Verdana",Font.BOLD,14));
		lblScore.setForeground(Color.BLACK);
		
		SetScore();
		
		statusPanel.add(lblScore);
	} //setStatusPanel()
	
	public void SetScore() {
		int sums = 0;
		for(int i = 0 ; i < myLectureTable.getRowCount(); i++) {
			sums += Integer.parseInt((String) myLectureTable.getValueAt(i, 7));
		}
		lblScore.setText("My Scroe : " + sums);
	}
	
	//문장 2차원 배열을 돌려주는 메소드
	public String[][] makeInsertData(ArrayList<LectureVO> lists){
		int sizes = lists.size();
		String output[][] = new String[sizes][12];
		for(int i = 0 ; i < lists.size();i++)
			output[i] = lists.get(i).makeStringArray();
		return output;
	} //makeInsertData(ArrayList<LectureVO> lists)
	
}
