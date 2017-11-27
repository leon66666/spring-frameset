package wangzhongqiu.spring.core.exception;

/**
 * 签名异常,指进行签名的过程中抛出了异常
 * 
 * 
 */
public class SignFailException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = -6924409864526745823L;

    public SignFailException(String msg) {
        super(msg);
    }

    public SignFailException(String msg, Exception e) {
        super(msg, e);
    }
}
