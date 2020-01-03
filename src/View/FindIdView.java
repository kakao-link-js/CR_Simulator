package View;

import Controller.FindIdController;
import common.Constants;
import common.DesignConstants;

import javax.swing.*;
import java.awt.*;

/**
 * FindIdView Class
 * 아이디 찾기 View를 갖는 Class 로 비즈니스 로직은 없다.
 */

public class FindIdView extends JPanel {
    private int width = 510, height = 220;
    private JTextField nameTextField; //이름
    private JTextField emailTextField; //휴대전화
    private JButton findBtn;
    FindIdController findIdController;

    public FindIdView(){
        findIdController = new FindIdController(this);

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.white);
        setLayout(null);

        addComponent();
    }

    public void addComponent(){
        JLabel lblTitle = new JLabel("ID 찾기");
        lblTitle.setBounds(105,10,300,30);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font(DesignConstants.HANGUL_FONT,Font.BOLD,30));
        add(lblTitle);

        JLabel lblName = new JLabel(Constants.NAMEKOR_TXT);
        JLabel lblPhone = new JLabel(Constants.EMAILKOR_TXT);
        lblName.setBounds( 110, 70, 40,30);
        lblName.setFont(new Font(DesignConstants.HANGUL_FONT,Font.BOLD,20));
        lblPhone.setFont(new Font(DesignConstants.HANGUL_FONT,Font.BOLD,20));
        lblPhone.setBounds( 90, 105, 80,30);
        add(lblName);
        add(lblPhone);

        nameTextField = new JTextField();
        nameTextField.setBounds(180,lblName.getY(),200,lblName.getHeight());
        nameTextField.setFont(new Font(DesignConstants.HANGUL_FONT,Font.PLAIN,15));
        emailTextField = new JTextField();
        emailTextField.setBounds(180,lblPhone.getY(),200,lblPhone.getHeight());
        emailTextField.setFont(new Font(DesignConstants.HANGUL_FONT,Font.PLAIN,15));

        add(nameTextField);
        add(emailTextField);

        setButton();
    }

    private void setButton(){
        findBtn = new JButton(Constants.IDSEARCH_TXT);
        findBtn.setBackground(Color.white);
        findBtn.setFont(new Font(DesignConstants.HANGUL_FONT,Font.BOLD,20));
        findBtn.setBounds(170,145,140,30);
        findBtn.addActionListener(findIdController);
        add(findBtn);

        JButton exitBtn = new JButton(Constants.EXIT_TXT);
        exitBtn.setFont(new Font(DesignConstants.HANGUL_FONT,Font.PLAIN,10));
        exitBtn.setBounds(20,20,70,40);
        exitBtn.setBackground(Color.white);
        exitBtn.addActionListener(findIdController);
        add(exitBtn);
    }

    public String getName(){
        return nameTextField.getText();
    }

    public String getEmail(){
        return emailTextField.getText();
    }

    public void resetView(){
        nameTextField.setText("");
        emailTextField.setText("");
    }
}
