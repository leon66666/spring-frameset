package wangzhongqiu.spring.core.exception;


public class CreditNotEnoughException extends ServiceException {

    private static final long serialVersionUID = -7979933196831843141L;

    public CreditNotEnoughException(String username, double amount) {
        super("Credit of [" + username + "] is less than <" + amount + ">");
    }
}
