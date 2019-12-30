package Controller;

import Model.ClassManager;
import View.FindIdView;
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
        if(ClassManager.getInstance().getDAO().isCorrectID(id,password)){
            ClassManager.getInstance().getMain().comeToMain();
        } else {
            JOptionPane.showMessageDialog(null, "잘못된 아이디나 비밀번호 입니다.");
        }
    }

    private void signUp(){
        SignUpView signUpView = ClassManager.getInstance().getSignUpView();
        ClassManager.getInstance().getMain().changePanel(signUpView);
    }

    private void idSearch(){
        FindIdView findIdView = ClassManager.getInstance().getFindIdView();
       ClassManager.getInstance().getMain().changePanel(findIdView);
    }
}