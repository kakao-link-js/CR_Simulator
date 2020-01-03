package Controller;

import common.ClassManager;
import View.*;
import common.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * LoginController Class
 * Login을 담당하는 Class
 */

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

    //아이디와 비밀번호를 확인 후 로그인 하는 메소드
    private void login(){
        String id = loginView.getIdTextField();
        String password = loginView.getPwTextField();
        if(ClassManager.getInstance().getDAO().isCorrectID(id,password)){
            loginView.resetInput();
            ClassManager.getInstance().getMainMenuView().setUser(ClassManager.getInstance().getDAO().getUserData(id));
            ClassManager.getInstance().getMain().comeToMain();
        } else {
            JOptionPane.showMessageDialog(null, "잘못된 아이디나 비밀번호 입니다.");
        }
    }

    //회원가입 View로 이동하는 메소드
    private void signUp(){
        SignUpView signUpView = ClassManager.getInstance().getSignUpView();
        ClassManager.getInstance().getMain().changePanel(signUpView);
    }

    //아이디 찾기 View로 이동하는 메소드
    private void idSearch(){
        FindIdView findIdView = ClassManager.getInstance().getFindIdView();
       ClassManager.getInstance().getMain().changePanel(findIdView);
    }
}