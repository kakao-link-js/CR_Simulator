package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MyTimetableView extends JFrame {

    private JPanel titlePanel = new JPanel();
    private JScrollPane timetablePanel;

    private JButton btnBack;
    private JLabel lblTitle;

    private JTable tblTimeTable;
    private DefaultTableModel dtmTimetable;

    private final String[] HEADER = {"", "월", "화", "수", "목", "금"};
    private String[][] ROW_TIME = {{"9"}, {""}, {"10"}, {""},
                                        {"11"}, {""}, {"12"}, {""},
                                        {"1"}, {""}, {"2"}, {""},
                                        {"3"}, {""}, {"4"}, {""},
                                        {"5"}, {""}, {"6"}, {""},
                                        {"7"}, {""}, {"8"}};

    public MyTimetableView () {

        super("MY TIMETABLE");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setResizable(false); // 크기 조정 안함

        // Title Panel Setting
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(500, 80));
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
        dtmTimetable = new DefaultTableModel(ROW_TIME, HEADER);

        tblTimeTable = new JTable(dtmTimetable);
        tblTimeTable.setGridColor(Color.BLACK);
        tblTimeTable.setBackground(Color.WHITE);
        tblTimeTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblTimeTable.setRowHeight(25);
        tblTimeTable.getTableHeader().setReorderingAllowed(false);
        tblTimeTable.setEnabled(false);
        tblTimeTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 11));

        timetablePanel = new JScrollPane(tblTimeTable);
        timetablePanel.setPreferredSize(new Dimension(500, 596));

        add(timetablePanel, BorderLayout.WEST);

        pack();
        setVisible(true);
    }
}
