package View;

import Model.LectureDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterestedFilterView extends JPanel {
    JPanel titlePanel; // 나가기 버튼 및 현재 페이지의 이름이 있는 Panel
    JPanel filterPanel; // 수강신청 분류 조건 Panel

    // setBounds를 위한 변수들
    private int x, y, width, height;
    private int standardNum = 50;
    private double standardBlank = 0.1;

    // filterPanel
    public JTextField txtCourseNum; // 학수번호
    public JTextField txtClassName; // 교과목명
    public JTextField txtProfessor; // 교수님 성함
    public JTextField txtClassNum; // 분반

    // 콤보 박스
    public JComboBox<String> cbxMajor; // 전공
    public JComboBox<String> cbxGrade; // 학년
    public JComboBox<String> cbxCompletion; // 이수구분

    // 필터가 완료된 강의 목록들
    public ArrayList<LectureDTO> filteredLectureList  = new ArrayList<LectureDTO>();

    // 필터 조건들 초기화
    public String filterMajorInfo = "";
    public String filterCourseNumInfo = "";
    public String filterProInfo = "";
    public String filterClassNameInfo = "";
    public String filterClassNumInfo = "";
    public String filterCompInfo = "";
    public int filterGradeInfo = -1;

    // 콤보 박스 설정을 위한 배열
    String[] strGrade = {"0","1", "2", "3", "4"};
    String[] strMajor = {"","대양휴머니티칼리지","국어국문학과","국제학부","국제학부 영어영문학전공","국제학부 일어일문학전공", "국제학부 중국통상학전공", "역사학과","교육학과","경제통상학과","행정학과","미디어커뮤니케이션학과","신문방송학과","경영학부","경영학부 경영학전공","호텔관광외식경영학부","호텔관광외식경영학부 호텔관광경영학전공","호텔관광외식경영학부 외식경영학전공","호텔외식관광프랜차이즈경영학과","호텔외식비즈니스학과","글로벌조리학과","수학통계학부","수학통계학부 수학전공","수학통계학부 응용통계학전공","물리천문학과","화학과","생명시스템학부","생명시스템학부 식품생명공학전공","생명시스템학부 식품공학전공","생명시스템학부 바이오융합공학전공","생명시스템학부 바이오산업자원공학전공","전자정보통신공학과","컴퓨터공학과","정보보호학과","디지털콘텐츠학과","소프트웨어학과","데이터사이언스학과","지능기전공학부","지능기전공학부 무인이동체공학전공","지능기전공학부 스마트기기공학전공","창의소프트학부","창의소프트학부 디자인이노베이션전공","창의소프트학부 만화애니메이션텍전공","건축공학부","건축공학부 건축공학전공","건축공학부 건축학전공","건설환경공학과","환경에너지공간융합학과","에너지자원공학과","기계항공우주공학부","기계항공우주공학부 기계공학전공","기계항공우주공학부 항공우주공학전공","나노신소재공학과","원자력공학과","국방시스템공학과","회화과","산업디자인학과","패션디자인학과","음악과","체육학과","무용과","만화애니메이션학과","영화예술학과","법학부","법학부 법학전공","융합창업전공","엔터테인먼트 소프트웨어 융합전공","글로벌미디어소프트웨어 융합전공","소셜미디어매니지먼트소프트웨어 융합전공","공연예술 융합전공","럭셔리 브랜드 디자인 융합전공","비즈니스 애널리틱스 융합전공","스마트 투어리즘 매니지먼트 소프트웨어 융합전공","영상디자인 융합전공","예술경영 융합전공"};
    String[] strCompletion = {"","교직","무관후보생교육","자유선택교양","전공기초교양","전공선택","전공필수","중핵필수","중핵필수선택"};

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

    public InterestedFilterView() { // 패널 설정
        setBackground(Color.lightGray);
        setPreferredSize(new Dimension((int)(standardNum*8.2),(int)(standardNum*12.5)));
        setLayout(null);

        setTitlePanel(); // 상단바 패널 설정
        setFilterPanel(); // 필터 패널 설정
    }

    public void setTitlePanel() { // 상단바 패널 설정
        x = (int) (standardBlank*standardNum); // 비율에 따라 x,y,width, height 설정
        y = (int) (standardBlank*standardNum);
        width = (int) (standardNum*8);
        height = (int) (standardNum * 1.6);

        // 패널 및 버튼 및 라벨 설정
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        titlePanel.setBounds(x, y, width, height);
        titlePanel.setLayout(null);
        add(titlePanel);

        btnExit = new JButton("나가기");
        btnExit.setFont(new Font("나눔스퀘어",Font.PLAIN, 14));
        btnExit.setBounds(0,0,(int)(standardNum*1.5),height);
        btnExit.setBackground(Color.white);
        btnExit.setBorderPainted(false);
        titlePanel.add(btnExit);

        lblTitle = new JLabel("관심과목 필터");
        lblTitle.setFont(new Font("a이끌림M", Font.BOLD, 28));
        lblTitle.setBounds((int)(x+(standardNum*2)),y,standardNum*5,height);
        titlePanel.add(lblTitle);
    }

    public void setFilterPanel() { // 필터 패널 설정
        y = (int)((standardNum*standardBlank)*2 + titlePanel.getHeight());
        width = (int)(standardNum*8);
        height = (int)(standardNum * 10.6);

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
        lblMajor = new JLabel("개설 학과 전공");
        lblMajor.setBackground(Color.white);
        lblMajor.setBounds(standardNum, (int)(standardNum*0.8), width, height);
        lblMajor.setFont(new Font("a이끌림M", Font.BOLD, 20));
        filterPanel.add(lblMajor);

        setMajorComboBox(); // 콤보 박스 설정
    }

    public void setCourseNum() { // 학수 번호
        lblCourseNum = new JLabel("학수 번호");
        lblCourseNum.setBackground(Color.white);
        lblCourseNum.setBounds(standardNum, (int)(standardNum*1.9), width, height);
        lblCourseNum.setFont(new Font("a이끌림M", Font.BOLD, 20));
        filterPanel.add(lblCourseNum);

        txtCourseNum = new JTextField();
        txtCourseNum.setBounds(standardNum*4, (int)(standardNum*2), width, (int)(height-(standardNum*0.3)));
        txtCourseNum.setFont(new Font("a이끌림M", Font.BOLD, 20));
        filterPanel.add(txtCourseNum);
    }

    public void setClassName() { // 교과목명
        lblClassName = new JLabel("교과목명");
        lblClassName.setBackground(Color.white);
        lblClassName.setBounds(standardNum, (int)(standardNum*3), width, height);
        lblClassName.setFont(new Font("a이끌림M", Font.BOLD, 20));
        filterPanel.add(lblClassName);

        txtClassName = new JTextField();
        txtClassName.setBounds(standardNum*4, (int)(standardNum*3.1), width, (int)(height-(standardNum*0.3)));
        txtClassName.setFont(new Font("a이끌림M", Font.BOLD, 20));
        filterPanel.add(txtClassName);
    }

    public void setProfessor() { // 교수명
        lblProfessor = new JLabel("교수명");
        lblProfessor.setBackground(Color.white);
        lblProfessor.setBounds(standardNum, (int)(standardNum*4.1), width, height);
        lblProfessor.setFont(new Font("a이끌림M", Font.BOLD, 20));
        filterPanel.add(lblProfessor);

        txtProfessor = new JTextField();
        txtProfessor.setBounds(standardNum*4, (int)(standardNum*4.2), width, (int)(height-(standardNum*0.3)));
        txtProfessor.setFont(new Font("a이끌림M", Font.BOLD, 20));
        filterPanel.add(txtProfessor);
    }

    public void setGrade() { // 학년
        lblGrade = new JLabel("학년");
        lblGrade.setBackground(Color.white);
        lblGrade.setBounds(standardNum, (int)(standardNum*5.2), width, height);
        lblGrade.setFont(new Font("a이끌림M", Font.BOLD, 20));
        filterPanel.add(lblGrade);

        setGradeComboBox(); // 콤보 박스 설정
    }

    public void setClassNum() { // 분반
        lblClassNum = new JLabel("분반");
        lblClassNum.setBackground(Color.white);
        lblClassNum.setBounds(standardNum, (int)(standardNum*6.3), width, height);
        lblClassNum.setFont(new Font("a이끌림M", Font.BOLD, 20));
        filterPanel.add(lblClassNum);

        txtClassNum = new JTextField();
        txtClassNum.setBounds(standardNum*4, (int)(standardNum*6.4), width, (int)(height-(standardNum*0.3)));
        txtClassNum.setFont(new Font("a이끌림M", Font.BOLD, 20));
        filterPanel.add(txtClassNum);
    }

    public void setCompletion() { // 이수 구분
        lblCompletion = new JLabel("이수 구분");
        lblCompletion.setBackground(Color.white);
        lblCompletion.setBounds(standardNum, (int)(standardNum*7.4), width, height);
        lblCompletion.setFont(new Font("a이끌림M", Font.BOLD, 20));
        filterPanel.add(lblCompletion);

        setCompletionComboBox(); // 콤보 박스 설정
    }

    public void setBtnSearch() { // 검색 버튼 설정
        btnSearch = new JButton("검색");
        btnSearch.setBackground(Color.white);
        btnSearch.setFont(new Font("a이끌림M", Font.BOLD, 20));
        btnSearch.setBounds((int)(standardNum*2.1), (int)(standardNum*8.7), (int)(standardNum*3.8), (int)(height-(standardNum*0.3)));
        filterPanel.add(btnSearch);
    }

    // 콤보 박스 설정
    // 학과 콤보 박스 설정
    public void setMajorComboBox() {
        cbxMajor = new JComboBox<>(strMajor);
        cbxMajor.setFont(new Font("a이끌림M", Font.BOLD, 14));
        cbxMajor.setBounds(standardNum*4, (int)(standardNum*0.9), width, (int)(height-(standardNum*0.3)));
        filterPanel.add(cbxMajor, BorderLayout.NORTH);
    }

    // 학년 콤보 박스 설정
    public void setGradeComboBox() {
        cbxGrade = new JComboBox<>(strGrade);
        cbxGrade.setFont(new Font("a이끌림M", Font.BOLD, 14));
        cbxGrade.setBounds(standardNum*4, (int)(standardNum*5.3), width, (int)(height-(standardNum*0.3)));
        filterPanel.add(cbxGrade, BorderLayout.NORTH);
    }

    // 이수 구분 콤보 박스
    public void setCompletionComboBox() {
        cbxCompletion = new JComboBox<>(strCompletion);
        cbxCompletion.setFont(new Font("a이끌림M", Font.BOLD, 14));
        cbxCompletion.setBounds(standardNum*4, (int)(standardNum*7.5), width, (int)(height-(standardNum*0.3)));
        filterPanel.add(cbxCompletion, BorderLayout.NORTH);
    }

    // 검색 버튼 리스너
    public void addSearchListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }

    // 나가기 버튼 리스너
    public void addExitButtonListener(ActionListener listener) {
        btnExit.addActionListener(listener);
    }

    // 각각의 필터 구분에 리스너 추가
    public void addValueListener(ActionListener listener) {
        txtCourseNum.addActionListener(listener);
        txtClassName.addActionListener(listener);
        txtProfessor.addActionListener(listener);
        txtClassNum.addActionListener(listener);
        cbxCompletion.addActionListener(listener);
        cbxGrade.addActionListener(listener);
        cbxMajor.addActionListener(listener);
    }
}
