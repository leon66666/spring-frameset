package wangzhongqiu.spring.core.exception;


public class VerifyException extends ServiceException {

    private static final long serialVersionUID = -1924943481251817792L;

    public VerifyException(Throwable th) {
        super("Failed to verify.", th);
    }
}
