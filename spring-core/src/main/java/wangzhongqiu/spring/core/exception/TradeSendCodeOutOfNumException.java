package wangzhongqiu.spring.core.exception;


public class TradeSendCodeOutOfNumException extends ServiceException {

    private static final long serialVersionUID = 281073942266998562L;

    public TradeSendCodeOutOfNumException(String mobile, String key) {
        super("Trade Send Code Out Of Num. key:" + key + " mobile:" + mobile);
    }
}
