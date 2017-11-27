package wangzhongqiu.spring.core.exception;


public class DBException extends ServiceException {

    private static final long serialVersionUID = -3901185625508473692L;

    public DBException(String msg, Throwable cause) {
        super("DB_EXCEPTION: " + msg, cause);
    }
}
