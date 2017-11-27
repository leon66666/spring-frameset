package wangzhongqiu.spring.core.exception;


public class LoanLenderTooFastException extends ServiceException {

    private static final long serialVersionUID = 2357508308156932583L;

    public LoanLenderTooFastException() {
        super("Loan lender is too fast");
    }
}
