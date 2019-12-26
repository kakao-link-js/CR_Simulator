package View;

import common.DesignConstants;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainMenuView extends JPanel {

    private ArrayList<JButton> btnMenuList;

    private final static String[] MENU = {"수강신청", "관심과목", "내 시간표", "학점 계산기", "종료"};

    //생성자
    public MainMenuView() {
        setLayout(null); //레이아웃 해제
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(410, 615));

        setProgramName();
        try {
            setSelectMenuPanel();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    private void setSelectMenuPanel() throws IOException {
        BufferedImage srcImg = ImageIO.read(new File("Images/logo2.png"));
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
        selectMenuPanel.setLayout(new GridLayout(MENU.length,1));

        btnMenuList = new ArrayList<>();
        for (String menu : MENU) { //메뉴 종류에 따라 메뉴 버튼 생성.
            JButton btnMenu = new JButton(menu);

            btnMenu.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
            btnMenu.setBorderPainted(false);
            btnMenu.setContentAreaFilled(false);
            btnMenu.setFocusPainted(false);
            btnMenu.setOpaque(false);
            btnMenu.setForeground(new Color(DesignConstants.SIGNATURE_COLOR));

            if (!menu.equals("종료"))
                btnMenu.setEnabled(false);

            selectMenuPanel.add(btnMenu);
            btnMenuList.add(btnMenu);
        }

        add(selectMenuPanel);
    }

    //대학교 이름 패널 설정 메소드
    private void setUnivNamePanel() {
        JLabel lblUnivName = new JLabel("SEJONG UNIVERSITY");
        lblUnivName.setBackground(Color.WHITE);
        lblUnivName.setOpaque(true);
        lblUnivName.setFont(new Font(DesignConstants.ENGLISH_FORMAL_FONT, Font.BOLD, 30));
        lblUnivName.setForeground(new Color(DesignConstants.SIGNATURE_COLOR));
        lblUnivName.setVerticalAlignment(SwingConstants.CENTER);
        lblUnivName.setHorizontalAlignment(SwingConstants.CENTER);
        lblUnivName.setBounds(5, 510, 400, 100);

        add(lblUnivName);
    }

    //버튼 리스너 연결 메소드
    public void addMenuButtonListener(EventListener listener) {
        for (JButton btnMenu : btnMenuList) {
            btnMenu.addActionListener((ActionListener)listener);
            btnMenu.addMouseListener((MouseListener)listener);
        }
    }

    //flag대로 버튼 활성화 및 비활성화.
    public void setEnabledAllButton(boolean flag) {
        for (JButton btnMenu : btnMenuList) {
            if (!btnMenu.getText().equals("종료"))
                btnMenu.setEnabled(flag);
        }
    }

}
