package wangzhongqiu.spring.core.exception.base;

public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1768175007038212920L;

    protected BaseException(String message) {
        super(message);
    }

    protected BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getCode() {
        return ExceptionRegister.getCode(this.getClass());
    }

}
