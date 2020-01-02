package common;

import javax.swing.*;

public class Constants {
    public static final String SCORES[] ={"NONE","A+","A","B+","B","C+","C","D+","D","F"};
    public static final String MENU[] = {"수강신청", "내 시간표", "학점 계산기","내 정보 수정", "로그아웃"};

    public static final String[] COLUMN_HEADER = {"", "월", "화", "수", "목", "금"};
    public static final String[][] ROW_HEADER = {
            {"9"}, {""}, {"10"}, {""},
            {"11"}, {""}, {"12"}, {""},
            {"1"}, {""}, {"2"}, {""},
            {"3"}, {""}, {"4"}, {""},
            {"5"}, {""}, {"6"}, {""},
            {"7"}, {""}, {"8"}
    };
    public static final String TABLE_HEADER[] = {"신청/취소","개설대학","개설학과전공","학수번호","분반","교과목명","이수구분","학년","학점","요일 및 강의시간","강의실","교수","신청인원"};
    public static final String MYTABLE_HEADER[] = {"신청/취소","개설대학","개설학과전공","학수번호","분반","교과목명","이수구분","학년","학점","요일 및 강의시간","강의실","교수"};
    // 콤보 박스 설정을 위한 배열
    public static final String[] GRADE_HEADER = {"NONE", "1", "2", "3", "4"};
    public static final String[] MAJOR_HEADER = {"","대양휴머니티칼리지","국어국문학과","국제학부","국제학부 영어영문학전공","국제학부 일어일문학전공", "국제학부 중국통상학전공", "역사학과","교육학과","경제통상학과","행정학과","미디어커뮤니케이션학과","신문방송학과","경영학부","경영학부 경영학전공","호텔관광외식경영학부","호텔관광외식경영학부 호텔관광경영학전공","호텔관광외식경영학부 외식경영학전공","호텔외식관광프랜차이즈경영학과","호텔외식비즈니스학과","글로벌조리학과","수학통계학부","수학통계학부 수학전공","수학통계학부 응용통계학전공","물리천문학과","화학과","생명시스템학부","생명시스템학부 식품생명공학전공","생명시스템학부 식품공학전공","생명시스템학부 바이오융합공학전공","생명시스템학부 바이오산업자원공학전공","전자정보통신공학과","컴퓨터공학과","정보보호학과","디지털콘텐츠학과","소프트웨어학과","데이터사이언스학과","지능기전공학부","지능기전공학부 무인이동체공학전공","지능기전공학부 스마트기기공학전공","창의소프트학부","창의소프트학부 디자인이노베이션전공","창의소프트학부 만화애니메이션텍전공","건축공학부","건축공학부 건축공학전공","건축공학부 건축학전공","건설환경공학과","환경에너지공간융합학과","에너지자원공학과","기계항공우주공학부","기계항공우주공학부 기계공학전공","기계항공우주공학부 항공우주공학전공","나노신소재공학과","원자력공학과","국방시스템공학과","회화과","산업디자인학과","패션디자인학과","음악과","체육학과","무용과","만화애니메이션학과","영화예술학과","법학부","법학부 법학전공","융합창업전공","엔터테인먼트 소프트웨어 융합전공","글로벌미디어소프트웨어 융합전공","소셜미디어매니지먼트소프트웨어 융합전공","공연예술 융합전공","럭셔리 브랜드 디자인 융합전공","비즈니스 애널리틱스 융합전공","스마트 투어리즘 매니지먼트 소프트웨어 융합전공","영상디자인 융합전공","예술경영 융합전공"};
    public static final String[] COMPLETION_HEADER = {"","교직","무관후보생교육","자유선택교양","전공기초교양","전공선택","전공필수","중핵필수","중핵필수선택"};

    //한글 라벨 부분
    public static final String INSERT_TXT = "수강신청";
    public static final String MYLECTURE_TXT = "내 시간표";
    public static final String CALCUL_TXT = "학점 계산기";
    public static final String MODIFY_TXT = "내 정보 수정";
    public static final String END_TXT = "종료";
    public static final String LOGOUT_TXT = "로그아웃";
    public static final String SEARCH_TXT = "검색";
    public static final String EXIT_TXT = "나가기";
    public static final String BACK_TXT = "<";

    public static final String MAJORKOR_TXT = "개설학과전공";
    public static final String COURSENUMKOR_TXT = "학수번호";
    public static final String CLASSNUMKOR_TXT = "분반";
    public static final String CLASSNAMEKOR_TXT = "교과목명";
    public static final String COMPLETIONKOR_TXT = "이수구분";
    public static final String GRADEKOR_TXT = "학년";
    public static final String SCOREKOR_TXT = "학점";
    public static final String PROFESSORKOR_TXT = "교수명";
    public static final String MYSCOREKOR_TXT = "성적";
    public static final String APPLY_TXT = "신청";
    public static final String CANCEL_TXT = "취소";
    public static final String REFRESH_TXT = "새로고침";

    public static final String OPENUNIV_TXT = "openUniv";
    public static final String MAJOR_TXT = "major";
    public static final String COURSENUM_TXT = "courseNum";
    public static final String CLASSNUM_TXT = "classNum";
    public static final String CLASSNAME_TXT = "className";
    public static final String COMPLETION_TXT = "completion";
    public static final String GRADE_TXT = "grade";
    public static final String SCORE_TXT = "score";
    public static final String TIME_TXT = "time";
    public static final String CLASSROOM_TXT = "classRoom";
    public static final String PROFESSOR_TXT = "professor";
    public static final String NUMBER_TXT = "nCount";

    public static final String LOGIN_TXT = "로그인";
    public static final String SIGNUP_TXT = "회원가입";
    public static final String IDSEARCH_TXT = "ID찾기";

    public static final String DUPLICATE_TXT = "중복";

    public static final String IDKOR_TXT = "ID(학번)";
    public static final String PWKOR_TXT = "비밀번호";
    public static final String NAMEKOR_TXT = "이름";
    public static final String PHONEKOR_TXT = "휴대전화";
    public static final String BIRTHKOR_TXT = "생년월일";
    public static final String MODIFYKOR_TXT = "수정";

    //통신 부분
    public static final String ID_TXT = "student_id";
    public static final String PASSWORD_TXT = "password";
    public static final String NAME_TXT = "name";
    public static final String PHONE_TXT = "phone";
    public static final String SUCCESS_TXT = "success";
    public static final String TRUE_TXT = "true";
    public static final String DELETE_TXT = "DELETE";
    public static final String PUT_TXT = "PUT";
    public static final String POST_TXT = "POST";

    //정규식 부분
    public static final String KOREAN_REGEX = "^[가-힣]*$";
    public static final String PHONE_REGEX = "^01(?:0|1|[6-9])[.-]?([0-9]{3}|[0-9]{4})[.-]?([0-9]{4})$";
    public static final String NUMBER_REGEX = "^[0-9]+$";
    public static final String BIRTH_REGEX = "^[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|[3][0-1])$";

    //서버 부분
    public static final String BASE_URL = "http://27.96.135.10:8888/";

    public static final String UNIV_NAME = "SEJONG UNIVERSITY";
}
