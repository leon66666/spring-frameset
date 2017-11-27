package wangzhongqiu.spring.core.exception;

public class ImportUserLoanParamsException extends ServiceException {

    public ImportUserLoanParamsException(String msg) {
        super(msg);
    }

    public ImportUserLoanParamsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    private static final long serialVersionUID = 3860117918654087023L;
}
