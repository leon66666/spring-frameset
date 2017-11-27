package wangzhongqiu.spring.core.exception;


public abstract class ServiceException extends Exception {

    private static final long serialVersionUID = -5924510760386924151L;
    public static final int ERR_GENERAL = 0xCAFEBABE;
    public static final int ERR_OPTIMISTIC_LOCKING_FAILURE = 1000;

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
