package View;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import org.apache.commons.math3.analysis.function.Add;

import Controller.LectureListController;
import Controller.LectureListController.CellRenderer;
import Model.*;
import common.DesignConstants;

public class LoginView extends JPanel{
 //	LoginViewController LVC;
	
	JPanel imagePanel; //상단에 삽입할 이미지 패널
	JPanel loginPanel; //하단에 삽입할 로그인 패널

	//로그인 창을 위한 Instance
	private int width, height, margin; //기본으로 넣을 크기
	private JButton loginBtn; //로그인 버튼
	private JButton searchIdBtn; //id찾기 버튼
	private JButton signUpBtn; //회원가입 버튼
	private JTextField idTextField, pwTextField;

	//생성자
	public LoginView() {

		width = 500;
		height = 180;
		margin = (int)(width * 0.01);


		setPreferredSize(new Dimension(width, height));
		setBackground(Color.white);
		setLayout(null);
		
		setImagePanel();
		
		setComponent();
		
	}
	
	private void setImagePanel() {
		int x = (int) (width*0.1),y =(int) (height*0.2) , width , height ;
		width = (int) (this.width * 0.12);
		height = (int)(this.height * 0.2);
		
		
		imagePanel = new JPanel();
		imagePanel.setBounds(x, y, width, height);
		
		
		
	}



	//component을 구성하는 메소드
	private void setComponent() {
		int x = (int) (width*0.1),y =(int) (height*0.2) , width , height ;

		width = (int) (this.width * 0.12);
		height = (int)(this.height * 0.2);
		
		ImageIcon i = new ImageIcon("Images/sejong.png");
		

		//라벨
		JLabel ID = new JLabel("ID");
		JLabel PW = new JLabel("PW");

		ID.setBounds(x, y, width, height);
		ID.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD + Font.ITALIC, 20));
		ID.setHorizontalAlignment(SwingConstants.CENTER);
		add(ID);
		ID.setLayout(null);

		PW.setBounds(x, ID.getY()+height, width, height);
		PW.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD + Font.ITALIC, 20));
		PW.setHorizontalAlignment(SwingConstants.CENTER);
		add(PW);
		PW.setLayout(null);

		//텍스트필드
		x = x+ID.getWidth()+margin*2;
		y = ID.getY();
		width = (int)(this.width*0.39);
		height = ID.getHeight();
		
		idTextField = new JTextField();
		pwTextField = new JTextField();

		idTextField.setBounds(x, y, width, height);
		pwTextField.setBounds(x, y+idTextField.getHeight()+margin, width, height);
		add(idTextField);
		add(pwTextField);
		setVisible(true);

		//버튼
		loginBtn = new JButton("로그인");
		searchIdBtn = new JButton("ID 찾기");
		signUpBtn = new JButton("회원 가입");

		loginBtn.setBounds(idTextField.getX()+idTextField.getWidth()+margin*2, idTextField.getY(),
				(int)(this.width*0.19), idTextField.getHeight()+pwTextField.getHeight()+margin);
		searchIdBtn.setBounds(idTextField.getX(), idTextField.getY()+idTextField.getHeight()+pwTextField.getHeight()+margin*2,
				(int)(idTextField.getWidth()*0.5-margin), (int)(this.height*0.16));
		signUpBtn.setBounds(idTextField.getX()+searchIdBtn.getWidth()+margin*2, idTextField.getY()+idTextField.getHeight()+pwTextField.getHeight()+margin*2,
				(int)(idTextField.getWidth()*0.5-margin), (int)(this.height*0.16));
		add(loginBtn);
		add(searchIdBtn);
		add(signUpBtn);


	}


}
