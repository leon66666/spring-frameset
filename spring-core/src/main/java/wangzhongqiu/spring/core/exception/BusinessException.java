package wangzhongqiu.spring.core.exception;

public class BusinessException extends ServiceException {

    private static final long serialVersionUID = -1705069509823304717L;

    public BusinessException() {
        super("");
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

}
