package Controller;

import Model.ClassManager;
import Model.LectureVO;
import common.ExceptionType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * TimetableParser Class
 * 시간표 정보를 담고있는 .xlsx 파일을 읽어들여 ArrayList<LectureVO> 타입의 m_lectureList 객체에 저장하는 Parser 클래스
 *
 * @author 이종진
 */
class TimetableParser {
    private String strXlsxFilePath;

    /**
     * TimetableParser Constructor
     * .xlsx 파일 경로를 초기화 또는 사용자 정의
     */
    TimetableParser() {
        this.strXlsxFilePath = "";
    } // Constructor

    /**
     * TimetableParser Constructor
     * .xlsx 파일 경로를 초기화 또는 사용자 정의
     *
     * @param strXlsxFilePath : String
     */
    TimetableParser(String strXlsxFilePath) {
        this.strXlsxFilePath = strXlsxFilePath;
    } // Constructor

    /**
     * parseXlsxFile Method
     * 지정된 파일 경로로 부터
     *
     * @param strXlsxFilePath : String
     */
    void parseXlsxFile() throws XlsxParseException {

        // 파일 경로 설정 여부 검사
        if (this.strXlsxFilePath.isEmpty())
            throw new XlsxParseException(ExceptionType.EMPTY_FILE_PATH);

        // 파일 확장자 검사
        if (!this.strXlsxFilePath.endsWith(".xlsx"))
            throw new XlsxParseException(ExceptionType.FILE_IS_NOT_XLSX);

        XSSFWorkbook workbook;

        // 파일 존재 여부 검사
        try {
            workbook = new XSSFWorkbook(new FileInputStream(this.strXlsxFilePath));
        } catch (IOException e) {
            throw new XlsxParseException(ExceptionType.FILE_NOT_FOUND);
        } // try - catch

        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.rowIterator();
        Row row = rowIterator.next();

        ArrayList<Integer> dataColumnIndexArrayList = getDataColumnIndexes(row);
        ArrayList<String> lectureDataArrayList = new ArrayList<>();

        while (rowIterator.hasNext()) {
            row = rowIterator.next();

            for (Integer dataColumnIndex : dataColumnIndexArrayList)
                lectureDataArrayList.add(row.getCell(dataColumnIndex).getStringCellValue());

            ClassManager.getInstance().getLecture().add(new LectureVO(lectureDataArrayList));

            lectureDataArrayList.clear();
        } // while
    } // parseXlsxFile()

    /**
     * getDataColumnIndexes Method
     * 읽어 올 컬럼을 지정하여 인덱스 값 반환
     *
     * @param header : Row
     * @return dataColumnIndexArrayList : ArrayList<Integer>
     */
    private ArrayList<Integer> getDataColumnIndexes(Row header) {
        Iterator<Cell> cellIterator = header.cellIterator();
        ArrayList<Integer> dataColumnIndexArrayList = new ArrayList<>();

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();

            if (cell.getCellType() != CellType.STRING)
                continue;

            if (!TimetableParserConstants.isExists(cell.getStringCellValue()))
                continue;

            dataColumnIndexArrayList.add(cell.getColumnIndex());
        } // while

        return dataColumnIndexArrayList;
    } // getDataColumnIndexes()

    String getStrXlsxFilePath() {
        return strXlsxFilePath;
    } // getStrXlsxFilePath()
    void setStrXlsxFilePath(String strXlsxFilePath) {
        this.strXlsxFilePath = strXlsxFilePath;
    } // setStrXlsxFilePath()
} // TimetableParser Class