package wangzhongqiu.spring.core.exception;


public class AlreadyVarifiedException extends ServiceException {

    private static final long serialVersionUID = 3610889837216510983L;

    public AlreadyVarifiedException() {
        super("Already been validated");
    }
}
