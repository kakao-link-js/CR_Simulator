package Controller;

class TimetableParserConstants {
    private final static String[] CONSTANTS = {
            "개설대학",
            "개설학과전공",
            "학수번호",
            "분반",
            "교과목명",
            "이수구분",
            "학년",
            "학점",
            "요일 및 강의시간",
            "강의실",
            "교수명"
    };

    static boolean isExists(String str) {
        for (String constant : CONSTANTS) {
            if (constant.equals(str))
                return true;
        }
        return false;
    } // isExists()
} // TimetableParserConstants Class
