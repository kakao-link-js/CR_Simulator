package Controller;

import common.ClassManager;
import View.FindIdView;
import common.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FindIdController Class
 * 아이디 찾기를 담당하는 Controller
 */

public class FindIdController implements ActionListener {

    FindIdView findIdView;

    public FindIdController(FindIdView findIdView){
        this.findIdView = findIdView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String operator = ((JButton)e.getSource()).getText();
        switch (operator){
            case Constants.EXIT_TXT:
                findIdView.resetView();
                ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLoginView());
                break;
            case Constants.IDSEARCH_TXT:
                findId();
                break;
        }
    }

    //ID 찾기 메소드
    private void findId(){
        String name = findIdView.getName();
        String email = findIdView.getEmail();
        String output = ClassManager.getInstance().getDAO().getID(name,email);
        if(output != null){
            showMessege("당신의 ID는 "+output + "입니다.");
            findIdView.resetView();
            ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLoginView());
        }else{
            showMessege("이름이나 이메일이 잘못 되었습니다.");
        }
    }

    private void showMessege(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
}
