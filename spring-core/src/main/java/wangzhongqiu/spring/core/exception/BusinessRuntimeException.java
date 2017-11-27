package wangzhongqiu.spring.core.exception;

public class BusinessRuntimeException extends ServiceRuntimeException {

    private static final long serialVersionUID = -1705069509823304717L;

    public BusinessRuntimeException() {
        super("");
    }

    public BusinessRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessRuntimeException(String message) {
        super(message);
    }

}
