package Controller;

import common.ClassManager;
import Model.UserDTO;
import View.StateModifyView;
import common.Constants;
import common.ExceptionHandling;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * StateModifyController Class
 * 상태를 변경을 담당하는 Class
 */

public class StateModifyController implements ActionListener{

    StateModifyView stateModifyView;

    public StateModifyController(StateModifyView stateModifyView){
        this.stateModifyView = stateModifyView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String operator = ((JButton)e.getSource()).getText();
        switch (operator){
            case Constants.MODIFYKOR_TXT:
                modify();
                break;
            case Constants.EXIT_TXT:
                ClassManager.getInstance().getMain().comeToMain();
                break;
        }
    }

    private void modify() {
        UserDTO temp = stateModifyView.getInsertData();
        if(!checkException(temp))
            return;
        if (ClassManager.getInstance().getDAO().modifyState(temp)) {
            showMessege("회원 정보 수정 성공");
            ClassManager.getInstance().getMain().comeToMain();
        } else {
            showMessege("회원 정보 수정 실패");
        }
    }

    //예외처리 메소드
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
        return true;
    }

    private void showMessege(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
}