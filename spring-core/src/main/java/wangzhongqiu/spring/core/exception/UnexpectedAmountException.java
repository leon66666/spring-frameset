package wangzhongqiu.spring.core.exception;

public class UnexpectedAmountException extends PointsNotEnoughException {

    private static String msg = "Negative amount is unexpected: ";

    public UnexpectedAmountException(String username, double amount) {
        super(msg + amount);
    }

    public UnexpectedAmountException(String username, double amount, String type) {
        super(msg + amount);
    }

    public UnexpectedAmountException(Integer userId, double amount) {
        super(msg + amount);
    }

    public UnexpectedAmountException(Integer userId, double amount, String type) {
        super(msg + amount);
    }
    
    public UnexpectedAmountException(Double amount) {
        super(msg + amount);
    }

}
