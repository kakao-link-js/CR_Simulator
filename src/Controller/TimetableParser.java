package Controller;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    public void parseXlsxFile() throws XlsxParseException, IOException {

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

        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file.getAbsolutePath()));
        HSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows();

        // TODO : Parse timetable

    }



    public String getStrXlsxFilePath() {
        return strXlsxFilePath;
    }
    public void setStrXlsxFilePath(String strXlsxFilePath) {
        this.strXlsxFilePath = strXlsxFilePath;
    }
} // TimetableParser Class