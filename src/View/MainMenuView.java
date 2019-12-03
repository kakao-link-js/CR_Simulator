package View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenuView extends JPanel {
    private JPanel pathPanel;       // 경로 Panel
    private JPanel proNamePanel;    // 프로그램 이름 Panel
    private JPanel selectMenuPanel; // 메뉴 Panel
    private JPanel univNamePanel;   // 대학교 이름 Panel

    public JLabel lblPath;
    public JButton[] btnMenus;

    private JLabel lblProName;
    private JLabel lblUnivName;
    private JButton browseButton;

    private final static String[] MENU = {"수강신청", "관심과목", "내 시간표", "학점 계산기", "종료"};

    // TODO :: Modify using layout
    private int x, y, width, height;
    private int standardNum = 50;
    private double standardBlank = 0.1;

    public MainMenuView() {
        setLayout(null);
        setPreferredSize(new Dimension((int)(standardNum * 8.2), (int)(standardNum * 12.5)));
        setBackground(Color.lightGray);

        setPathPanel();
        setProNamePanel();
        setSelectMenuPanel();
        setUnivNamePanel();
    }

    private void setPathPanel() {
        x = (int) (standardNum*standardBlank);
        y = (int) (standardNum*standardBlank);
        width = (int) (standardNum*8);
        height = (int) (standardNum);

        pathPanel = new JPanel();
        pathPanel.setBackground(Color.white);
        pathPanel.setBounds(x, y, width, height);
        pathPanel.setLayout(null);

        lblPath = new JLabel("PATH : ") ;
        lblPath.setFont(new Font("a이끌림M",Font.BOLD, 16));
        lblPath.setBounds(0,0, standardNum*6, height);
        pathPanel.add(lblPath);

        browseButton = new JButton("Browse");
        browseButton.setBorderPainted(false);
        browseButton.setBackground(Color.lightGray);
        browseButton.setFont(new Font("a이끌림M", Font.BOLD, 15));
        browseButton.setBounds(standardNum*6, 0, standardNum*2, height);
        pathPanel.add(browseButton);

        add(pathPanel);
    }

    private void setProNamePanel() {
        y = (int) ((standardNum*standardBlank) * 2 + pathPanel.getHeight());
        width = (int) (standardNum*8);
        height = (int) (standardNum*3);

        proNamePanel = new JPanel();
        proNamePanel.setBackground(Color.white);
        proNamePanel.setBounds(x, y, width, height);
        proNamePanel.setLayout(null);

        lblProName = new JLabel("<html><center>CLASS<br>REGISTRATION<br>REGISTER</center></html>");
        lblProName.setFont(new Font("a시월구일3", Font.BOLD, 30));
        lblProName.setBounds(0,0,width,height);
        lblProName.setVerticalAlignment(SwingConstants.CENTER);
        lblProName.setHorizontalAlignment(SwingConstants.CENTER);
        proNamePanel.add(lblProName);

        add(proNamePanel);
    }

    private void setSelectMenuPanel() {
        // selectMenuPanel 설정, 기본 x, y, width, height
        y = (int) ((standardNum*standardBlank) + y + proNamePanel.getHeight());
        width = (int) (standardNum*8);
        height = (int) (standardNum*6);

        selectMenuPanel = new JPanel();
        selectMenuPanel.setBackground(Color.white);
        selectMenuPanel.setBounds(x, y, width, height);
        selectMenuPanel.setLayout(new GridLayout(5,1));

        btnMenus = new JButton[5];
        for (int i = 0; i < 5; i++) {
            btnMenus[i] = new JButton(MENU[i]);
            // TODO :: Modify using system font
            btnMenus[i].setFont(new Font("a이끌림M", Font.BOLD, 20));
            btnMenus[i].setBorderPainted(false);
            btnMenus[i].setBackground(Color.white);
            btnMenus[i].setForeground(Color.darkGray);
            btnMenus[i].setEnabled(false);
            selectMenuPanel.add(btnMenus[i]);
        }
        btnMenus[4].setEnabled(true);

        add(selectMenuPanel);
    }

    private void setUnivNamePanel() {
        y = (int) ((standardNum*standardBlank) + y + selectMenuPanel.getHeight());
        width = (int) (standardNum*8);
        height = (int) (standardNum*2);

        univNamePanel = new JPanel();
        univNamePanel.setBackground(Color.white);
        univNamePanel.setBounds(x, y, width, height);
        univNamePanel.setLayout(null);

        lblUnivName = new JLabel("SEJONG UNIVERSITY");
        lblUnivName.setFont(new Font("a시월구일3", Font.BOLD, 30));
        lblUnivName.setBounds(0,0,width,height);
        lblUnivName.setVerticalAlignment(SwingConstants.CENTER);
        lblUnivName.setHorizontalAlignment(SwingConstants.CENTER);
        univNamePanel.add(lblUnivName);

        add(univNamePanel);
    }

    public void addMenuButtonListener(ActionListener listener) {
        for (int i = 0; i < 5; i++)
            btnMenus[i].addActionListener(listener);
    }

    public void addBrowseButtonListener(ActionListener listener) {
        browseButton.addActionListener(listener);
    }

    public void setEnabledAllButton(boolean flag) {
        for (int i = 0; i < 4; i++)
            btnMenus[i].setEnabled(flag);
    }
}
