package Controller;

/**
 * TimetableParserConstants Class
 * TimetableParser에서 xlsx파일에서 읽어 올 Column의 String constants 클래스
 *
 * @author 이종진
 */
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

    /**
     * isExists Method
     * 해당 String constant 존재 여부 반환
     *
     * @param str
     */
    static boolean isExists(String str) {
        for (String constant : CONSTANTS) {
            if (constant.equals(str))
                return true;
        }
        return false;
    } // isExists()
} // TimetableParserConstants Class
