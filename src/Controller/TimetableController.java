package Controller;

import Model.ClassManager;
import Model.LectureVO;
import View.TimetableView;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class TimetableController {

    public TimetableController() {
        getTimetableView().addBackButtonListener(new BackButtonListener());
        getTimetableView().addComponentListener(new ViewComponentListener());
    } // Constructor

    public TimetableView getTimetableView() {
        return ClassManager.getInstance().getTimetableView();
    } // getTimetableView()

    private void drawTimetale() {
        ArrayList<LectureVO> lectureList =  ClassManager.getInstance().getReal();

        fillTimetableCell("선형대수", "센 B107", "수", "10:00~15:30");

//        for (LectureVO lecture : lectureList) {
//            String strTime = lecture.time;
//
//            if (strTime.isEmpty())
//                continue;
//
//            String[] splitTimes = strTime.split(",");
//
//            if (splitTimes.length <= 1) {
//
//            } else {
//
//            }
//        }
    }

    private void fillTimetableCell(final String className, final String classRoom, final String strWeek, final String strTime) {
        class TimeModel {
            int hour;
            int minute;

            TimeModel(String time) {
                String[] splitTime = time.split(":");
                hour = Integer.parseInt(splitTime[0]);
                minute = Integer.parseInt(splitTime[1]);
            }

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
        }
    }

    private boolean isDivisible(String str) {
        return str.split(" ").length > 1;
    }

    private class ViewComponentListener implements java.awt.event.ComponentListener {

        @Override
        public void componentShown(ComponentEvent e) {
            drawTimetale();
        }

        @Override
        public void componentHidden(ComponentEvent e) { }
        @Override
        public void componentResized(ComponentEvent e) { }
        @Override
        public void componentMoved(ComponentEvent e) { }
    }

    private static class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ClassManager.getInstance().getMainMenuController().comeToMain();
        } // actionPerformed()
    } // BackButtonListener Class
} // TimetableController Class
