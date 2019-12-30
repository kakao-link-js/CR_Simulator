package Controller;

import Model.ClassManager;
import View.LoginView;
import View.SignUpView;
import common.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {

    LoginView loginView;

    public LoginController(LoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String operator = ((JButton)e.getSource()).getText();
        switch(operator){
            case Constants.LOGIN_TXT:
                login();
                break;
            case Constants.IDSEARCH_TXT:
                idSearch();
                break;
            case Constants.SIGNUP_TXT:
                signUp();
                break;
        }
    }

    private void login(){
        String id = loginView.getIdTextField();
        String password = loginView.getPwTextField();
        ClassManager.getInstance().getMain().comeToMain();
    }

    private void signUp(){
        JFrame frame = new JFrame();
        frame.setBounds(770,220,485,570);
        frame.add(new SignUpView());
        frame.setVisible(true);
    }

    private void idSearch(){
        String id = loginView.getIdTextField();
        JOptionPane.showMessageDialog(null, "ID찾기");
    }
}