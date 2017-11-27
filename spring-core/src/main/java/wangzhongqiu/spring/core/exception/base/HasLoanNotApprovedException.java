package wangzhongqiu.spring.core.exception.base;

import wangzhongqiu.spring.core.model.User;

public class HasLoanNotApprovedException extends BusinessBaseException {

    private static final long serialVersionUID = 3375467655147415950L;

    public HasLoanNotApprovedException(User borrower) {
        super(String.format("Borrower %d has loan not approved", borrower.getUserId()));
    }
}
