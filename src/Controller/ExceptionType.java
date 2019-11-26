package Controller;

enum ExceptionType {
    FILE_NOT_FOUND(0x0A, "File not found"),
    FILE_IS_NOT_XLSX(0x5C, "The file is not .xlsx"),
    EMPTY_FILE_PATH(0x8F, "A file path is empty");

    private final int       errCode;
    private final String    msg;

    ExceptionType(int errCode, String msg) {
        this.errCode    = errCode;
        this.msg        = msg;
    } // Constructor

    int getErrCode() { return errCode; }
    String getMsg() { return msg; }
} // ExceptionType enum class