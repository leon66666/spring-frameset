package wangzhongqiu.spring.core.exception;

public class RedisConnectException extends Exception {

    private static final long serialVersionUID = -1354750010315770275L;

    public RedisConnectException() {
        super();
    }

    public RedisConnectException(String msg) {
        super(msg);
    }

    public RedisConnectException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
