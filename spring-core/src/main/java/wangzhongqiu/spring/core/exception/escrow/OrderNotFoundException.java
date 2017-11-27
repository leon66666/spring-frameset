package wangzhongqiu.spring.core.exception.escrow;

/**
 * 订单数据库中找不到
 */
public class OrderNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4292069145731686743L;

    public OrderNotFoundException() {
        super("");
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(String message) {
        super(message);
    }

}
