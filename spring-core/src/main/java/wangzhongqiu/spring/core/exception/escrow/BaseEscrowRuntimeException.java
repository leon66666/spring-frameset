package wangzhongqiu.spring.core.exception.escrow;

/**
 *
 */
public class BaseEscrowRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -4292069145731686743L;

    public BaseEscrowRuntimeException() {
        super("");
    }

    public BaseEscrowRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseEscrowRuntimeException(String message) {
        super(message);
    }

}
