package Controller;

import Model.ClassManager;
import Model.LectureVO;
import View.InterestedFilterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.Objects;

public class InterestedFilterController {

    InterestedFilterView interestedFilter;

    //생성자
    public InterestedFilterController() {
        interestedFilter = new InterestedFilterView();
        interestedFilter.addSearchListener(new InterestedFilterController.SearchListener());
        interestedFilter.addExitButtonListener(new InterestedFilterController.ExitButtonListener());
        interestedFilter.addValueListener(new InterestedFilterController.ValueListener());
    } //public InterestedFilterController()

    public InterestedFilterView getInterestedFilterView() { return interestedFilter; }

    //Iterator 형식으로 필터를 진행
    public void filteredLecture() {
    	interestedFilter.filteredLectureList  = new ArrayList<LectureVO>();
        for (int i = 0; i < ClassManager.getInstance().getLecture().size(); i++ ) { // 개설 학과 전공 필터
            if (interestedFilter.filterMajorInfo.equals("") || ClassManager.getInstance().getLecture().get(i).major.equals(interestedFilter.filterMajorInfo))
                interestedFilter.filteredLectureList.add(ClassManager.getInstance().getLecture().get(i));
        }

        Iterator<LectureVO> iter = interestedFilter.filteredLectureList.iterator(); // 학수번호
        while (iter.hasNext()) {
            LectureVO lvo = iter.next();
            if (!(interestedFilter.filterCourseNumInfo.equals(""))) {
                if (!lvo.courseNum.equals(interestedFilter.filterCourseNumInfo)) {
                    iter.remove();
                } // if
            } // if
        } // while

        iter = interestedFilter.filteredLectureList.iterator(); // 교과목명
        while (iter.hasNext()) {
            LectureVO lvo = iter.next();
            if (!(interestedFilter.filterClassNameInfo.equals(""))) {
                if (!lvo.className.equals(interestedFilter.filterClassNameInfo)) {
                    iter.remove();
                } // if
            } // if
        } // while

        iter = interestedFilter.filteredLectureList.iterator(); // 교수명
        while (iter.hasNext()) {
            LectureVO lvo = iter.next();
            if (!(interestedFilter.filterProInfo.equals(""))) {
                if (!lvo.professor.equals(interestedFilter.filterProInfo)) {
                    iter.remove();
                } // if
            } // if
        } // while

        iter = interestedFilter.filteredLectureList.iterator(); // 학년
        while (iter.hasNext()) {
            LectureVO lvo = iter.next();
            if (interestedFilter.filterGradeInfo != -1) {
                if (lvo.grade != interestedFilter.filterGradeInfo) {
                    iter.remove();
                } // if
            } // if
        } // while

        iter = interestedFilter.filteredLectureList.iterator(); // 분반
        while (iter.hasNext()) {
            LectureVO lvo = iter.next();
            if (!(interestedFilter.filterClassNumInfo.equals(""))) {
                if (!lvo.classNum.equals(interestedFilter.filterClassNameInfo)) {
                    iter.remove();
                } // if
            } // if
        } // while

        iter = interestedFilter.filteredLectureList.iterator(); // 이수 구분
        while (iter.hasNext()) {
            LectureVO lvo = iter.next();
            if (!(interestedFilter.filterCompInfo.equals(""))) {
                if (!lvo.completion.equals(interestedFilter.filterCompInfo)) {
                    iter.remove();
                } // if
            } // if
        } // while

    } // filteredLecture

    private class ValueListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();

            if (obj == interestedFilter.cbxMajor) { // 개설학과전공
                JComboBox cbx = (JComboBox) obj;
                interestedFilter.filterMajorInfo = (String) cbx.getSelectedItem();
            } else if (obj == interestedFilter.txtCourseNum) { // 학수번호
                JTextField txt = (JTextField) obj;
                interestedFilter.filterCourseNumInfo = txt.getText();
            } else if (obj == interestedFilter.txtClassName) { // 교과목명
                JTextField txt = (JTextField) obj;
                interestedFilter.filterClassNameInfo = txt.getText();
            } else if (obj == interestedFilter.txtProfessor) { // 교수명
                JTextField txt = (JTextField) obj;
                interestedFilter.filterProInfo = txt.getText();
            } else if (obj == interestedFilter.cbxGrade) { // 학년
                JComboBox cbx = (JComboBox) obj;
                interestedFilter.filterGradeInfo = Integer.parseInt((String) Objects.requireNonNull(cbx.getSelectedItem()));
                if (interestedFilter.filterGradeInfo == 0) interestedFilter.filterGradeInfo = -1; // 필터할 때 학년 구분 없이 검색할 때 쓰기 위함
            } else if (obj == interestedFilter.txtClassNum) { // 분반
                JTextField txt = (JTextField) obj;
                interestedFilter.filterClassNumInfo = txt.getText();
            } else if (obj == interestedFilter.cbxCompletion){ // 이수 구분
                JComboBox cbx = (JComboBox) obj;
                interestedFilter.filterCompInfo = (String) cbx.getSelectedItem();
            }
        }
    }

    private class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            filteredLecture();
            ClassManager.getInstance().getLectureListController().getLLV().isFavorite = true;
            ClassManager.getInstance().getLectureListController().setSearchListAtLectureListView(interestedFilter.filteredLectureList);
			ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLectureListController().getLLV());
        }// actionPerformed
    } // SearchListener

    // ConditionListener

    private class ExitButtonListener implements ActionListener { // 나가기 버튼
        @Override
        public void actionPerformed(ActionEvent e) {
            ClassManager.getInstance().getMainMenuController().comeToMain();
            // 수강신청으로 돌아가기
        } // actionPerformed
    } // ExitButtonListener
 }
