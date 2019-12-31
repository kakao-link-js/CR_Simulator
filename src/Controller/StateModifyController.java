package Controller;

import common.ClassManager;
import Model.UserDTO;
import View.StateModifyView;
import common.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                signUp();
                break;
            case Constants.EXIT_TXT:
                ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLoginView());
                break;
        }
    }

    private void signUp() {
        UserDTO temp = stateModifyView.getInsertData();
        if (ClassManager.getInstance().getDAO().modifyState(temp)) {
            showMessege("회원 정보 수정 성공");
            ClassManager.getInstance().getMain().comeToMain();
        } else {
            showMessege("회원 정보 수정 실패");
        }
    }

    private void showMessege(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
}