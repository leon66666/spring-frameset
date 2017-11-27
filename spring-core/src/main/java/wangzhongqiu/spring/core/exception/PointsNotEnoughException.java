package wangzhongqiu.spring.core.exception;


public class PointsNotEnoughException extends ServiceException {

    private static final long serialVersionUID = -8655192426198580738L;

    public PointsNotEnoughException(String username, double amount) {
        super("Points of [" + username + "] is less than <" + amount + ">");
    }

    public PointsNotEnoughException(String username, double amount, String type) {
        super("Points of [" + username + "] is less than <" + amount + "> to repay " + type);
    }

    public PointsNotEnoughException(Integer userId, double amount) {
        super("Points of [" + userId + "] is less than <" + amount + ">");
    }

    public PointsNotEnoughException(Integer userId, double amount, String type) {
        super("Points of [" + userId + "] is less than <" + amount + "> to repay " + type);
    }

    public PointsNotEnoughException(String notes) {
        super(notes);
    }
}
