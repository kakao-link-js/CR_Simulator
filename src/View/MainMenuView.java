package View;

import Controller.MainMenuController;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenuView extends JPanel {
    MainMenuController MMC;

    public JPanel pathPanel; // 경로 Panel
    JPanel proNamePanel; // 프로그램 이름 Panel
    JPanel selectMenuPanel; // 메뉴 Panel
    JPanel univNamePanel; // 대학교 이름 Panel

    // SetBounds를 위한 x, y, width, height
    private int x, y, width, height;
    private int standardNum = 50;
    private double standardBlank = 0.1;

    // pathPanel
    public String filePath = "";
    public JLabel lblPath;
    public int infoSelectedFile;
    private JButton browseButton;
    public final JFileChooser excFileChooser; {
        excFileChooser = new JFileChooser();
    }

    // proNamePanel
    private JLabel lblProName;
    public JButton[] btnMenu;

    // selectMenuPanel
    private final static String[] MENU = {"수강신청","관심과목","내 시간표", "학점 계산기", "종료"};

    // univNamePanel
    private JLabel universityName;

    // 파일 선택 여부에 대한 boolean 정보
    public boolean checkFile = false;

    public MainMenuView(MainMenuController MMC) {
        this.MMC = MMC;

        setBackground(Color.lightGray);
        setPreferredSize(new Dimension((int)(standardNum*8.2),(int)(standardNum*12.5)));
        setLayout(null);

        setPathPanel(); // pathPanel 설정
        setProNamePanel();
        setSelectMenuPanel();
        setUnivNamePanel();
    }

    // pathPanel 설정
    public void setPathPanel() {
        // pathPanel 설정, 기본 x, y, width, height
        x = (int) (standardNum*standardBlank); //
        y = (int) (standardNum*standardBlank);
        width = (int) (standardNum*8);
        height = (int) (standardNum);

        pathPanel = new JPanel();
        pathPanel.setBackground(Color.white);
        pathPanel.setBounds(x, y, width, height);
        pathPanel.setLayout(null);
        add(pathPanel);

        lblPath = new JLabel("PATH : "+ filePath) ;
        lblPath.setFont(new Font("a이끌림M",Font.BOLD, 16));
        lblPath.setBounds(0,0, standardNum*6, height);
        pathPanel.add(lblPath);

        browseButton = new JButton("Browse");
        browseButton.setBorderPainted(false);
        browseButton.setBackground(Color.lightGray);
        browseButton.setFont(new Font("a이끌림M", Font.BOLD, 15));
        browseButton.setBounds(standardNum*6, 0, standardNum*2, height);
        pathPanel.add(browseButton);
    }

    // proNamePanel 설정
    public void setProNamePanel() {
        // proNamePanel 설정, 기본 x, y, width, height
        y = (int) ((standardNum*standardBlank)*2 + pathPanel.getHeight());
        width = (int) (standardNum*8);
        height = (int) (standardNum*3);

        proNamePanel = new JPanel();
        proNamePanel.setBackground(Color.white);
        proNamePanel.setBounds(x, y, width, height);
        proNamePanel.setLayout(null);
        add(proNamePanel);

        lblProName = new JLabel("<html><center>CLASS<br>REGISTRATION<br>REGISTER</center></html>");
        lblProName.setFont(new Font("a시월구일3", Font.BOLD, 30));
        lblProName.setBounds(0,0,width,height);
        lblProName.setVerticalAlignment(SwingConstants.CENTER);
        lblProName.setHorizontalAlignment(SwingConstants.CENTER);
        proNamePanel.add(lblProName);
    }

    // selectMenuPanel 설정
    public void setSelectMenuPanel() {
        // selectMenuPanel 설정, 기본 x, y, width, height
        y = (int) ((standardNum*standardBlank) + y + proNamePanel.getHeight());
        width = (int) (standardNum*8);
        height = (int) (standardNum*6);

        selectMenuPanel = new JPanel();
        selectMenuPanel.setBackground(Color.white);
        selectMenuPanel.setBounds(x, y, width, height);
        selectMenuPanel.setLayout(new GridLayout(5,1));
        add(selectMenuPanel);

        btnMenu = new JButton[5];
        for (int i = 0; i < 5; i++) {
            btnMenu[i] = new JButton(MENU[i]);
            btnMenu[i].setFont(new Font("a이끌림M", Font.BOLD, 20));
            btnMenu[i].setBorderPainted(false);
            btnMenu[i].setBackground(Color.white);
            btnMenu[i].setForeground(Color.darkGray);
            btnMenu[i].setEnabled(true);
            selectMenuPanel.add(btnMenu[i]);
        }
    }

    // univNamePanel 설정
    public void setUnivNamePanel() {
        // univNamePanel 설정, 기본 x, y, width, height
        y = (int) ((standardNum*standardBlank) + y + selectMenuPanel.getHeight());
        width = (int) (standardNum*8);
        height = (int) (standardNum*2);

        univNamePanel = new JPanel();
        univNamePanel.setBackground(Color.white);
        univNamePanel.setBounds(x, y, width, height);
        univNamePanel.setLayout(null);
        add(univNamePanel);

        universityName = new JLabel("SEJONG UNIVERSITY");
        universityName.setFont(new Font("a시월구일3", Font.BOLD, 30));
        universityName.setBounds(0,0,width,height);
        universityName.setVerticalAlignment(SwingConstants.CENTER);
        universityName.setHorizontalAlignment(SwingConstants.CENTER);
        univNamePanel.add(universityName);
    }

    public void addMenuButtonListener(ActionListener listener) {
        for (int i = 0; i < 5; i++) {
            btnMenu[i].addActionListener(listener);
        }
    }

    public void addBrowseButtonListener(ActionListener listener) {
        browseButton.addActionListener(listener);
    }

    public void setEnabledAllButton(boolean flag) {
        for (int i = 0; i < 4; i++) {
            btnMenu[i].setEnabled(flag);
        }
    }
}
