package View;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import Controller.LectureListController;
import Controller.LectureListController.CellRenderer;
import Model.*;
import common.ClassManager;
import common.Constants;
import common.DesignConstants;
import org.json.simple.JSONObject;

/**
 * LectureListView Class
 * 수강신청 View를 갖는 Class 로 비즈니스 로직은 없다.
 */

public class LectureListView extends JPanel {
	LectureListController LLC;
	
	JPanel infoPanel; //제일 상단에 표시될 Panel
	JPanel searchListPanel; //필터링 한 출력 Panel
	JPanel statusPanel; //정보표현용 Panel
	JPanel myLecturePanel; //내 수강신청 과목 Panel

	//표 출력을 위한 Instance
	private JTable searchListTable; //SearchList를 담는 JTable
	private JTable myLectureTable; //내 수강신청을 담는 JTable
	private JScrollPane searchListPane; //JTable에 스크롤을 넣을 Pane
	private JScrollPane myLecturePane; //JTable에 스크롤을 넣을 Pane
	private DefaultTableModel searchListDTM; //JTable과 연결될 DTM
	private DefaultTableModel myLectureDTM; //JTable과 연결될 DTM
	
	JLabel lblScore; //현제 신청학점을 알기 위한 JLabel함수
	
	private int width = 1000; //동적 사이즈 조절을 위한 사이즈 변수
	private int height = (int)(width * 0.7);
	private int margin = (int)(height * 0.01);

	
	//LectureListController를 파라미터로 받아 서로 연결한다.
	public LectureListView() {
		LLC = new LectureListController(this);
		
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
		infoPanel.setBackground(Color.white);
		infoPanel.setLayout(null);
		
		JButton btnExit = new JButton(Constants.EXIT_TXT);
		btnExit.setBounds(0,0, (int)(width * 0.1), infoPanel.getHeight());
		btnExit.addActionListener(LLC);
		btnExit.setBackground(Color.white);
		infoPanel.add(btnExit);

		JButton btnRefresh = new JButton(Constants.REFRESH_TXT);
		btnRefresh.setBounds((int) (width * 0.9),0,(int) ( width * 0.1 ),infoPanel.getHeight());
		btnRefresh.addActionListener(LLC);
		btnRefresh.setBackground(Color.white);
		infoPanel.add(btnRefresh);
		
		JLabel title = new JLabel("수강신청");
		title.setFont(new Font(DesignConstants.HANGUL_FONT,Font.BOLD,20));
		title.setBounds(0,0,infoPanel.getWidth(),infoPanel.getHeight());
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		infoPanel.add(title);
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
		changeSearchDTM(null);
	} //setLectureListPanel()
	
	public void setStatusPanel() {
		int X = margin,Y = (int)(this.height * 0.63), height = (int)(this.height*0.05),width = this.width-margin*2;
		statusPanel = new JPanel();
		statusPanel.setBounds(X,Y,width,height);
		statusPanel.setBackground(Color.white);
		statusPanel.setLayout(null);
		add(statusPanel);
		
		lblScore = new JLabel("신청 학점 : ");
		lblScore.setBounds(10, statusPanel.getHeight() / 2, width-margin*2, (int)(statusPanel.getHeight()*0.5));
		lblScore.setFont(new Font(DesignConstants.HANGUL_FONT,Font.BOLD,14));
		lblScore.setForeground(Color.BLACK);

		statusPanel.add(lblScore);
	} //setStatusPanel()
	
	//MyLecturePanel 초기화
	public void setMyLecturePanel() {
		int X = margin,Y = (int)(this.height * 0.69), height = (int)(this.height*0.3),width = this.width-margin*2;
		myLecturePanel = new JPanel();
		myLecturePanel.setBounds(X,Y,width,height);
		myLecturePanel.setBackground(Color.CYAN);
		myLecturePanel.setLayout(null);
		changeMyLectureDTM(null);
		add(myLecturePanel);
	} //setMyLecturePanel()
	
	//JTable에 Renderer를 연결하고 사이즈를 조정하는 메소드
	public void setColumnSize(JTable table) {
		CellRenderer newCellRenderer = LLC.connectCellRenderer();
		newCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		int sizes[] = {30,50,50,20,3,100,5,2,2,100,5,5,5};
		for(int i = 0 ; i < 13; i ++)
			table.getColumn(Constants.TABLE_HEADER[i]).setPreferredWidth(sizes[i]);
		table.setDefaultRenderer(Object.class, newCellRenderer);
	}

	//JTable에 Renderer를 연결하고 사이즈를 조정하는 메소드
	public void setMyColumnSize(JTable table) {
		CellRenderer newCellRenderer = LLC.connectCellRenderer();
		newCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		int sizes[] = {30,50,50,20,3,100,5,2,2,100,5,5};
		for(int i = 0 ; i < 12; i ++)
			table.getColumn(Constants.MYTABLE_HEADER[i]).setPreferredWidth(sizes[i]);
		table.setDefaultRenderer(Object.class, newCellRenderer);
	}
	
	//DTM이 최신화 될때마다 바꿔주는 메소드
	public void changeMyLectureDTM(ArrayList<LectureDTO> lectureData) {
		if(myLecturePane != null)
			myLecturePanel.remove(myLecturePane);
		myLectureDTM = new DefaultTableModel(makeInsertData(lectureData),Constants.MYTABLE_HEADER);
		
		myLectureTable = new JTable(myLectureDTM);
		myLecturePane = new JScrollPane(myLectureTable);
		myLecturePane.setBounds(0,0,myLecturePanel.getWidth(),myLecturePanel.getHeight());
		setMyColumnSize(myLectureTable);
		
		//버튼을 연결한다.
		myLectureTable.getColumnModel().getColumn(0).setCellRenderer(new TableCell(Constants.CANCEL_TXT));
        myLectureTable.getColumnModel().getColumn(0).setCellEditor(new TableCell(Constants.CANCEL_TXT));

		myLecturePanel.add(myLecturePane);	
	} //public void changeMyLectureDTM();
	
	//DTM이 최신화 될때마다 바꿔주는 메소드
	public void changeSearchDTM(ArrayList<LectureDTO> lectureData) {
		if(searchListPane != null)
			searchListPanel.remove(searchListPane);
		searchListDTM = new DefaultTableModel(makeInsertData(lectureData),Constants.TABLE_HEADER);
		
		searchListTable = new JTable(searchListDTM);
		searchListPane = new JScrollPane(searchListTable);
		searchListPane.setBounds(0,0,searchListPanel.getWidth(),searchListPanel.getHeight());
		setColumnSize(searchListTable);
		
		//버튼을 연결한다.
		searchListTable.getColumnModel().getColumn(0).setCellRenderer(new TableCell(Constants.APPLY_TXT));
		searchListTable.getColumnModel().getColumn(0).setCellEditor(new TableCell(Constants.APPLY_TXT));
		
		searchListPanel.add(searchListPane);
	} //public void changeSearchDTM
	
	//학점을 표시하는 메소드, String으로 받는다.
	public void setScore(String data) {
		lblScore.setText("신청 학점 : " + data);
	} //public void setScore();
	
	
	//문장 2차원 배열을 돌려주는 메소드
	public String[][] makeInsertData(ArrayList<LectureDTO> lists){
		if(lists == null)
			return null;
		int sizes = lists.size();
		String output[][] = new String[sizes][13];
		for(int i = 0 ; i < lists.size();i++) {
			output[i] = lists.get(i).makeStringArray();
		}
		return output;
	} //makeInsertData(ArrayList<LectureVO> lists)

	//jsonObject로 table을 구성하게 하는 메소드
	public void setLectureList(JSONObject jsonObject){
		ArrayList<LectureDTO> lectureData = ClassManager.getInstance().getDAO().getFilterLecture(jsonObject);
		changeSearchDTM(lectureData);
		ArrayList<LectureDTO> myData = ClassManager.getInstance().getDAO().getMyLecture(ClassManager.getInstance().getMainMenuView().getUser());
		LLC.setMyLecture(myData);
		changeMyLectureDTM(myData);
		LLC.setScore();
	}

	//View를 조종할 리스너.
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{

		JButton jb;
		//flag 1은 신청 2는 취소
		public TableCell(String text) {
			jb = new JButton(text);
			jb.addActionListener(LLC);
		}

		@Override
		public Object getCellEditorValue() { return null;}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {return jb;}
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {return jb;}
	}

}
