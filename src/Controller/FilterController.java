package Controller;

import common.ClassManager;
import View.FilterView;
import common.Constants;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FilterController Class
 * Filter후 강의를 출력하는 클래스입니다.
 */

public class FilterController implements ActionListener {
    private FilterView filterView;

    public FilterController(FilterView realFilter)  {
        this.filterView = realFilter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        String operator = ((JButton)obj).getText();
        switch (operator){
            case Constants.SEARCH_TXT:
                searchFunction();
                break;
            case Constants.EXIT_TXT:
                ClassManager.getInstance().getMain().comeToMain();
                break;
        }
    }

    //필터를 정리 후 서치를 하는 메소드
    private void searchFunction(){
        JSONObject jsonObject = new JSONObject();
        if(!filterView.getMajor().isEmpty())
            jsonObject.put(Constants.MAJOR_TXT,filterView.getMajor());
        if(!filterView.getCourseNum().isEmpty())
            jsonObject.put(Constants.COURSENUM_TXT,filterView.getCourseNum());
        if(!filterView.getClassName().isEmpty())
            jsonObject.put(Constants.CLASSNAME_TXT,filterView.getClassName());
        if(!filterView.getProfessor().isEmpty())
            jsonObject.put(Constants.PROFESSOR_TXT,filterView.getProfessor());
        if(!filterView.getGrade().isEmpty())
            jsonObject.put(Constants.GRADE_TXT,filterView.getGrade());
        if(!filterView.getClassNum().isEmpty())
            jsonObject.put(Constants.CLASSNUM_TXT,filterView.getClassNum());
        if(!filterView.getCompletion().isEmpty())
            jsonObject.put(Constants.COMPLETION_TXT,filterView.getCompletion());
        ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLectureListView(jsonObject));
    }
} // RealFilterController Class
