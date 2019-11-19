package View;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import Controller.LectureListController;
import Model.*;

public class LectureListView extends JPanel {
	LectureListController LLC;
	
	JPanel infoPanel; //제일 상단에 표시될 Panel
	JPanel searchListPanel; //필터링 한 출력 Panel
	JPanel statusPanel; //정보표현용 Panel
	JPanel myLecturePanel; //내 수강신청 과목 Panel
	
	
	//표 출력을 위한 Instance
	private JTable searchListTable;
	private JTable myLectureTable;
	private JScrollPane searchListPane;
	private JScrollPane myLecturePane;
	private DefaultTableModel searchListDTM;
	private DefaultTableModel myLectureDTM;
	
	JLabel lblScore;
	
	
	private int width = 1000; //동적 사이즈 조절을 위한 사이즈 변수
	private int height = 700;
	private int margin = (int)(height * 0.01);
	
	String header[] = {"신청/취소","개설대학","개설학과전공","학수번호","분반","교과목명","이수구분","학년","학점","요일 및 강의시간","강의실","교수"};
	
	public LectureListView(boolean flag,LectureListController LLC) {
		this.LLC = LLC;
		
		initPrimaryPanel();
		
		setInfoPanel();
		
		setLectureListPanel();
		
		setMyLecturePanel();
		
		setStatusPanel();
	} //LectureListView(boolean flag, LectureListController LLC)
	
	public JTable getMyLectureTable() {return myLectureTable;}
	public JTable getSearchListTable() {return searchListTable;}
	public DefaultTableModel getMyLectureDTM() {return myLectureDTM;}
	public DefaultTableModel getSearchListDTM() {return searchListDTM;}
	
	
	//PrimaryPanel 초기화
	public void initPrimaryPanel() {
		setPreferredSize(new Dimension(width,height)); 
		setBackground(Color.white);
		setLayout(null);
	}//initPrimaryPanel()
	
	public void setInfoPanel() {
		int X = margin,Y = margin,height = (int)(this.height*0.05),width = this.width-margin*2;
		infoPanel = new JPanel();
		infoPanel.setBounds(X,Y,width,height);
		infoPanel.setBackground(Color.BLUE);
		infoPanel.setLayout(null);
		add(infoPanel);
		
	} //public void setInfoPanel()
	
	//LectureListPanel 초기화
	public void setLectureListPanel() {
		int X = margin,Y = margin*2 + infoPanel.getHeight(),height = (int)(this.height*0.55),width = this.width-margin*2;
		searchListPanel = new JPanel();
		searchListPanel.setBounds(X,Y,width,height);
		searchListPanel.setBackground(Color.WHITE);
		searchListPanel.setLayout(null);
		add(searchListPanel);
		
		searchListDTM = new DefaultTableModel(makeInsertData(ClassManagement.getInstance().getLecture()),header);
		
		searchListTable = new JTable(searchListDTM);
		searchListPane = new JScrollPane(searchListTable);
		searchListPane.setBounds(0,0,searchListPanel.getWidth(),searchListPanel.getHeight());
		setColumnSize(searchListTable);
		
		//버튼을 연결한다.
		searchListTable.getColumnModel().getColumn(0).setCellRenderer(LLC.connectTableCell("신청",1));
		searchListTable.getColumnModel().getColumn(0).setCellEditor(LLC.connectTableCell("신청",1));
		
		searchListPanel.add(searchListPane);
	} //setLectureListPanel()
	
	public void setStatusPanel() {
		int X = margin,Y = (int)(height * 0.63), height = (int)(this.height*0.05),width = this.width-margin*2;
		statusPanel = new JPanel();
		statusPanel.setBounds(X,Y,width,height);
		statusPanel.setBackground(Color.white);
		statusPanel.setLayout(null);
		add(statusPanel);
		
		lblScore = new JLabel("My Score : ");
		lblScore.setBounds(10, statusPanel.getHeight() / 2, width-margin*2, (int)(statusPanel.getHeight()*0.5));
		lblScore.setFont(new Font("Verdana",Font.BOLD,14));
		lblScore.setForeground(Color.BLACK);

		statusPanel.add(lblScore);
	} //setStatusPanel()
	
	//MyLecturePanel 초기화
	public void setMyLecturePanel() {
		int X = margin,Y = (int)(height * 0.69), height = (int)(this.height*0.3),width = this.width-margin*2;
		myLecturePanel = new JPanel();
		myLecturePanel.setBounds(X,Y,width,height);
		myLecturePanel.setBackground(Color.CYAN);
		myLecturePanel.setLayout(null);
		add(myLecturePanel);
		
		myLectureDTM = new DefaultTableModel(makeInsertData(ClassManagement.getInstance().getInterested()),header);
		
		myLectureTable = new JTable(myLectureDTM);
		myLecturePane = new JScrollPane(myLectureTable);
		myLecturePane.setBounds(0,0,myLecturePanel.getWidth(),myLecturePanel.getHeight());
		setColumnSize(myLectureTable);
		
		//버튼을 연결한다.
		myLectureTable.getColumnModel().getColumn(0).setCellRenderer(LLC.connectTableCell("취소",2));
        myLectureTable.getColumnModel().getColumn(0).setCellEditor(LLC.connectTableCell("취소",2));

		myLecturePanel.add(myLecturePane);
	} //setMyLecturePanel()
	
	public void setColumnSize(JTable table) {
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		int sizes[] = {30,50,50,50,3,100,5,5,5,100,50,50};
		for(int i = 0 ; i < 12; i ++) {
			table.getColumn(header[i]).setPreferredWidth(sizes[i]);
			table.getColumn(header[i]).setCellRenderer(celAlignCenter);
		}
	}
	
	public void setScore(String data) {
		lblScore.setText("My Score : " + data);
	} //public void setScore();
	
	
	//문장 2차원 배열을 돌려주는 메소드
	public String[][] makeInsertData(ArrayList<LectureVO> lists){
		int sizes = lists.size();
		String output[][] = new String[sizes][12];
		for(int i = 0 ; i < lists.size();i++) {
			output[i] = lists.get(i).makeStringArray();
		}
		return output;
	} //makeInsertData(ArrayList<LectureVO> lists)
}
