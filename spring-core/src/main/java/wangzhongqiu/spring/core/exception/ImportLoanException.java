package wangzhongqiu.spring.core.exception;

public class ImportLoanException extends Exception {

    public ImportLoanException(String msg) {
        super(msg);
    }

    public ImportLoanException(String msg, Throwable cause) {
        super(msg, cause);
    }

    private static final long serialVersionUID = 2453586514425965224L;

}
