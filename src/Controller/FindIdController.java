package Controller;

import Model.ClassManager;
import View.FindIdView;
import common.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLoginView());
                break;
            case Constants.IDSEARCH_TXT:
                findId();
                break;
        }
    }

    private void findId(){
        String name = findIdView.getName();
        String phone = findIdView.getPhone();
        String output = ClassManager.getInstance().getDAO().getID(name,phone);
        if(output != null){
            showMessege("당신의 ID는 "+output + "입니다.");
        }else{
            showMessege("이름이나 전화번호가 잘못 되었습니다.");
        }
    }

    private void showMessege(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
}
