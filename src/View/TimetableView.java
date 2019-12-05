package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TimetableView extends JPanel {
    private JButton btnBack;
    private JTable timetable;

    private final String[] HEADER = {"", "월", "화", "수", "목", "금"};
    private final String[][] ROW_TIME = {{"9"}, {""}, {"10"}, {""},
                                        {"11"}, {""}, {"12"}, {""},
                                        {"1"}, {""}, {"2"}, {""},
                                        {"3"}, {""}, {"4"}, {""},
                                        {"5"}, {""}, {"6"}, {""},
                                        {"7"}, {""}, {"8"}};

    public TimetableView() {
        setLayout(new BorderLayout());

        setTitlePanel();
        setTimetablePanel();

        setVisible(true);
    }

    private void setTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(500, 80));
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        btnBack = new JButton("<");
        btnBack.setPreferredSize(new Dimension(35, 0));
        titlePanel.add(btnBack, BorderLayout.WEST);

        JLabel lblTitle = new JLabel("TIME TABLE");
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 25));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(lblTitle, BorderLayout.CENTER);

        add(titlePanel, BorderLayout.NORTH);
    }

    private void setTimetablePanel() {

        timetable = new JTable(new DefaultTableModel(ROW_TIME, HEADER));
        timetable.setBackground(Color.WHITE);
        timetable.setGridColor(Color.GRAY);
        timetable.getColumnModel().getColumn(0).setPreferredWidth(5);
        timetable.setRowHeight(25);
        timetable.setEnabled(false);

        setHeaderRenderer();

        timetable.setDefaultRenderer(Object.class, new customCellRenderer());

        JScrollPane timetablePanel = new JScrollPane(timetable);
        timetablePanel.setPreferredSize(new Dimension(500, 596));

        add(timetablePanel, BorderLayout.WEST);
    }

    private void setHeaderRenderer() {
        JTableHeader tableHeader = timetable.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setFont(new Font("Verdana", Font.PLAIN, 10));
        tableHeader.setBackground(Color.WHITE);

        JLabel headerRenderer = (JLabel)tableHeader.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void addBackButtonListener(ActionListener listener) {
        btnBack.addActionListener(listener);
    }

    public JTable getTable() {
        return timetable;
    }

    public static class customCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            JLabel cell = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

            if (value != null) {
                cell.setHorizontalAlignment(SwingConstants.CENTER);
                cell.setFont(new Font("Verdana", Font.BOLD, 10));

                if (col != 0)
                    cell.setBackground(Color.PINK);
            }
            else
                cell.setBackground(Color.WHITE);

            return cell;
        }
    }
}
