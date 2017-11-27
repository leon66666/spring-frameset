package wangzhongqiu.spring.core.exception;


public class RegisterSendCodeOutOfNumException extends ServiceException {

    private static final long serialVersionUID = 281073942266998562L;

    public RegisterSendCodeOutOfNumException(String mobile, String ip) {
        super("Register Send Code Out Of Num. ip:" + ip + " mobile:" + mobile);
    }
}
