package Controller;

import java.io.IOException;

public class XlsxParseException extends IOException {

    private final ExceptionType ERR_TYPE;

    XlsxParseException(ExceptionType errType) {
        super(errType.getMsg());
        ERR_TYPE = errType;
    } // Constructor

    public ExceptionType getErrorType() { return ERR_TYPE; }
} // XlsxParseException class

