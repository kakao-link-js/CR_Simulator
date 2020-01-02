package Controller;

import common.ClassManager;
import Model.UserDTO;
import View.SignUpView;
import common.Constants;
import common.ExceptionHandling;

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
        if(!ExceptionHandling.isOnlyNumber(id)){
            showMessege("id를 숫자로만 입력 해주세요.");
            return;
        }
        if(ClassManager.getInstance().getDAO().isDuplicateID(id)){
            flag = true;
            showMessege("사용이 가능한 아이디 입니다.");
            return;
        }
        showMessege("중복된 아이디 입니다.");
    }

    private void signUp(){
        if(flag) {
            UserDTO temp = signUpView.getInsertData();
            if(temp == null) {
                showMessege("입력 되지 않은 값이 있습니다.");
                return;
            }
            if(!checkException(temp))
                return;
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

    private boolean checkException(UserDTO user){
        if(user.getPassword() == null){
            showMessege("비밀번호가 빈값입니다.");
            return false;
        }
        if(!ExceptionHandling.isOnlyKorean(user.getName())) {
            showMessege("이름이 한글이 아닙니다.");
            return false;
        }
        if(!ExceptionHandling.isPhoneNumber(user.getPhone())) {
            showMessege("전화번호 형식이 맞지 않습니다.(010-XXXX-XXXX)");
            return false;
        }
        if(!ExceptionHandling.isBirth(user.getBirth())){
            showMessege("생년월일 형식이 맞지 않습니다.(주민번호앞자리 형식)");
            return false;
        }
        return true;
    }


    private void showMessege(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
}