package View;

import Controller.FilterController;
import common.*;

import javax.swing.*;
import java.awt.*;

/**
 * FilterView Class
 * Filter View를 갖는 Class 비즈니스 로직은 없다.
 */

public class FilterView extends JPanel {
    FilterController filterController;

    JPanel titlePanel; // 나가기 버튼 및 현재 페이지의 이름이 있는 Panel
    JPanel filterPanel; // 수강신청 분류 조건 Panel

    // setBounds를 위한 변수들
    private int x, y, width, height;
    private int standardNum = 50;
    private double standardBlank = 0.1;

    public JTextField txtCourseNum; // 학수번호
    public JTextField txtClassName; // 교과목명
    public JTextField txtProfessor; // 교수님 성함
    public JTextField txtClassNum; // 분반

    // 콤보 박스
    public JComboBox<String> cbxMajor; // 전공
    public JComboBox<String> cbxGrade; // 학년
    public JComboBox<String> cbxCompletion; // 이수 구분

    public JButton btnSearch; // 검색 버튼
    public JButton btnExit; // 나가기 버튼

    JLabel lblMajor; // 개설학과전공
    JLabel lblCourseNum; // 학수번호
    JLabel lblClassName; // 교과목명
    JLabel lblProfessor; // 교수님 성함
    JLabel lblClassNum; // 분반
    JLabel lblGrade; // 학년
    JLabel lblCompletion; // 이수 구분
    JLabel lblTitle; // 제목

    public FilterView() { // 패널 설정
        filterController = new FilterController(this);

        setBackground(Color.lightGray);
        setPreferredSize(new Dimension((int)(standardNum*8.2),(int)(standardNum*10.8)));
        setLayout(null);

        setTitlePanel(); // 상단바 패널 설정
        setFilterPanel(); // 필터 패널 설정
    }

    public void setTitlePanel() { // 상단바 패널 설정
        x = (int) (standardBlank*standardNum); // 비율에 따라 x,y,width, height 설정
        y = (int) (standardBlank*standardNum);
        width = standardNum*8;
        height = (int) (standardNum * 1.6);

        // 패널 및 버튼 및 라벨 설정
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        titlePanel.setBounds(x, y, width, height);
        titlePanel.setLayout(null);
        add(titlePanel);

        btnExit = new JButton(Constants.EXIT_TXT);
        btnExit.setFont(new Font(DesignConstants.HANGUL_FONT,Font.PLAIN, 14));
        btnExit.setBounds(0,0,(int)(standardNum*2),height);
        btnExit.setBackground(Color.white);
        btnExit.setBorderPainted(false);
        titlePanel.add(btnExit);
        btnExit.addActionListener(filterController);

        lblTitle = new JLabel("수강신청 필터");
        lblTitle.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 28));
        lblTitle.setBounds((int)(x+(standardNum*2)),y,standardNum*5,height);
        titlePanel.add(lblTitle);
    }

    public void setFilterPanel() { // 필터 패널 설정
        y = (int)((standardNum*standardBlank) + titlePanel.getHeight());
        width = standardNum*8;
        height = standardNum * 9;

        filterPanel = new JPanel();
        filterPanel.setBackground(Color.white);
        filterPanel.setBounds(x, y, width, height);
        filterPanel.setLayout(null);
        add(filterPanel);

        // 필터 분류 setBounds를 위한 width, height 재설정
        width = standardNum*3;
        height = (int) (standardNum * 1.1);
        setMajor(); setCourseNum(); // 필터 분류 각각 설정
        setClassName(); setProfessor();
        setGrade(); setClassNum();
        setCompletion(); setBtnSearch();

    }

    public void setMajor() { // 개설 학과 전공
        lblMajor = new JLabel(Constants.MAJORKOR_TXT);
        lblMajor.setBackground(Color.white);
        lblMajor.setBounds(standardNum, 0, width, height);
        lblMajor.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
        filterPanel.add(lblMajor);

        setMajorComboBox(); // 콤보 박스 설정
    }

    public void setCourseNum() { // 학수 번호
        lblCourseNum = new JLabel(Constants.COURSENUMKOR_TXT);
        lblCourseNum.setBackground(Color.white);
        lblCourseNum.setBounds(standardNum, standardNum, width, height);
        lblCourseNum.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
        filterPanel.add(lblCourseNum);

        txtCourseNum = new JTextField();
        txtCourseNum.setBounds(standardNum*4, (int)(standardNum*1.1), width, (int)(height-(standardNum*0.3)));
        txtCourseNum.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
        filterPanel.add(txtCourseNum);
    }

    public void setClassName() { // 교과목명
        lblClassName = new JLabel(Constants.CLASSNAMEKOR_TXT);
        lblClassName.setBackground(Color.white);
        lblClassName.setBounds(standardNum, standardNum*2, width, height);
        lblClassName.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
        filterPanel.add(lblClassName);

        txtClassName = new JTextField();
        txtClassName.setBounds(standardNum*4, (int)(standardNum*2.1), width, (int)(height-(standardNum*0.3)));
        txtClassName.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
        filterPanel.add(txtClassName);
    }

    public void setProfessor() { // 교수명
        lblProfessor = new JLabel(Constants.PROFESSORKOR_TXT);
        lblProfessor.setBackground(Color.white);
        lblProfessor.setBounds(standardNum, standardNum*3, width, height);
        lblProfessor.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
        filterPanel.add(lblProfessor);

        txtProfessor = new JTextField();
        txtProfessor.setBounds(standardNum*4, (int)(standardNum*3.1), width, (int)(height-(standardNum*0.3)));
        txtProfessor.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
        filterPanel.add(txtProfessor);
    }

    public void setGrade() { // 학년
        lblGrade = new JLabel(Constants.GRADEKOR_TXT);
        lblGrade.setBackground(Color.white);
        lblGrade.setBounds(standardNum, standardNum*4, width, height);
        lblGrade.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
        filterPanel.add(lblGrade);

        setGradeComboBox(); // 콤보 박스 설정
    }

    public void setClassNum() { // 분반
        lblClassNum = new JLabel(Constants.CLASSNUMKOR_TXT);
        lblClassNum.setBackground(Color.white);
        lblClassNum.setBounds(standardNum, standardNum*5, width, height);
        lblClassNum.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
        filterPanel.add(lblClassNum);

        txtClassNum = new JTextField();
        txtClassNum.setBounds(standardNum*4, (int) (standardNum*5.1), width, (int)(height-(standardNum*0.3)));
        txtClassNum.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
        filterPanel.add(txtClassNum);
    }

    public void setCompletion() { // 이수 구분
        lblCompletion = new JLabel(Constants.COMPLETIONKOR_TXT);
        lblCompletion.setBackground(Color.white);
        lblCompletion.setBounds(standardNum, standardNum*6, width, height);
        lblCompletion.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
        filterPanel.add(lblCompletion);

        setCompletionComboBox(); // 콤보 박스 설정
    }

    public void setBtnSearch() { // 검색 버튼 설정
        btnSearch = new JButton(Constants.SEARCH_TXT);
        btnSearch.setBackground(Color.white);
        btnSearch.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 20));
        btnSearch.setBounds((int)(standardNum*2.1), (int)(standardNum*7.5), (int)(standardNum*3.8), (int)(height-(standardNum*0.3)));
        filterPanel.add(btnSearch);
        btnSearch.addActionListener(filterController);
    }

    // 콤보 박스 설정
    // 학과 콤보 박스 설정
    public void setMajorComboBox() {
        cbxMajor = new JComboBox<>(Constants.MAJOR_HEADER);
        cbxMajor.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 14));
        cbxMajor.setBounds(standardNum*4, (int)(standardNum*0.1), width, (int)(height-(standardNum*0.3)));
        filterPanel.add(cbxMajor, BorderLayout.NORTH);
    }

    // 학년 콤보 박스 설정
    public void setGradeComboBox() {
        cbxGrade = new JComboBox<>(Constants.GRADE_HEADER);
        cbxGrade.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 14));
        cbxGrade.setBounds(standardNum*4, (int)(standardNum*4.1), width, (int)(height-(standardNum*0.3)));
        filterPanel.add(cbxGrade, BorderLayout.NORTH);
    }

    // 이수 구분 콤보 박스
    public void setCompletionComboBox() {
        cbxCompletion = new JComboBox<>(Constants.COMPLETION_HEADER);
        cbxCompletion.setFont(new Font(DesignConstants.HANGUL_FONT, Font.BOLD, 14));
        cbxCompletion.setBounds(standardNum*4, (int)(standardNum*6.1), width, (int)(height-(standardNum*0.3)));
        filterPanel.add(cbxCompletion, BorderLayout.NORTH);
    }

    public String getMajor(){ return (String) cbxMajor.getSelectedItem(); }
    public String getCourseNum(){return txtCourseNum.getText();}
    public String getProfessor() {return txtProfessor.getText();}
    public String getGrade(){
        if (!cbxGrade.getSelectedItem().equals("NONE"))
            return (String) cbxGrade.getSelectedItem();
        return "";
    }
    public String getClassNum(){return txtClassNum.getText();}
    public String getCompletion(){return (String) cbxCompletion.getSelectedItem();}
    public String getClassName(){return txtClassName.getText();}

}
