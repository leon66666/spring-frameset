package wangzhongqiu.spring.core.exception;


public class LoanNotExistedException extends ServiceException {

    private static final long serialVersionUID = -9014701834766534605L;

    public LoanNotExistedException(int loanID) {
        super("Loan [" + loanID + "] not existed");
    }
}
