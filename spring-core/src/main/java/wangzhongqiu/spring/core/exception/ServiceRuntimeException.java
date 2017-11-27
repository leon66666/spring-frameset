package wangzhongqiu.spring.core.exception;

/**
 * 同ServiceException, 将用于中断事务
 * 
 */
public abstract class ServiceRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 936533375392833499L;

    public ServiceRuntimeException() {
        super();
    }

    public ServiceRuntimeException(String msg) {
        super(msg);
    }

    public ServiceRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
