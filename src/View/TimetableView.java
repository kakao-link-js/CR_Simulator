package View;

import common.DesignConstants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.util.EventListener;

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
    } // Constructor

    private void setTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(500, 80));
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(new EmptyBorder(20, 15, 20, 15));

        btnBack = new JButton("<");
        btnBack.setPreferredSize(new Dimension(50, 0));
        btnBack.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 15));
        btnBack.setForeground(new Color(DesignConstants.SIGNATURE_COLOR));
//        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
//        btnBack.setFocusPainted(false);
//        btnBack.setOpaque(false);
        titlePanel.add(btnBack, BorderLayout.WEST);

        JLabel lblTitle = new JLabel("시 간 표");
        lblTitle.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 25));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(DesignConstants.SIGNATURE_COLOR));
        titlePanel.add(lblTitle, BorderLayout.CENTER);

        // TODO :: add feature to print

        JButton btnPrint = new JButton("print");
        btnPrint.setPreferredSize(new Dimension(50, 0));
        btnPrint.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 15));
        btnPrint.setForeground(new Color(DesignConstants.SIGNATURE_COLOR));
//        btnBack.setBorderPainted(false);
        btnPrint.setContentAreaFilled(false);
//        btnBack.setFocusPainted(false);
//        btnBack.setOpaque(false);
        titlePanel.add(btnPrint, BorderLayout.EAST);

        add(titlePanel, BorderLayout.NORTH);
    } // setTitlePanel()

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
    } // setTimetablePanel()

    private void setHeaderRenderer() {
        JTableHeader tableHeader = timetable.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setFont(new Font(DesignConstants.HANGUL_FONT, Font.PLAIN, 10));
        tableHeader.setBackground(Color.WHITE);
        tableHeader.setForeground(Color.DARK_GRAY);

        JLabel headerRenderer = (JLabel)tableHeader.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    } // setHeaderRenderer()

    public void addBackButtonListener(EventListener listener) {
        btnBack.addActionListener((ActionListener)listener);
        btnBack.addMouseListener((MouseListener)listener);
    }

    public JTable getTable() {
        return timetable;
    }

    public class customCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            JLabel cell = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

            if (value != null) {
                if (col == 0) {
                    cell.setBackground(Color.WHITE);
                    cell.setHorizontalAlignment(SwingConstants.RIGHT);
                    cell.setVerticalAlignment(SwingConstants.TOP);
                } else {
                    cell.setBackground(new Color(DesignConstants.TIMETABLE_CELL_COLOR));
                    cell.setHorizontalAlignment(SwingConstants.LEFT);
                    cell.setVerticalAlignment(SwingConstants.CENTER);
                }

                cell.setFont(new Font(DesignConstants.HANGUL_FONT, Font.PLAIN, 10));
                cell.setForeground(Color.DARK_GRAY);
            }
            else
                cell.setBackground(Color.WHITE);

            return cell;
        } // getTableCellRendererComponent()
    } // customCellRenderer Class
} // TimetableView Class