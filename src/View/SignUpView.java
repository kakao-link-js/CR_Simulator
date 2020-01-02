package View;

import java.awt.*;
import javax.swing.*;

//import org.apache.commons.math3.analysis.function.Add;

import Controller.SignUpController;
import Model.*;
import common.Constants;
import common.DesignConstants;

public class SignUpView extends JPanel{
	//회원가입 창을 위한 Instance
	JPanel signUpPanel; //회원가입 패널
	private int width, height, margin; //기본으로 넣을 크기
	private JButton duChkBtn; //중복체크 버튼
	private JButton signUpBtn; //회원가입 버튼
	private JButton emailChkBtn; //이메일 인증 버튼
	private JTextField idTextField, pwTextField;
	private JTextField nameTextField; //이름 
	private JTextField phoneTextField; //휴대전화
	private JTextField emailTextField; //이메일
	private JLabel emailChk; //이메일 인증여부표시

	SignUpController signUpController;

	public SignUpView() {
		signUpController = new SignUpController(this);
		//470x570
		width = 470;
		height = 570;
		margin = (int)(width * 0.01);

		setPreferredSize(new Dimension(width, height));
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblTitle = new JLabel("회원 가입");
		lblTitle.setFont(new Font(DesignConstants.HANGUL_FONT,Font.BOLD,30));
		lblTitle.setBounds(40,30,390,40);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle);

		JButton exitBtn = new JButton(Constants.EXIT_TXT);
		exitBtn.setFont(new Font(DesignConstants.HANGUL_FONT,Font.PLAIN,10));
		exitBtn.setBounds(20,20,70,50);
		exitBtn.setBackground(Color.white);
		exitBtn.addActionListener(signUpController);
		add(exitBtn);

		setSignUpPanel();
	}

	//그룹화
	private void setSignUpPanel() {
		int x, y;

		signUpPanel = new JPanel();
		signUpPanel.setBounds(0, 0, width, height);
		signUpPanel.setLayout(null);

		//label
		x = 0;
		y = (int)(height * 0.15);
		width = (int)(width * 0.2);
		height = (int)(height * 0.08);

		JLabel ID = new JLabel(Constants.IDKOR_TXT);
		JLabel PW = new JLabel(Constants.PWKOR_TXT);
		JLabel name = new JLabel(Constants.NAMEKOR_TXT);
		JLabel phone = new JLabel(Constants.PHONEKOR_TXT);
		JLabel email = new JLabel(Constants.EMAILKOR_TXT);
		emailChk = new JLabel(Constants.EMAILCHK_TXT);

		ID.setBounds(x+margin*6, y, width, height);
		ID.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD + Font.PLAIN, 20));
		ID.setHorizontalAlignment(SwingConstants.CENTER);
		ID.setLayout(null);

		PW.setBounds(x+margin*6, ID.getY()+ID.getHeight()+margin*6, width, height);
		PW.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD + Font.PLAIN, 20));
		PW.setHorizontalAlignment(SwingConstants.CENTER);
		PW.setLayout(null);

		name.setBounds(x+margin*6, PW.getY()+PW.getHeight()+margin*6, width, height);
		name.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD + Font.PLAIN, 20));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setLayout(null);

		phone.setBounds(x+margin*6, name.getY()+name.getHeight()+margin*6, width, height);
		phone.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD + Font.PLAIN, 20));
		phone.setHorizontalAlignment(SwingConstants.CENTER);
		phone.setLayout(null);

		email.setBounds(x+margin*6, phone.getY()+phone.getHeight()+margin*6, width, height);
		email.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD + Font.PLAIN, 20));
		email.setHorizontalAlignment(SwingConstants.CENTER);
		email.setLayout(null);

		//이메일 인증여부 표시
		emailChk.setBounds(this.width-x+margin*5, email.getY()+phone.getHeight()-margin, width, height);
		emailChk.setFont(new Font(DesignConstants.HANGUL_FONT, Font.PLAIN, 12));
		emailChk.setHorizontalAlignment(SwingConstants.CENTER);
		emailChk.setForeground(Color.red);
		emailChk.setLayout(null);


		signUpPanel.add(ID);
		signUpPanel.add(PW);
		signUpPanel.add(name);
		signUpPanel.add(phone);
		signUpPanel.add(email);
		signUpPanel.add(emailChk);

		//TextField
		x = ID.getWidth()+margin*10;
		y =	ID.getY();
		width = (int)(ID.getWidth()*2.5);
		height = ID.getHeight();

		idTextField = new JTextField();
		pwTextField = new JTextField();
		nameTextField = new JTextField();
		phoneTextField = new JTextField();
		emailTextField = new JTextField();

		idTextField.setBounds(x, y, width, height);
		pwTextField.setBounds(x, y+idTextField.getHeight()+margin*6, width, height);
		nameTextField.setBounds(x, PW.getY()+idTextField.getHeight()+margin*6, width, height);
		phoneTextField.setBounds(x, name.getY()+idTextField.getHeight()+margin*6, width, height);
		emailTextField.setBounds(x, phone.getY()+idTextField.getHeight()+margin*6, width, height);

		signUpPanel.add(idTextField);
		signUpPanel.add(pwTextField);
		signUpPanel.add(nameTextField);
		signUpPanel.add(phoneTextField);
		signUpPanel.add(emailTextField);

		//Button
		duChkBtn = new JButton(Constants.DUPLICATE_TXT);
		duChkBtn.setBackground(Color.white);
		signUpBtn = new JButton(Constants.SIGNUP_TXT);
		signUpBtn.setBackground(Color.white);
		emailChkBtn = new JButton(Constants.EMAIL_TXT);
		emailChkBtn.setBackground(Color.white);
		duChkBtn.setBounds(idTextField.getX()+idTextField.getWidth()+margin*2, ID.getY(),
				ID.getY(), ID.getHeight());
		signUpBtn.setBounds((int) (idTextField.getX()*1.4),
				email.getY()+email.getHeight()+margin*12,
				ID.getWidth(), ID.getHeight());
		emailChkBtn.setBounds(duChkBtn.getX(), email.getY(), ID.getY(), ID.getHeight());

		signUpPanel.add(duChkBtn);
		signUpPanel.add(signUpBtn);
		signUpPanel.add(emailChkBtn);
		
		signUpPanel.setBackground(Color.WHITE);
		add(signUpPanel);


		duChkBtn.addActionListener(signUpController);
		signUpBtn.addActionListener(signUpController);
		emailChkBtn.addActionListener(signUpController);

	}

	public String getId(){
		return idTextField.getText();
	}
	public String getEmail() { return emailTextField.getText(); }
	public void setEmailChk()
	{
		//이메일 인증여부 표시
		emailChk.setText("인증완료");
		emailChk.setForeground(Color.green);
		emailTextField.setEditable(false);
	}

	public UserDTO getInsertData(){
		UserDTO temp = new UserDTO();
		if(idTextField.getText() == null || pwTextField.getText() == null || nameTextField.getText() == null || phoneTextField.getText() == null || emailTextField.getText() == null)
			return null;
		temp.setId(idTextField.getText());
		temp.setPassword(pwTextField.getText());
		temp.setName(nameTextField.getText());
		temp.setPhone(phoneTextField.getText());
		temp.setEmail(emailTextField.getText());
		return temp;
	}

	public void resetView(){
		emailChk.setText("인증안됨");
		emailChk.setForeground(Color.RED);
		emailTextField.setEditable(true);
		idTextField.setText("");
		pwTextField.setText("");
		nameTextField.setText("");
		phoneTextField.setText("");
		emailTextField.setText("");
	}

}
