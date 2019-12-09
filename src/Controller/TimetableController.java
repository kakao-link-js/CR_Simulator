package Controller;

import Model.ClassManager;
import Model.LectureVO;
import View.TimetableView;
import common.DesignConstants;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimetableController {

    private final String[] HEADER = {"", "월", "화", "수", "목", "금"};
    private final String[][] ROW_TIME = {{"9"}, {""}, {"10"}, {""},
            {"11"}, {""}, {"12"}, {""},
            {"1"}, {""}, {"2"}, {""},
            {"3"}, {""}, {"4"}, {""},
            {"5"}, {""}, {"6"}, {""},
            {"7"}, {""}, {"8"}};

    public TimetableController() {
        getTimetableView().addBackButtonListener(new BackButtonListener());
        getTimetableView().addPrintButtonListener(new PrintButtonListener());
        getTimetableView().addAncestorListener(new ViewComponentListener());
    } // Constructor

    public TimetableView getTimetableView() {
        return ClassManager.getInstance().getTimetableView();
    } // getTimetableView()

    private void drawTimetable() {
        getTimetableView().getTable().setModel(new DefaultTableModel(ROW_TIME, HEADER));
        getTimetableView().getTable().getColumnModel().getColumn(0).setPreferredWidth(5);

        ArrayList<LectureVO> lectureList =  ClassManager.getInstance().getReal();

        for (LectureVO lecture : lectureList) {
            String strWeekAndTime = lecture.time;

            if (strWeekAndTime.isEmpty())
                continue;

            String[] strSplitWeekAndTimeList = strWeekAndTime.split(",");

            for (String strSplitWeekAndTime : strSplitWeekAndTimeList) {
                List<String> strSplitWeekList = new ArrayList<>(Arrays.asList(strSplitWeekAndTime.split(" ")));
                int listSize = strSplitWeekList.size();

                if (listSize < 2)
                    continue;

                String time = strSplitWeekList.get(listSize - 1);
                strSplitWeekList.remove(listSize - 1);

                for (String week : strSplitWeekList)
                    fillTimetableCell(lecture.className, lecture.classRoom, week, time);
            } // foreach
        } // foreach
    } // drawTimetable()

    private void fillTimetableCell(final String className, final String classRoom, final String strWeek, final String strTime) {
        class TimeModel {
            int hour;
            int minute;

            TimeModel(String time) {
                String[] splitTime = time.split(":");
                hour = Integer.parseInt(splitTime[0]);
                minute = Integer.parseInt(splitTime[1]);
            } // Constructor

            int isHalf() { return minute > 0 ? 1 : 0; }
        } // TimeModel Inner Class

        String[] splitTimes = strTime.split("~");
        TimeModel startTime = new TimeModel(splitTimes[0]);
        TimeModel endTime = new TimeModel(splitTimes[1]);

        int startRowIndex = (startTime.hour - 9) * 2 + startTime.isHalf();
        int endRowIndex = (endTime.hour - 9) * 2 + endTime.isHalf();

        JTable table = getTimetableView().getTable();
        TableColumn column = table.getColumn(strWeek);
        int columnIndex = column.getModelIndex();

        table.setValueAt(className, startRowIndex, columnIndex);
        table.setValueAt(classRoom, startRowIndex + 1, columnIndex);

        for (int i = startRowIndex + 2; i <= endRowIndex; ++i) {
            table.setValueAt(" ", i, columnIndex);
        } // for
    } // fillTimetableCell

    private class ViewComponentListener implements AncestorListener {
        @Override
        public void ancestorAdded(AncestorEvent event) {
            drawTimetable();
        } // ancestorAdded()

        @Override
        public void ancestorRemoved(AncestorEvent event) { }
        @Override
        public void ancestorMoved(AncestorEvent event) { }
    } // ViewComponentListener Class

    private static class BackButtonListener implements ActionListener, MouseListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClassManager.getInstance().getMainMenuController().comeToMain();
        } // actionPerformed()

        @Override
        public void mouseEntered(MouseEvent e) {
            JButton btnEvent = (JButton)e.getSource();
            btnEvent.setForeground(new Color(DesignConstants.HOVERING_COLOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton btnEvent = (JButton)e.getSource();
            btnEvent.setForeground(new Color(DesignConstants.SIGNATURE_COLOR));
        }

        @Override
        public void mouseClicked(MouseEvent e) { }
        @Override
        public void mousePressed(MouseEvent e) { }
        @Override
        public void mouseReleased(MouseEvent e) { }
    } // BackButtonListener Class

    public class PrintButtonListener implements ActionListener, MouseListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                getTimetableView().getTable().print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(getTimetableView(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            getTimetableView().setHoveringPrintIcon(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            getTimetableView().setHoveringPrintIcon(false);
        }

        @Override
        public void mouseClicked(MouseEvent e) { }
        @Override
        public void mousePressed(MouseEvent e) { }
        @Override
        public void mouseReleased(MouseEvent e) { }
    }
} // TimetableController Class
