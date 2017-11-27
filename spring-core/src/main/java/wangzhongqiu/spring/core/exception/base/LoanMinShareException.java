package wangzhongqiu.spring.core.exception.base;

public class LoanMinShareException extends BusinessBaseException {
    private static final long serialVersionUID = 358200318550656519L;

    public LoanMinShareException(Integer loanId, Integer minShare) {
        super(String.format("The min investment share of loan %d is %d", loanId, minShare));
    }
}
