package Controller;

public class XlsxParseException extends RuntimeException {

    private final ExceptionType ERR_TYPE;

    public XlsxParseException(ExceptionType errType) {
        super(errCode.getMsg());
        ERR_TYPE = errType;
    }

    public ExceptionType getErrorType() {
        return ERR_TYPE;
    }
}

