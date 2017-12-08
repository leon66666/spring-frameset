package wangzhongqiu.spring.core.exception;

public class CommonRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 3909753914696899192L;
    private int errorCode;
    private String message;
    private Object[] parameters;

    public CommonRuntimeException(int errorCode) {
        this.errorCode = errorCode;
    }

    public CommonRuntimeException(int errorCode, String message) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public CommonRuntimeException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public CommonRuntimeException(int errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }

    public CommonRuntimeException(int errorCode, Object[] args) {
        this.errorCode = errorCode;
        this.parameters = args;
    }

    public CommonRuntimeException(int errorCode, String message, Object[] args) {
        this.errorCode = errorCode;
        this.message = message;
        this.parameters = args;
    }

    public CommonRuntimeException(int errorCode, Throwable cause, Object[] args) {
        super(cause);
        this.errorCode = errorCode;
        this.parameters = args;
    }

    public CommonRuntimeException(int errorCode, String message, Throwable cause, Object[] args) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
        this.parameters = args;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        if (null == this.message) {
            return String.valueOf(this.errorCode);
        }
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getParameters() {
        return this.parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return message != null ? s + ": EC = " + this.errorCode + ": MSG = " + message : s;
    }
}