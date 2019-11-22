package Controller;

public enum ExceptionType {
    FILE_NOT_FOUND(0x0A, "File not found"),
    FILE_IS_NOT_XLSX(0x5C, "The file is not .xlsx"),
    EMPTY_FILE_PATH(0x8F, "A file path is empty");

    private final int       errCode;
    private final String    msg;

    ExceptionType(int errCode, String msg) {
        this.errCode    = errCode;
        this.msg        = msg;
    }

    public int getErrCode() { return errCode; }
    public String getMsg() { return msg; }
}

package Controller;

        import java.io.File;

public class TimetableParser {
    private String strXlsxFilePath;
    private File file;

    public TimetableParser() {
        this.strXlsxFilePath = "";
        this.file = null;
    } // Constructor

    public TimetableParser(String strXlsxFilePath) throws XlsxParseException {
        this.file = new File(strXlsxFilePath);

        // 파일 존재 유무 검사
        if (!file.isFile())
            throw new XlsxParseException(ExceptionType.FILE_NOT_FOUND);

        // 파일 확장자 검사
        if (!file.getName().endsWith(".xlsx"))
            throw new XlsxParseException(ExceptionType.FILE_IS_NOT_XLSX);

        this.strXlsxFilePath = strXlsxFilePath;
    } // Constructor

    public void parseXlsxFile() throws XlsxParseException {

        // 파일 경로 설정 유무 검사
        if (this.strXlsxFilePath.isEmpty())
            throw new XlsxParseException(ExceptionType.EMPTY_FILE_PATH);

        if (file == null)
            this.file = new File(strXlsxFilePath);

        // 파일 존재 유무 검사
        if (!file.isFile())
            throw new XlsxParseException(ExceptionType.FILE_NOT_FOUND);

        // 파일 확장자 검사
        if (!file.getName().endsWith(".xlsx"))
            throw new XlsxParseException(ExceptionType.FILE_IS_NOT_XLSX);




    }



    public String getStrXlsxFilePath() {
        return strXlsxFilePath;
    }
    public void setStrXlsxFilePath(String strXlsxFilePath) {
        this.strXlsxFilePath = strXlsxFilePath;
    }
} // TimetableParser Class