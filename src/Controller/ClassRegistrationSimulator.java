package Controller;

import common.ClassManager;
import View.*;

import javax.swing.*;
import java.awt.*;

/**
 * ClassRegistrationSimulator Class
 * 메인 Frame을 구성하며 실제 프로그램 Main 파트 클래스
 */
public class ClassRegistrationSimulator {
    private JFrame frame;

    public static void main(String[] arg) {
        ClassManager.getInstance().getMain();
    } //public static void main(String[] arg)

    public ClassRegistrationSimulator() {
        createAndShowUI();
    } //public ClassRegistrationSimulator()

    //Frame을 만들고 구성하는 메소드
    private void createAndShowUI() {
        frame = new JFrame("Class Registration Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        LoginView loginView = ClassManager.getInstance().getLoginView();
        frame.getContentPane().add(loginView);

        setLocationScreenCenter();

        frame.pack();
        frame.setVisible(true);
    } //private void createAndShowUI()

    private void setLocationScreenCenter() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2 - 410/2, dim.height/2 - 615/2);
    } // setLocationScreenCenter()

    //패널을 교체하는 메소드
    public void changePanel(JPanel view) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(view);
        frame.pack();
        frame.repaint();
    } // public void changePanel(JPanel view)

    public void comeToMain(){
        changePanel(ClassManager.getInstance().getMainMenuView());
    }
}
