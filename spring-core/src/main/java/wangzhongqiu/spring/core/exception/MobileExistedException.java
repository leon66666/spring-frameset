package wangzhongqiu.spring.core.exception;


public class MobileExistedException extends ServiceException {

    private static final long serialVersionUID = -936773334387771006L;

    public MobileExistedException(String mobile) {
        super("Mobile already existed: [" + mobile + "]");
    }
}
