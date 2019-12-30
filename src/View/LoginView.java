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
import Model.*;
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

	//생성자
	public LoginView() {

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

		int x = (int) (width*0.1),y =(int) (height*0.2) , width , height ;

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
		loginBtn = new JButton("로그인");
		searchIdBtn = new JButton("ID 찾기");
		signUpBtn = new JButton("회원 가입");

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


		//***********************************************//
		//회원가입 리스너
		ActionListener listener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ClassManager.getInstance().getMainMenuController().comeToMain();
				//회원가입창을 새창에 띄움 
				JFrame frame = new JFrame();
				frame.setBounds(770,220,485,570);
				frame.add(new SignUpView());
				frame.setVisible(true);
			}

		};
		signUpBtn.addActionListener(listener);
		
		//로그인버튼 리스너
		ActionListener id_pw_Listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				String id = idTextField.getText();
				char[] pass = ((JPasswordField) pwTextField).getPassword();
				String password = new String(pass);
				JOptionPane.showMessageDialog(null, "ID : "+id+"\nPW : "+password);
			}
		};
		loginBtn.addActionListener(id_pw_Listener);
		
		//id찾기버튼 리스너
		ActionListener id_Listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				String id = idTextField.getText();
				JOptionPane.showMessageDialog(null, "ID찾기");
			}
		};
		searchIdBtn.addActionListener(id_Listener);
	}



}
