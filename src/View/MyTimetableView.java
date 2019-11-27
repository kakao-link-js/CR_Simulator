package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyTimetableView extends JFrame {

    private JPanel titlePanel = new JPanel();
    private JPanel timetablePanel = new JPanel();

    private JButton btnBack;
    private JLabel lblTitle;

    private JTable tblTimeTable;


    public MyTimetableView () {

        super("MY TIMETABLE");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setResizable(false); // 크기 조정 안함

        // Title Panel Setting
        titlePanel.setBackground(Color.PINK);
        titlePanel.setPreferredSize(new Dimension(600, 80));
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        btnBack = new JButton("<");
        btnBack.setPreferredSize(new Dimension(35, 0));
        titlePanel.add(btnBack, BorderLayout.WEST);

        lblTitle = new JLabel("TIME TABLE");
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 25));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(lblTitle, BorderLayout.CENTER);

        add(titlePanel, BorderLayout.NORTH);

        // Timetable Panel Setting
        timetablePanel.setBackground(Color.PINK);
        timetablePanel.setPreferredSize(new Dimension(600, 620));
        timetablePanel.setLayout(new BorderLayout());

        tblTimeTable = new JTable(13, 6);
        timetablePanel.add(tblTimeTable, BorderLayout.CENTER);

        add(timetablePanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }
}
