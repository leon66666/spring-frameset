package wangzhongqiu.spring.core.exception;

public class ImportUserLoanException extends ServiceException {

    public ImportUserLoanException(String msg) {
        super(msg);
    }

    public ImportUserLoanException(String msg, Throwable cause) {
        super(msg, cause);
    }

    private static final long serialVersionUID = 2453586514425965224L;

}
