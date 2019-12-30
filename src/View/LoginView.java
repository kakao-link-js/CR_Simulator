package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

//import org.apache.commons.math3.analysis.function.Add;

import Controller.LectureListController;
import Controller.LectureListController.CellRenderer;
import Controller.LoginController;
import Model.*;
import common.Constants;
import common.DesignConstants;

public class LoginView extends JPanel{
	JPanel imagePanel; //상단에 삽입할 이미지 패널
	JPanel loginPanel; //하단에 삽입할 로그인 패널

	//로그인 창을 위한 Instance
	private int width, height, margin; //기본으로 넣을 크기
	private JButton loginBtn; //로그인 버튼
	private JButton searchIdBtn; //id찾기 버튼
	private JButton signUpBtn; //회원가입 버튼
	private JTextField idTextField, pwTextField;

	LoginController loginController;

	//생성자
	public LoginView() {
		loginController = new LoginController(this);

		//500x360
		width = 500;
		height = 360;
		margin = (int)(width * 0.01);

		setPreferredSize(new Dimension(width, height));
		setBackground(Color.white);
		setLayout(null);

		setImagePanel();
		setComponent();
	}

	public void setImagePanel() {
		imagePanel = new JPanel();
		imagePanel.setBounds(0, 0, this.width, (int)(this.height*0.5));

		ImageIcon image = new ImageIcon("Images/sejong.png");
		Image sizeConversation = image.getImage();

		double length = (int)(height*0.5)-margin*2;
		Image results = sizeConversation.getScaledInstance((int)length, (int)length, java.awt.Image.SCALE_SMOOTH);
		ImageIcon sizeImg = new ImageIcon(results);
		JLabel label = new JLabel(sizeImg);
		imagePanel.add(label);
		imagePanel.setBackground(Color.white);

		add(imagePanel);
	}

	//component을 구성하는 메소드
	public void setComponent() {

		int x = (int) (this.width*0.1),y =(int)(this.height*0.2) , width , height ;

		width = (int) (this.width * 0.12);
		height = (int)(this.height * 0.2*0.5);

		//로그인 패널
		loginPanel = new JPanel();
		loginPanel.setBounds((int)(width*0.5), imagePanel.getHeight()+margin*4, this.width, (int)(this.height*0.5));
		loginPanel.setLayout(null);

		//라벨
		JLabel ID = new JLabel("ID");
		JLabel PW = new JLabel("PW");

		ID.setBounds(x, 0, width, height);
		ID.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD + Font.ITALIC, 20));
		ID.setHorizontalAlignment(SwingConstants.CENTER);
		loginPanel.add(ID);
		ID.setLayout(null);

		PW.setBounds(x, ID.getY()+height, width, height);
		PW.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD + Font.ITALIC, 20));
		PW.setHorizontalAlignment(SwingConstants.CENTER);
		loginPanel.add(PW);
		PW.setLayout(null);

		//텍스트필드
		x = x+ID.getWidth()+margin*2;
		y = ID.getY();
		width = (int)(this.width*0.39);
		height = ID.getHeight();

		idTextField = new JTextField();
		pwTextField = new JPasswordField();

		idTextField.setBounds(x, y, width, height);
		pwTextField.setBounds(x, y+idTextField.getHeight()+margin, width, height);
		loginPanel.add(idTextField);
		loginPanel.add(pwTextField);
		setVisible(true);

		//버튼
		loginBtn = new JButton(Constants.LOGIN_TXT);
		searchIdBtn = new JButton(Constants.IDSEARCH_TXT);
		signUpBtn = new JButton(Constants.SIGNUP_TXT);

		loginBtn.setBounds(idTextField.getX()+idTextField.getWidth()+margin*2, idTextField.getY(),
				(int)(this.width*0.19), idTextField.getHeight()+pwTextField.getHeight()+margin);
		searchIdBtn.setBounds(idTextField.getX(), idTextField.getY()+idTextField.getHeight()+pwTextField.getHeight()+margin*2,
				(int)(idTextField.getWidth()*0.5-margin), height);
		signUpBtn.setBounds(idTextField.getX()+searchIdBtn.getWidth()+margin*2, idTextField.getY()+idTextField.getHeight()+pwTextField.getHeight()+margin*2,
				(int)(idTextField.getWidth()*0.5-margin), height);
		loginPanel.add(loginBtn);
		loginPanel.add(searchIdBtn);
		loginPanel.add(signUpBtn);

		loginPanel.setBackground(Color.white);
		add(loginPanel);

		signUpBtn.addActionListener(loginController);
		loginBtn.addActionListener(loginController);
		searchIdBtn.addActionListener(loginController);
	}

	public String getIdTextField(){
		return idTextField.getText();
	}

	public String getPwTextField(){
		return pwTextField.getText();
	}

}
