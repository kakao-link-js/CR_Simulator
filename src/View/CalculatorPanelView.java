package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import Controller.*;
import Model.ClassManager;

public class CalculatorPanelView extends JPanel {
	
	private JPanel titlePanel, infoPanel, calculPanel;
	private JLabel SCORE, CALCULATOR;
	private JLabel	title1, title2, title3;
	private int width, height, margin;
	private JButton btnCalcul, btnExit;
	private JLabel txtResult;
	private Image image;

	public ArrayList<JComboBox> comboArr = new ArrayList<JComboBox>();
	public ArrayList<JTextField> scoreArr = new ArrayList<JTextField>();
	public ArrayList<JTextField> subjectArr = new ArrayList<JTextField>();

	CalculatorController m_CC;

	public CalculatorPanelView(CalculatorController CC) {
		width = 600;
		height = 850;
		margin = (int)(height * 0.01);
		m_CC = CC;

		setPreferredSize(new Dimension(width, height));
		setBackground(Color.white);
		setLayout(null);

		setTitlePanel();
		setInfoPanel();
		setCalculPanel();
	}
	
	public void setTitlePanel() {
		int x = margin,y =margin , width , height ;
		
		width = this.width - margin * 2;
		height = (int)(this.height * 0.2);
		
		titlePanel = new JPanel();
		titlePanel.setBounds(x,y,width, height);
		titlePanel.setBackground(Color.white);
		titlePanel.setLayout(null);
		add(titlePanel);
		setLabelTitle();
		setBtnExit();
	}
	
	public void setInfoPanel() {
		int x = margin;
		int y = (int)(this.height * 0.22);
		int width = this.width - margin * 2;
		int height = (int)(this.height * 0.6);
		
		infoPanel = new JPanel();
		infoPanel.setBounds(x, y, width, height);
		infoPanel.setBackground(Color.white);
		infoPanel.setLayout(null);
		add(infoPanel);
		setSubTitle();
		
		
		setGradeComboBox();
		setTextScore();
		setTextSubject();
	}
	
	public void setCalculPanel() {
		int x = margin;
		int y = (int)(this.height * 0.83);
		int width = this.width - margin * 2;
		int height = (int)(this.height *0.16);
		
		calculPanel = new JPanel();
		calculPanel.setBounds(x, y, width, height);
		calculPanel.setBackground(Color.white);
		calculPanel.setLayout(null);
		//calculPanel.setVerticalAlignment(SwingConstants.CENTER);
		add(calculPanel);
		setButton();
		setTextResult();
	}
	
	public void setLabelTitle() {
		int x1 = margin;
		int y1 = margin;
		int width = this.width - margin * 2;
		int height = (int)(this.height * 0.1);
		int x2 = margin;
		int y2 = height;
		
		SCORE = new JLabel("SCORE");
		
		SCORE.setBounds(x1, y1, width, height);
		SCORE.setFont(new Font("Verdana", Font.BOLD + Font.ITALIC, 40));
		SCORE.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(SCORE);
		
		CALCULATOR = new JLabel("CALCULATOR");
		
		CALCULATOR.setBounds(x2, y2, width, height);
		CALCULATOR.setFont(new Font("Verdana", Font.BOLD + Font.ITALIC, 40));
		CALCULATOR.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(CALCULATOR);
	}

	public void setSubTitle() {
		int x = margin;
		int y = (int)(height * 0.02);
		int width = (int)((this.width - margin * 2) * 0.45);
		int height = (int)(this.height * 0.6* 0.05);
		
		title1 = new JLabel("Course Name");
		title2 = new JLabel("credit");
		title3 = new JLabel("grade Point");
		
		title1.setBounds(x, y, width, height);
		title2.setBounds(margin+width, y, width / 2, height);
		title3.setBounds(margin+width + 150, y, width / 2, height);
		title1.setFont(new Font("Verdana", Font.PLAIN, 20));
		title2.setFont(new Font("Verdana", Font.PLAIN, 20));
		title3.setFont(new Font("Verdana", Font.PLAIN, 20));
		title1.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title3.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.add(title1);
		infoPanel.add(title2);
		infoPanel.add(title3);
	}	
	
	public void setScoreComboBox() {
		int x = (int)(this.width * 0.6);
		int width = (int)(this.width * 0.2);
		String Score[] = {"1", "2", "3", "4", "5", "6"};
		JComboBox box = new JComboBox(Score);
		box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox)e.getSource();		
			}
		});
		box.setBounds(x, margin, width, 300);
		box.setSize(100,100);
		box.setVisible(true);
		infoPanel.add(box);
	}
	
	public void setGradeComboBox() {
		int x = (int)(this.width * 0.73);
		int width = (int)(this.width * 0.2);
		String Grade[] = {"None", "A+", "A0", "B+", "B0", "C+", "C0", "D+", "D0", "F"};

		for(int i = 1 ; i <= 8; i++) {
			JComboBox box = new JComboBox(Grade);
			box.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox jcb = (JComboBox)e.getSource();		
				}
			});
			box.setFont(new Font("Verdana", Font.PLAIN, 15));
			box.setBounds(x, margin * (i*7), width, 15);
			box.setSize(110,50);
			box.setVisible(true);
			infoPanel.add(box);
			comboArr.add(box);
		}	
	}
	
	public void setTextScore() {
		int x = (int)(this.width * 0.45);
		int width = (int)(this.width * 0.2); 
			
		for (int i = 1; i <= 8; i++) {
			JTextField txtScore = new JTextField();
			txtScore.setFont(new Font("Verdana", Font.PLAIN, 15));
			txtScore.setBounds(x, margin * (i * 7), width, 50);
			System.out.println(ClassManager.getInstance().getReal().size());
			if(ClassManager.getInstance().getReal().size() > i-1) {
				System.out.println(String.valueOf(ClassManager.getInstance().getReal().get(i-1).score));
				txtScore.setText(String.valueOf(ClassManager.getInstance().getReal().get(i-1).score));
			}
			
			infoPanel.add(txtScore);
			scoreArr.add(txtScore);
		}
	}
	
	public void setTextSubject() {
		
		int x = (int)(this.height * 0.05);
		int width = (int)(this.width * 0.3); 
			
		for (int i = 1; i <= 8; i++) {
			JTextField txtSubject = new JTextField();
			txtSubject.setFont(new Font("Verdana", Font.PLAIN, 15));
			txtSubject.setBounds(x, margin * (i * 7), width, 50);
			
			if(ClassManager.getInstance().getReal().size() > i-1)
				txtSubject.setText(ClassManager.getInstance().getReal().get(i-1).className);
				
			infoPanel.add(txtSubject);
		
			subjectArr.add(txtSubject);
		}
		
	}
	
	
	public void setButton() {
		int x = margin * 5;
		int y = (int)(this.height * 0.04);
		int width = (int)(this.width * 0.4);
		int height = (int)(this.height * 0.08);
		
		btnCalcul = new JButton("Calculate!");
		btnCalcul.setFont(new Font("Verdana", Font.BOLD, 20));
		btnCalcul.setBounds(x, y, width, height);
		btnCalcul.addActionListener(m_CC.connectActionListener());
		calculPanel.add(btnCalcul);
		
	}
	
	public void setTextResult() {
		int x = (int)(this.width / 2);
		int y = (int)(this.height * 0.04);
		int width = (int)(this.width * 0.4);
		int height = (int)(this.height * 0.08);
		
		txtResult = new JLabel();
		txtResult.setBounds(x, y, width, height);
		txtResult.setFont(new Font("Verdana", Font.BOLD, 30));
		calculPanel.add(txtResult);
	}
	
	public void setBtnExit() {
	      int x = margin;
	      int y = margin;
	      int width = (int)(this.height * 0.2)/2;
	      int height = width;
	      
	      btnExit = new JButton("exit");
	      btnExit.setBounds(x, y, width, height);
	      btnExit.addActionListener(m_CC.connectActionListener());
	      titlePanel.add(btnExit);
	}

	public boolean isBtnCalcul(Object temp){
		if(temp == btnCalcul)
			return true;
		else
			return false;
	}

	public void setScore(String data){
		txtResult.setText(data);
	}
}
