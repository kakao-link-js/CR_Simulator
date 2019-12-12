package common;

/**
 * ExceptionType Enum Class
 * 발생할 수 있는 예외에 대해 정의한 Enum 클래스
 */
public enum ExceptionType {
    FILE_NOT_FOUND(0x0A, "존재하지 않는 파일입니다."),
    FILE_IS_NOT_XLSX(0x5C, ".xlsx 파일이 아닙니다."),
    EMPTY_FILE_PATH(0x8F, "A file path is empty");

    private final int       errCode;
    private final String    msg;

    ExceptionType(int errCode, String msg) {
        this.errCode    = errCode;
        this.msg        = msg;
    } // Constructor

    public int getErrCode() { return errCode; }
    public String getMsg() { return msg; }
} // ExceptionType enum class