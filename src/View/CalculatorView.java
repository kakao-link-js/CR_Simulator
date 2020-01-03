package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import Controller.*;
import Model.LectureDTO;
import common.ClassManager;
import common.Constants;
import common.DesignConstants;

/**
 * CalculatorView Class
 * 학점계산기 View를 갖는 Class 비즈니스 로직은 없다.
 */

public class CalculatorView extends JPanel {
	
	private JPanel titlePanel, infoPanel, calculPanel; //타이틀, 정보, 계산 패널
	private int width = 600, height = 600, margin = (int)(height * 0.01); //기본으로 넣을 크기
	private JButton btnCalcul; //계산버튼
	private JLabel txtResult; //평점

	public ArrayList<JComboBox> comboArr = new ArrayList<JComboBox>(); //학점 콤보박스 리스트
	public ArrayList<JTextField> scoreArr = new ArrayList<JTextField>(); //몇 학점 TextField 리스트
	public ArrayList<JTextField> subjectArr = new ArrayList<JTextField>(); //과목명 TextField 리스트

	CalculatorController CalculatorController; //컨트롤러와 연결할 컨트롤러 객체변수

	//생성자
	public CalculatorView() {
		CalculatorController = new CalculatorController(this);

		setPreferredSize(new Dimension(width, height));
		setBackground(Color.white);
		setLayout(null);

		setTitlePanel();
		setInfoPanel();
		setCalculPanel();
	} //public CalculatorPanelView(CalculatorController CC)
	
	//타이틀 패널 구성 메소드
	public void setTitlePanel() {
		int x = margin, y =margin , width , height ;
		
		width = this.width - margin * 2;
		height = (int)(this.height * 0.1);
		
		titlePanel = new JPanel();
		titlePanel.setBounds(x,y,width, height);
		titlePanel.setBackground(Color.white);
		titlePanel.setLayout(null);

		int x1 = margin, y1 = margin;
		width = this.width - margin * 2;
		height = (int)(this.height * 0.1);

		JLabel SCORE = new JLabel("학점 계산기");

		SCORE.setBounds(x1, y1, width, height);
		SCORE.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 40));
		SCORE.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(SCORE);

		add(titlePanel);
		setBtnExit();
	} //public void setTitlePanel()
	
	//정보 패널 구성 메소드
	public void setInfoPanel() {
		int x = margin, y = (int)(this.height * 0.17), width = this.width - margin * 2, height = (int)(this.height * 0.7);
		
		infoPanel = new JPanel();
		infoPanel.setBounds(x, y, width, height);
		infoPanel.setBackground(Color.white);
		infoPanel.setLayout(null);
		add(infoPanel);

		ArrayList<LectureDTO> list = ClassManager.getInstance().getDAO().getMyLecture(ClassManager.getInstance().getMainMenuView().getUser());

		setSubTitle();
		setGradeComboBox();
		setTextScore(list);
		setTextSubject(list);
	} //public void setInfoPanel()
	
	//계산 패널 구성 메소드
	public void setCalculPanel() {
		int x = margin;
		int y = (int)(this.height * 0.83);
		int width = this.width - margin * 2;
		int height = (int)(this.height *0.16);
		
		calculPanel = new JPanel();
		calculPanel.setBounds(x, y, width, height);
		calculPanel.setBackground(Color.white);
		calculPanel.setLayout(null);

		add(calculPanel);
		setButton();
		setTextResult();
	}//public void setCalculPanel()

	//과목명 학점 성적 타이틀을 구성하는 메소드
	public void setSubTitle() {
		int x = margin;
		int y = (int)(height * 0.02);
		int width = (int)((this.width - margin * 2) * 0.45);
		int height = (int)(this.height * 0.6* 0.05);

		JLabel title1 = new JLabel(Constants.CLASSNAMEKOR_TXT);
		JLabel title2 = new JLabel(Constants.SCOREKOR_TXT);
		JLabel title3 = new JLabel(Constants.MYSCOREKOR_TXT);
		
		title1.setBounds(x, y, width, height);
		title2.setBounds(margin+width, y, width / 2, height);
		title3.setBounds(margin+width + 150, y, width / 2, height);
		title1.setFont(new Font(DesignConstants.HANGUL_FONT, Font.PLAIN, 15));
		title2.setFont(new Font(DesignConstants.HANGUL_FONT, Font.PLAIN, 15));
		title3.setFont(new Font(DesignConstants.HANGUL_FONT, Font.PLAIN, 15));
		title1.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title3.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(title1);
		infoPanel.add(title2);
		infoPanel.add(title3);
	}	//public void setSubTitle()
	
	//성적 콤보박스 구성 메소드
	public void setGradeComboBox() {
		int x = (int)(this.width * 0.73);
		int width = (int)(this.width * 0.2);

		for(int i = 1 ; i <= 8; i++) {
			JComboBox box = new JComboBox(Constants.SCORES);
			box.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox jcb = (JComboBox)e.getSource();
				}
			});
			box.setFont(new Font(DesignConstants.HANGUL_FONT, Font.PLAIN, 10));
			box.setBounds(x, margin * (i*7), width, 30);
			box.setVisible(true);
			infoPanel.add(box);
			comboArr.add(box);
		}	
	} //public void setGradeComboBox()
	
	//학점 Label구성 메소드
	public void setTextScore(ArrayList<LectureDTO> list) {
		int x = (int)(this.width * 0.45);
		int width = (int)(this.width * 0.2); 
			
		for (int i = 1; i <= 8; i++) {
			JTextField txtScore = new JTextField();
			txtScore.setFont(new Font(DesignConstants.HANGUL_FONT, Font.PLAIN, 15));
			txtScore.setBounds(x, margin * (i * 7), width, 30);

			if(list.size() > i-1)
				txtScore.setText(String.valueOf(list.get(i-1).getScore()));
			
			infoPanel.add(txtScore);
			scoreArr.add(txtScore);
		}
	} //public void setTextScore()
	
	//제목 구성 메소드
	public void setTextSubject(ArrayList<LectureDTO> list) {
		int x = (int)(this.height * 0.05);
		int width = (int)(this.width * 0.3); 
			
		for (int i = 1; i <= 8; i++) {
			JTextField txtSubject = new JTextField();
			txtSubject.setFont(new Font(DesignConstants.HANGUL_FONT, Font.PLAIN, 15));
			txtSubject.setBounds(x, margin * (i * 7), width, 30);
			
			if(list.size() > i-1)
				txtSubject.setText(list.get(i-1).getClassName());
				
			infoPanel.add(txtSubject);
			subjectArr.add(txtSubject);
		}
	} //public void setTextSubject()
	
	//버튼을 구성하는 메소드
	public void setButton() {
		int x = margin * 5;
		int y = (int)(this.height * 0.04);
		int width = (int)(this.width * 0.4);
		int height = (int)(this.height * 0.08);
		
		btnCalcul = new JButton("계산하기!");
		btnCalcul.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
		btnCalcul.setBounds(x, y, width, height);
		btnCalcul.addActionListener(CalculatorController);
		btnCalcul.setBackground(Color.white);
		calculPanel.add(btnCalcul);
	} //public void setButton() 
	
	//평점을 출력하는 메소드
	public void setTextResult() {
		int x = (int)(this.width / 2);
		int y = (int)(this.height * 0.04);
		int width = (int)(this.width * 0.4);
		int height = (int)(this.height * 0.08);
		
		txtResult = new JLabel();
		txtResult.setBounds(x, y, width, height);
		txtResult.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 30));
		calculPanel.add(txtResult);
	} //public void setTextResult()
	
	//나가기버튼 구성 메소드입니다.
	public void setBtnExit() {
	      int x = margin, y = margin, width = (int)(this.height * 0.2), height = (int) (this.height*0.1 - 10);

		  JButton btnExit = new JButton(Constants.EXIT_TXT);
	      btnExit.setBounds(x, y, width, height);
	      btnExit.setFont(new Font(DesignConstants.HANGUL_FONT,Font.BOLD,15));
	      btnExit.addActionListener(CalculatorController);
	      btnExit.setBackground(Color.white);
	      titlePanel.add(btnExit);
	} //public void setBtnExit()

	//2개있는 Btn을 구분하는 메소드입니다.
	public boolean isBtnCalcul(Object temp){
		if(temp == btnCalcul)
			return true;
		else
			return false;
	} //public boolean isBtnCalcul(Object temp)

	//평점에 데이터를 넣을 수 있는 메소드입니다.
	public void setScore(String data){
		txtResult.setText(data);
	} //public void setScore(String data)

	public boolean isInserted(int index){
		if(comboArr.get(index).getSelectedItem() != "NONE"
				&& scoreArr.get(index).getText() != null
				&& subjectArr.get(index).getText()!= null)
			return true;
		return false;
	}
}
