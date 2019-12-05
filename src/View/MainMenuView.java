package View;

import common.FontConstants;

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

    private JTextField txtFilePath;
    private JButton btnBrowse;
    private ArrayList<JButton> btnMenuList;

    private final static String[] MENU = {"수강신청", "관심과목", "내 시간표", "학점 계산기", "종료"};

    public MainMenuView() {
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(410, 615));

        setPathPanel();
        setProgramName();
        try {
            setSelectMenuPanel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setUnivNamePanel();
    }

    private void setPathPanel() {
        JPanel pathPanel = new JPanel();
        pathPanel.setBackground(Color.white);
        pathPanel.setLayout(new BorderLayout());
        pathPanel.setBorder(new EmptyBorder(4, 4, 4, 4));
        pathPanel.setBounds(5, 5, 400, 40);

        txtFilePath = new JTextField("  Path ..");
        txtFilePath.setFont(new Font(FontConstants.ENGLISH_CASUAL_FONT, Font.BOLD + Font.ITALIC, 10));
        txtFilePath.setPreferredSize(new Dimension(45, 100));
        txtFilePath.setForeground(Color.LIGHT_GRAY);
        txtFilePath.setEditable(false);
        pathPanel.add(txtFilePath, BorderLayout.CENTER);

        btnBrowse = new JButton("Browse");
//        btnBrowse.setBorderPainted(false);
        btnBrowse.setBackground(Color.lightGray);
        btnBrowse.setFont(new Font(FontConstants.ENGLISH_CASUAL_FONT, Font.BOLD, 12));
        btnBrowse.setForeground(new Color(FontConstants.SIGNATURE_COLOR));
        pathPanel.add(btnBrowse, BorderLayout.EAST);

        add(pathPanel);
    }

    private void setProgramName() {
        JLabel lblProgramName = new JLabel("<html><center>CLASS<br>REGISTRATION<br>SIMULATOR</center></html>");
        lblProgramName.setBackground(Color.WHITE);
        lblProgramName.setOpaque(true);
        lblProgramName.setFont(new Font(FontConstants.ENGLISH_FORMAL_FONT, Font.BOLD, 30));
        lblProgramName.setForeground(new Color(FontConstants.SIGNATURE_COLOR));
        lblProgramName.setVerticalAlignment(SwingConstants.CENTER);
        lblProgramName.setHorizontalAlignment(SwingConstants.CENTER);
        lblProgramName.setBounds(5, 50, 400, 150);

        add(lblProgramName);
    }

    private void setSelectMenuPanel() throws IOException {
        BufferedImage srcImg = ImageIO.read(new File("Images/logo2.png"));
        Image imgLogo = srcImg.getScaledInstance(250, 250, Image.SCALE_SMOOTH);

        JPanel selectMenuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imgLogo, 75, 25, null);
            }
        };
        selectMenuPanel.setBackground(Color.WHITE);
        selectMenuPanel.setBounds(5, 205, 400, 300);
        selectMenuPanel.setLayout(new GridLayout(MENU.length,1));

        btnMenuList = new ArrayList<>();
        for (String menu : MENU) {
            JButton btnMenu = new JButton(menu);

            btnMenu.setFont(new Font(FontConstants.HANGUL_FONT, Font.BOLD, 20));
            btnMenu.setBorderPainted(false);
            btnMenu.setContentAreaFilled(false);
            btnMenu.setFocusPainted(false);
            btnMenu.setOpaque(false);
            btnMenu.setForeground(new Color(FontConstants.SIGNATURE_COLOR));

            if (!menu.equals("종료"))
                btnMenu.setEnabled(false);

            selectMenuPanel.add(btnMenu);
            btnMenuList.add(btnMenu);
        }

        add(selectMenuPanel);
    }

    private void setUnivNamePanel() {
        JLabel lblUnivName = new JLabel("SEJONG UNIVERSITY");
        lblUnivName.setBackground(Color.WHITE);
        lblUnivName.setOpaque(true);
        lblUnivName.setFont(new Font(FontConstants.ENGLISH_FORMAL_FONT, Font.BOLD, 30));
        lblUnivName.setForeground(new Color(FontConstants.SIGNATURE_COLOR));
        lblUnivName.setVerticalAlignment(SwingConstants.CENTER);
        lblUnivName.setHorizontalAlignment(SwingConstants.CENTER);
        lblUnivName.setBounds(5, 510, 400, 100);

        add(lblUnivName);
    }

    public void addMenuButtonListener(EventListener listener) {
        for (JButton btnMenu : btnMenuList) {
            btnMenu.addActionListener((ActionListener)listener);
            btnMenu.addMouseListener((MouseListener)listener);
        }
    }

    public void addBrowseButtonListener(EventListener listener) {
        btnBrowse.addActionListener((ActionListener)listener);
        btnBrowse.addMouseListener((MouseListener)listener);
    }

    public void setEnabledAllButton(boolean flag) {
        for (JButton btnMenu : btnMenuList)
            btnMenu.setEnabled(flag);
    }

    public JTextField getTxtFilePath() {
        return txtFilePath;
    }
}
