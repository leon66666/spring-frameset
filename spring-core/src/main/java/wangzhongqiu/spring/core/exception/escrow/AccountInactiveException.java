package wangzhongqiu.spring.core.exception.escrow;


/**
 * 存管未创建账号
 */
public class AccountInactiveException extends BaseEscrowException {

    private static final long serialVersionUID = 9067454164194426637L;

    public AccountInactiveException() {
        super("");
    }

    public AccountInactiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountInactiveException(String message) {
        super(message);
    }

}
