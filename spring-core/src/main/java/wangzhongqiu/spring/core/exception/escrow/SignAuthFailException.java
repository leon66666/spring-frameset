package wangzhongqiu.spring.core.exception.escrow;


/**
 * 验证签名失败
 */
public class SignAuthFailException extends BaseEscrowException {

    private static final long serialVersionUID = 9067454164194426637L;

    public SignAuthFailException() {
        super("");
    }

    public SignAuthFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignAuthFailException(String message) {
        super(message);
    }

}
