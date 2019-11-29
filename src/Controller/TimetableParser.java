package Controller;

import Model.ClassManager;
import Model.LectureVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

class TimetableParser {
    private String strXlsxFilePath;

    TimetableParser() {
        this.strXlsxFilePath = "";
    } // Constructor

    TimetableParser(String strXlsxFilePath) {
        this.strXlsxFilePath = strXlsxFilePath;
    } // Constructor

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
        }

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
        }
    } // parseXlsxFile()

    private ArrayList<Integer> getDataColumnIndexes(Row firstRow) {
        Iterator<Cell> cellIterator = firstRow.cellIterator();
        ArrayList<Integer> dataColumnIndexArrayList = new ArrayList<>();

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();

            if (cell.getCellType() != CellType.STRING)
                continue;

            if (!TimetableParserConstants.isExists(cell.getStringCellValue()))
                continue;

            System.out.println(cell.getStringCellValue());

            dataColumnIndexArrayList.add(cell.getColumnIndex());
        } // while

        return dataColumnIndexArrayList;
    } // getDataColumnIndexes()

    String getStrXlsxFilePath() {
        return strXlsxFilePath;
    }
    void setStrXlsxFilePath(String strXlsxFilePath) {
        this.strXlsxFilePath = strXlsxFilePath;
    }
} // TimetableParser Class