package Controller;

import Model.ClassManager;
import View.FilterView;
import common.Constants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        ArrayList<String> temp = filterView.getSelect();
        ClassManager.getInstance().getMain().changePanel(ClassManager.getInstance().getLectureListView());
    }
} // RealFilterController Class
