package wangzhongqiu.spring.core.exception.escrow;

public class BaseEscrowException extends RuntimeException {

    private static final long serialVersionUID = -3344899395950648244L;

    public BaseEscrowException() {
        super("");
    }

    public BaseEscrowException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseEscrowException(String message) {
        super(message);
    }

}
