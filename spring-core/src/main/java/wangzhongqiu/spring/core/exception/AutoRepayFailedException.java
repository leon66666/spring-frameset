package wangzhongqiu.spring.core.exception;

public class AutoRepayFailedException extends Exception {

    private static final long serialVersionUID = -1354750010315770275L;

    public AutoRepayFailedException() {
        super();
    }

    public AutoRepayFailedException(String msg) {
        super(msg);
    }

    public AutoRepayFailedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
