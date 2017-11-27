package wangzhongqiu.spring.core.exception;

public class ImportUserException extends Exception {

    public ImportUserException(String msg) {
        super(msg);
    }

    public ImportUserException(String msg, Throwable cause) {
        super(msg, cause);
    }

    private static final long serialVersionUID = 2453586514425965224L;

}
