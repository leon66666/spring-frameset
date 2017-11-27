package wangzhongqiu.spring.core.exception;

public class SubpointsNotEnoughException extends ServiceException {

    private static final long serialVersionUID = 1800987979726771248L;

    public SubpointsNotEnoughException(Integer subpointId, double amount) {
        super("Subpoints [" + subpointId + "] is less than <" + amount + ">");
    }

}
