package Controller;

import common.ClassManager;
import Model.UserDTO;
import View.SignUpView;
import common.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpController implements ActionListener{

    SignUpView signUpView;
    boolean flag = false;

    public SignUpController( SignUpView signUpView){
        this.signUpView = signUpView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String operator = ((JButton)e.getSource()).getText();
        switch (operator){
            case Constants .DUPLICATE_TXT:
                checkDuplication();
                break;
            case Constants.SIGNUP_TXT:
                signUp();
                break;
            case Constants.EXIT_TXT:
                ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLoginView());
                break;
        }
    }

    private void checkDuplication(){
        String id = signUpView.getId();
        if(ClassManager.getInstance().getDAO().isDuplicateID(id)){
            flag = true;
        }
        showMessege("중복된 아이디 입니다.");
    }

    private void signUp(){
        if(flag) {
            UserDTO temp = signUpView.getInsertData();
            if(ClassManager.getInstance().getDAO().signUp(temp)){
                showMessege("회원 등록 성공");
                ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLoginView());
            }
            else{
                showMessege("회원 등록 실패");
            }
        }else{
            showMessege("중복 확인을 해주세요.");
        }
    }
    private void showMessege(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
}