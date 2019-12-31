package Controller;

import common.ClassManager;
import View.FilterView;
import common.Constants;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private void searchFunction(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.MAJOR_TXT,filterView.getMajor());
        jsonObject.put(Constants.COURSENUM_TXT,filterView.getCourseNum());
        jsonObject.put(Constants.CLASSNAME_TXT,filterView.getClassName());
        jsonObject.put(Constants.PROFESSOR_TXT,filterView.getProfessor());
        jsonObject.put(Constants.GRADE_TXT,filterView.getGrade());
        jsonObject.put(Constants.CLASSNUM_TXT,filterView.getClassNum());
        jsonObject.put(Constants.COMPLETION_TXT,filterView.getCompletion());
        ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLectureListView(jsonObject));
    }
} // RealFilterController Class
