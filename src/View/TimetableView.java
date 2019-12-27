package View;

import Controller.TimetableController;
import common.Constants;
import common.DesignConstants;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.EventListener;

/**
 * TimetableView Class
 * 시간표 화면 출력 및 인쇄 컴포넌트들을 구성하고 있는 사용자 정의 패널 View 클래스
 *
 * @author 이종진
 */
public class TimetableView extends JPanel {
    private JButton btnBack;
    private JButton btnPrint;
    private JTable timetable;

    private ImageIcon imgDefaultPrintButton;
    private ImageIcon imgHoveringPrintButton;

    private TimetableController timetableController;

    /**
     * TimetableView Constructor
     * 레이아웃 설정과 내부 컴포넌트를 구성하는 메소드를 호출하고 화면에 출력
     */
    public TimetableView() {
        timetableController = new TimetableController(this);

        setLayout(new BorderLayout());
        addAncestorListener(timetableController);

        setTitlePanel();
        setTimetablePanel();

        setVisible(true);
    } // Constructor

    /**
     * setTitlePanel Method
     * 하나의 패널(titlePanel)안에 메뉴이동 버튼, 제목, 인쇄 버튼 컴포넌트를 구성
     */
    private void setTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(500, 80));
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(new EmptyBorder(20, 15, 20, 15));

        btnBack = new JButton(Constants.BACK_TXT);
        btnBack.setPreferredSize(new Dimension(50, 0));
        btnBack.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 15));
        btnBack.setForeground(new Color(DesignConstants.SIGNATURE_COLOR));
        btnBack.setContentAreaFilled(false);
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(timetableController);
        titlePanel.add(btnBack, BorderLayout.WEST);

        JLabel lblTitle = new JLabel("시 간 표");
        lblTitle.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 25));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(DesignConstants.SIGNATURE_COLOR));
        titlePanel.add(lblTitle, BorderLayout.CENTER);

        imgDefaultPrintButton = new ImageIcon("Images/printer1.png");
        imgHoveringPrintButton = new ImageIcon("Images/printer2.png");

        btnPrint = new JButton(imgDefaultPrintButton);
        btnPrint.setPreferredSize(new Dimension(50, 0));
        btnPrint.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 15));
        btnPrint.setForeground(new Color(DesignConstants.SIGNATURE_COLOR));
        btnPrint.setContentAreaFilled(false);
        btnPrint.setFocusPainted(false);
        btnPrint.addActionListener(timetableController);
        titlePanel.add(btnPrint, BorderLayout.EAST);

        add(titlePanel, BorderLayout.NORTH); // 메인 패널(this) 상단에 titlePanel 추가
    } // setTitlePanel()

    /**
     * setTimetablePanel Method
     * 시간표를 구성하는 패널 설정 및 추가
     */
    private void setTimetablePanel() {

        timetable = new JTable(new DefaultTableModel(23, 5));
        timetable.setBackground(Color.WHITE);
        timetable.setGridColor(Color.GRAY);
        timetable.setRowHeight(25);
        timetable.setEnabled(false);

        setHeaderRenderer();

        timetable.setDefaultRenderer(Object.class, new customCellRenderer());

        JScrollPane timetablePanel = new JScrollPane(timetable);
        timetablePanel.setPreferredSize(new Dimension(500, 596));

        add(timetablePanel, BorderLayout.WEST);
    } // setTimetablePanel()

    /**
     * setHeaderRenderer Method
     * 시간표 JTable의 헤더 렌더링 설정
     */
    private void setHeaderRenderer() {
        JTableHeader tableHeader = timetable.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setFont(new Font(DesignConstants.HANGUL_FONT, Font.PLAIN, 10));
        tableHeader.setBackground(Color.WHITE);
        tableHeader.setForeground(Color.DARK_GRAY);

        JLabel headerRenderer = (JLabel)tableHeader.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    } // setHeaderRenderer()

    /**
     * setHoveringPrintIcon Method
     * 프린트 버튼 Hovering 이미지 설정
     *
     * @param status : boolean
     */
    public void setHoveringPrintIcon(boolean status) {
        if (status)
            btnPrint.setIcon(imgHoveringPrintButton);
        else
            btnPrint.setIcon(imgDefaultPrintButton);
    }

    public JTable getTable() {
        return timetable;
    }

    /**
     * customCellRenderer Class
     * JTable의 사용자 정의 Renderer 구현 클래스
     */
    public static class customCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            JLabel cell = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

            // value값, 즉 데이터가 Cell안에 존재할 경우
            if (value != null) {
                // Row Header
                if (col == 0) {
                    cell.setBackground(Color.WHITE);
                    cell.setHorizontalAlignment(SwingConstants.RIGHT);
                    cell.setVerticalAlignment(SwingConstants.TOP);
                }
                // 실제 시간표 정보를 담고 있는 Cell
                else {
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
