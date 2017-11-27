package wangzhongqiu.spring.core.exception;


public class AlreadyTriedLoanException extends ServiceException {

    private static final long serialVersionUID = -8652908498727621331L;

    public AlreadyTriedLoanException(String username) {
        super("User [" + username + "] has already tried loan");
    }
}
