package wangzhongqiu.spring.core.exception;


public class MobileRandCodeException extends ServiceException {

    private static final long serialVersionUID = -936773334387771006L;

    public MobileRandCodeException(String mobile) {
        super(mobile);
    }
}
