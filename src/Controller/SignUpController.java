package Controller;

import common.ClassManager;
import Model.UserDTO;
import View.SignUpView;
import common.Constants;
import common.ExceptionHandling;

import javax.mail.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SignUpController implements ActionListener {

    SignUpView signUpView;
    boolean flag = false;
    boolean emailFlag = false;
    boolean validEmailFlag = true;

    public SignUpController(SignUpView signUpView) {
        this.signUpView = signUpView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String operator = ((JButton) e.getSource()).getText();
        switch (operator) {
            case Constants.DUPLICATE_TXT:
                checkDuplication();
                break;
            case Constants.SIGNUP_TXT:
                signUp();
                break;
            case Constants.EMAIL_TXT:
                checkEmail();
                break;
            case Constants.EXIT_TXT:
                resetData();
                ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLoginView());
                break;
        }
    }

    private void resetData(){
        flag = false;
        emailFlag = false;
        validEmailFlag = true;
        signUpView.resetView();
    }

    private void checkDuplication() {
        String id = signUpView.getId();
        if (!ExceptionHandling.isOnlyNumber(id)) {
            showMessege("id를 숫자로만 입력 해주세요.");
            return;
        }
        if (ClassManager.getInstance().getDAO().isDuplicateID(id)) {
            flag = true;
            showMessege("사용이 가능한 아이디 입니다.");
            return;
        }
        showMessege("중복된 아이디 입니다.");
    }

    private void signUp() {
        if (flag) {
            UserDTO temp = signUpView.getInsertData();
            if(temp == null) {
                showMessege("입력 되지 않은 값이 있습니다.");
                return;
            }
            if(!checkException(temp))
                return;
            if(!emailFlag) {
                showMessege("이메일 인증을 완료해주세요.");
                return;
            }
            if (ClassManager.getInstance().getDAO().signUp(temp)) {
                showMessege("회원가입 성공");
                resetData();
                ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLoginView());
            }
            else {
                showMessege("회원가입 실패");
            }
        } else {
            showMessege("중복 확인을 해주세요.");
        }
    }

    private boolean checkException(UserDTO user) {
        if (user.getPassword() == null) {
            showMessege("비밀번호가 빈값입니다.");
            return false;
        }
        if (!ExceptionHandling.isOnlyKorean(user.getName())) {
            showMessege("이름이 한글이 아닙니다.");
            return false;
        }
        if (!ExceptionHandling.isPhoneNumber(user.getPhone())) {
            showMessege("전화번호 형식이 맞지 않습니다.(010-XXXX-XXXX)");
            return false;
        }
        return true;
    }

    private void showMessege(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    class MyAuthentication extends Authenticator {
        PasswordAuthentication pa;
        public MyAuthentication(String mailId, String mailPass) { pa = new PasswordAuthentication(mailId, mailPass); }
        public PasswordAuthentication getPasswordAuthentication() { return pa; }
    }
    //이메일 인증 메소드
    private void checkEmail() {
        //메일 전송
        String mailProtocol = "smtp";
        String mailHost = "smtp.gmail.com";
        String mailPort = "587";
        String mailId = "msmn1729@gmail.com"; // 구글계정
        String mailPassword = "park1235!"; // 구글계정 비밀번호

        String fromName = "관리자";
        String fromEmail = "sejong@gmail.com"; // 보내는 사람 메일
        String toName = "회원";
        String toEmail = signUpView.getEmail(); // 받는사람메일
        String mailTitle = "회원가입 인증 코드 발급 안내 입니다.";

        int ran = new Random().nextInt(900000) + 100000;
        String authCode = String.valueOf(ran);
        String mailContents = "귀하의 인증 코드는 " + authCode +"입니다.";
        String debugMode = "false";
        String authMode = "true";

        try {
            boolean debug = Boolean.valueOf(debugMode).booleanValue();

            Properties mailProps = new Properties();
            mailProps.put("mail.smtp.starttls.enable", "true");
            mailProps.setProperty("mail.transport.protocol", mailProtocol);
            mailProps.put("mail.debug", debugMode);
            mailProps.put("mail.smtp.host", mailHost);
            mailProps.put("mail.smtp.port", mailPort);
            mailProps.put("mail.smtp.connectiontimeout", "5000");
            mailProps.put("mail.smtp.timeout", "5000");
            mailProps.put("mail.smtp.auth", authMode);

            Session msgSession = null;

            if(authMode.equals("true")) {
                Authenticator auth = new MyAuthentication(mailId, mailPassword);
                msgSession = Session.getInstance(mailProps, auth);
            } else {
                msgSession = Session.getInstance(mailProps, null);
            }
            msgSession.setDebug(debug);

            MimeMessage msg = new MimeMessage(msgSession);
            msg.setFrom(new InternetAddress(fromEmail, fromName));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, toName));
            msg.setSubject(mailTitle);
            msg.setContent(mailContents, "text/html; charset=euc-kr");

            // 스태틱함수로 직접 보내지 않고 객체를 이용해서 보내고 객체를 닫아준다.
            Transport t = msgSession.getTransport(mailProtocol);

            try {
                t.connect();
                t.sendMessage(msg, msg.getAllRecipients());
            } finally {
                t.close();
            }
        } catch(Exception e) {
            showMessege("유효하지 않은 이메일주소입니다.");
            validEmailFlag = false;
        }

        //인증번호 확인
        while (!emailFlag && validEmailFlag) {
            String code = JOptionPane.showInputDialog("인증번호를 입력하세요.");
            if (code == null) {
                break;
            }
            if (code.equals(authCode)) {
                JOptionPane.showMessageDialog(null, "인증완료");
                emailFlag = true; //인증완료
                signUpView.setEmailChk();
                break;
            } else {
                JOptionPane.showMessageDialog(null, "인증실패");
                emailFlag = false;
            }
        }
        validEmailFlag = true;
    }

}


