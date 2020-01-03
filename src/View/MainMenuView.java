package View;

import Controller.MainMenuController;
import Model.*;
import common.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * MainMenu Class
 * 메인메뉴 View를 갖는 Class 로 비즈니스 로직은 없다.
 */

public class MainMenuView extends JPanel {
    MainMenuController MMC;
    UserDTO user;

    private ArrayList<JButton> btnMenuList;

    //생성자
    public MainMenuView() {
        MMC = new MainMenuController(this);
        setLayout(null); //레이아웃 해제
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(410, 615));

        setProgramName();
        setSelectMenuPanel();
        setUnivNamePanel();
        setEnabledAllButton(true);
    }

    //프로그램이름 패널 설정
    private void setProgramName() {
        JLabel lblProgramName = new JLabel("<html><center>CLASS<br>REGISTRATION<br>SIMULATOR</center></html>");
        lblProgramName.setBackground(Color.WHITE);
        lblProgramName.setOpaque(true);
        lblProgramName.setFont(new Font(DesignConstants.ENGLISH_FORMAL_FONT, Font.BOLD, 30));
        lblProgramName.setForeground(new Color(DesignConstants.SIGNATURE_COLOR));
        lblProgramName.setVerticalAlignment(SwingConstants.CENTER);
        lblProgramName.setHorizontalAlignment(SwingConstants.CENTER);
        lblProgramName.setBounds(5, 5, 400, 150);

        add(lblProgramName);
    }

    //선택 메뉴 설정
    private void setSelectMenuPanel() {
        BufferedImage srcImg = null;
        try {
            srcImg = ImageIO.read(new File("Images/logo2.png"));
        } catch (IOException e) { }
        Image imgLogo = srcImg.getScaledInstance(250, 250, Image.SCALE_SMOOTH);

        JPanel selectMenuPanel = new JPanel() { //패널에 이미지 설정
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imgLogo, 75, 25, null);
            }
        };
        selectMenuPanel.setBackground(Color.WHITE);
        selectMenuPanel.setBounds(5, 160, 400, 345);
        selectMenuPanel.setLayout(new GridLayout(Constants.MENU.length,1));

        btnMenuList = new ArrayList<>();
        for (String menu : Constants.MENU) { //메뉴 종류에 따라 메뉴 버튼 생성.
            JButton btnMenu = new JButton(menu);

            btnMenu.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
            btnMenu.setBorderPainted(false);
            btnMenu.setContentAreaFilled(false);
            btnMenu.setFocusPainted(false);
            btnMenu.setOpaque(false);
            btnMenu.setForeground(new Color(DesignConstants.SIGNATURE_COLOR));
            selectMenuPanel.add(btnMenu);
            btnMenuList.add(btnMenu);
        }
        addMenuButtonListener();
        add(selectMenuPanel);
    } //private void setSelectMenuPanel()

    //대학교 이름 패널 설정 메소드
    private void setUnivNamePanel() {
        JLabel lblUnivName = new JLabel(Constants.UNIV_NAME);
        lblUnivName.setBackground(Color.WHITE);
        lblUnivName.setOpaque(true);
        lblUnivName.setFont(new Font(DesignConstants.ENGLISH_FORMAL_FONT, Font.BOLD, 30));
        lblUnivName.setForeground(new Color(DesignConstants.SIGNATURE_COLOR));
        lblUnivName.setVerticalAlignment(SwingConstants.CENTER);
        lblUnivName.setHorizontalAlignment(SwingConstants.CENTER);
        lblUnivName.setBounds(5, 510, 400, 100);

        add(lblUnivName);
    } //private void setUnivNamePanel()

    //버튼 리스너 연결 메소드
    private void addMenuButtonListener() {
        for (JButton btnMenu : btnMenuList) {
            btnMenu.addActionListener(MMC);
            btnMenu.addMouseListener(MMC);
        }
    } //public void addMenuButtonListener()

    //flag대로 버튼 활성화 및 비활성화.
    private void setEnabledAllButton(boolean flag) {
        for (JButton btnMenu : btnMenuList)
            btnMenu.setEnabled(flag);
    }//public void setEnabledAllButton(boolean flag)


    public UserDTO getUser(){return user;}

    public void setUser(UserDTO user) {this.user = user;}

}