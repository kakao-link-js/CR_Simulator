package View;

import common.FontConstants;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class MainMenuView extends JPanel {
    private JPanel pathPanel;       // 경로 Panel
    private JPanel proNamePanel;    // 프로그램 이름 Panel
    private JPanel selectMenuPanel; // 메뉴 Panel
    private JPanel univNamePanel;   // 대학교 이름 Panel

    public JLabel lblPath;
    private JTextField txtFilePath;
    private JButton btnBrowse;

    private JLabel lblProName;
    private JLabel lblUnivName;
    public JButton[] btnMenus;

    private final static String[] MENU = {"수강신청", "관심과목", "내 시간표", "학점 계산기", "종료"};

    // TODO :: Modify using layout
    private int x, y, width, height;
    private int standardNum = 50;
    private double standardBlank = 0.1;

    public MainMenuView() {
        setLayout(new BorderLayout());
        setBackground(Color.lightGray);
//        setPreferredSize(new Dimension((int)(standardNum * 8.2), (int)(standardNum * 12.5)));

        setPathPanel();
        setProNamePanel();
        setSelectMenuPanel();
        setUnivNamePanel();
    }

    private void setPathPanel() {
//        x = (int) (standardNum*standardBlank);
//        y = (int) (standardNum*standardBlank);
//        width = (int) (standardNum*8);
//        height = (int) (standardNum);

        pathPanel = new JPanel();
        pathPanel.setBackground(Color.white);
        pathPanel.setLayout(new BorderLayout());
        pathPanel.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));
        pathPanel.setPreferredSize(new Dimension(400, 30));

        lblPath = new JLabel("PATH : ") ;
        lblPath.setFont(new Font(FontConstants.ENGLISH_CASUAL_FONT, Font.BOLD, 12));
        lblPath.setBounds(0,0, standardNum*6, height);
        pathPanel.add(lblPath, BorderLayout.WEST);

        txtFilePath = new JTextField();
        pathPanel.add(txtFilePath, BorderLayout.CENTER);

        btnBrowse = new JButton("Browse");
//        btnBrowse.setBorderPainted(false);
        btnBrowse.setBackground(Color.lightGray);
        btnBrowse.setFont(new Font(FontConstants.ENGLISH_CASUAL_FONT, Font.BOLD, 12));
        btnBrowse.setBounds(standardNum*6, 0, standardNum*2, height);
        pathPanel.add(btnBrowse, BorderLayout.EAST);

        add(pathPanel, BorderLayout.NORTH);
    }

    private void setProNamePanel() {
        y = (int) ((standardNum*standardBlank) * 2 + pathPanel.getHeight());
        width = (int) (standardNum*8);
        height = (int) (standardNum*3);

        proNamePanel = new JPanel();
        proNamePanel.setBackground(Color.white);
        proNamePanel.setBounds(x, y, width, height);
        proNamePanel.setLayout(new BorderLayout());

        lblProName = new JLabel("<html><center>CLASS<br>REGISTRATION<br>REGISTER</center></html>");
        lblProName.setFont(new Font(FontConstants.ENGLISH_FORMAL_FONT, Font.BOLD, 30));
        lblProName.setForeground(new Color(FontConstants.SIGNATURE_COLOR));
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
            btnMenus[i].setFont(new Font(FontConstants.HANGUL_FONT, Font.BOLD, 20));
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
        lblUnivName.setFont(new Font(FontConstants.ENGLISH_FORMAL_FONT, Font.BOLD, 30));
        lblUnivName.setForeground(new Color(FontConstants.SIGNATURE_COLOR));
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
        btnBrowse.addActionListener(listener);
    }

    public void setEnabledAllButton(boolean flag) {
        for (int i = 0; i < 4; i++)
            btnMenus[i].setEnabled(flag);
    }
}
