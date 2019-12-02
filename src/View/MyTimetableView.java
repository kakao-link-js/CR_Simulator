package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;

public class MyTimetableView extends JPanel {

    private JPanel titlePanel;
    private JScrollPane timetablePanel;

    private JButton btnBack;
    private JLabel lblTitle;

    private JTable timetable;
    private DefaultTableModel timetableModel;

    private final String[] HEADER = {"", "월", "화", "수", "목", "금"};
    private final String[][] ROW_TIME = {{"9"}, {""}, {"10"}, {""},
                                        {"11"}, {""}, {"12"}, {""},
                                        {"1"}, {""}, {"2"}, {""},
                                        {"3"}, {""}, {"4"}, {""},
                                        {"5"}, {""}, {"6"}, {""},
                                        {"7"}, {""}, {"8"}};

    public MyTimetableView () {
        setLayout(new BorderLayout());

        setTitlePanel();
        setTimetablePanel();

        setVisible(true);
    }

    private void setTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(500, 80));
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        btnBack = new JButton("<");
        btnBack.setPreferredSize(new Dimension(35, 0));
        titlePanel.add(btnBack, BorderLayout.WEST);

        lblTitle = new JLabel("TIME TABLE");
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 25));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(lblTitle, BorderLayout.CENTER);

        add(titlePanel, BorderLayout.NORTH);
    }

    private void setTimetablePanel() {
        timetableModel = new DefaultTableModel(ROW_TIME, HEADER);

        timetable = new JTable(timetableModel);
        timetable.setGridColor(Color.BLACK);
        timetable.setBackground(Color.WHITE);
        timetable.getColumnModel().getColumn(0).setPreferredWidth(5);
        timetable.setRowHeight(25);
        timetable.setEnabled(false);

        setCellsAlignment(SwingConstants.CENTER);

        timetable.getTableHeader().setReorderingAllowed(false);
        timetable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 11));
        timetable.getTableHeader().setBackground(Color.WHITE);

        timetablePanel = new JScrollPane(timetable);
        timetablePanel.setPreferredSize(new Dimension(500, 596));

        add(timetablePanel, BorderLayout.WEST);
    }

    private void setCellsAlignment(int alignment) {
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer)timetable.getTableHeader().getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(alignment);
        timetable.getTableHeader().setDefaultRenderer(headerRenderer);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(alignment);

        for (int columnIndex = 0; columnIndex < timetableModel.getColumnCount(); ++columnIndex)
            timetable.getColumnModel().getColumn(columnIndex).setCellRenderer(cellRenderer);
    }
}
