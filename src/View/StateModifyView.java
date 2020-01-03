package View;

import Controller.StateModifyController;
import Model.UserDTO;
import common.ClassManager;
import common.Constants;
import common.DesignConstants;

import javax.swing.*;
import java.awt.*;

/**
 * StateModifyView Class
 * 내정보변경 View를 갖는 Class 로 비즈니스 로직은 없다.
 */

public class StateModifyView extends JPanel{
	//회원가입 창을 위한 Instance
	JPanel signUpPanel; //회원가입 패널
	private int width, height, margin; //기본으로 넣을 크기
	private JButton confirmBtn; //회원가입 버튼
	private JTextField idTextField;
	private JPasswordField pwTextField;
	private JTextField nameTextField; //이름
	private JTextField phoneTextField; //휴대전화
	private JTextField emailTextField; //생년월일

	StateModifyController stateModifyController;

	public StateModifyView() {
		stateModifyController = new StateModifyController(this);
		//470x570
		width = 470;
		height = 570;
		margin = (int)(width * 0.01);

		setPreferredSize(new Dimension(width, height));
		setBackground(Color.WHITE);
		setLayout(null);

		JLabel lblTitle = new JLabel("회원 정보 수정");
		lblTitle.setFont(new Font(DesignConstants.HANGUL_FONT,Font.BOLD,30));
		lblTitle.setBounds(40,30,390,40);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle);

		JButton exitBtn = new JButton(Constants.EXIT_TXT);
		exitBtn.setFont(new Font(DesignConstants.HANGUL_FONT,Font.PLAIN,10));
		exitBtn.setBounds(20,20,70,50);
		exitBtn.setBackground(Color.white);
		exitBtn.addActionListener(stateModifyController);
		add(exitBtn);

		setPanel();
		setUserData();
	}

	//그룹화
	private void setPanel() {
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

		signUpPanel.add(ID);
		signUpPanel.add(PW);
		signUpPanel.add(name);
		signUpPanel.add(phone);
		signUpPanel.add(email);

		//TextField
		x = ID.getWidth()+margin*10;
		y =	ID.getY();
		width = (int)(ID.getWidth()*2.5);
		height = ID.getHeight();

		idTextField = new JTextField();
		pwTextField = new JPasswordField();
		nameTextField = new JTextField();
		phoneTextField = new JTextField();
		emailTextField = new JTextField();

		idTextField.setBounds(x, y, width, height);
		idTextField.setEditable(false); //ID는 수정이 안된다.
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

		confirmBtn = new JButton(Constants.MODIFYKOR_TXT);
		confirmBtn.setBackground(Color.white);

		confirmBtn.setBounds((int) (idTextField.getX()*1.4), email.getY()+email.getHeight()+margin*10,
				ID.getWidth(), ID.getHeight());


		confirmBtn.addActionListener(stateModifyController);

		signUpPanel.add(confirmBtn);
		
		signUpPanel.setBackground(Color.WHITE);
		add(signUpPanel);
	}

	public UserDTO getInsertData(){
		UserDTO temp = new UserDTO();
		temp.setId(idTextField.getText());
		temp.setPassword(pwTextField.getText());
		temp.setName(nameTextField.getText());
		temp.setPhone(phoneTextField.getText());
		temp.setEmail(emailTextField.getText());
		return temp;
	}

	private void setUserData(){
		UserDTO temp = ClassManager.getInstance().getMainMenuView().getUser();
		System.out.println(temp.getId()+" "+temp.getPhone());
		idTextField.setText(temp.getId());
		nameTextField.setText(temp.getName());
		phoneTextField.setText(temp.getPhone());
		emailTextField.setText(temp.getEmail());
	}

}
