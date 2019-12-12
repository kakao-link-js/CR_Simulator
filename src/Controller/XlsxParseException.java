package Controller;

import common.ExceptionType;

import java.io.IOException;

/**
 * XlsxParseException Class
 * TimetableParser Class에서 발생할 수 있는 사용자 정의 예외 클래스
 *
 * @author 이종진
 */
public class XlsxParseException extends IOException {

    private final ExceptionType ERR_TYPE;

    public XlsxParseException(ExceptionType errType) {
        super(errType.getMsg());
        ERR_TYPE = errType;
    } // Constructor

    public ExceptionType getErrorType() { return ERR_TYPE; }
} // XlsxParseException class

