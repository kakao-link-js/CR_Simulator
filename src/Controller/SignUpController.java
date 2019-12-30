package Controller;

import Model.UserDTO;
import View.SignUpView;
import common.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpController implements ActionListener{

    SignUpView signUpView;

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
        }
    }

    private void checkDuplication(){
        String id = signUpView.getId();
        JOptionPane.showMessageDialog(null, "ID : "+id);
    }

    private void signUp(){
        UserDTO temp = signUpView.getInsertData();
    }
}