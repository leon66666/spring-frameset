package wangzhongqiu.spring.core.exception.base;

import wangzhongqiu.spring.core.model.User;

public class HasLoanOverdueException extends BusinessBaseException {

    private static final long serialVersionUID = 1558159304757773962L;

    public HasLoanOverdueException(User borrower) {
        super(String.format("Borrower %d has loan overdue or in bad debt status", borrower.getUserId()));
    }

}
