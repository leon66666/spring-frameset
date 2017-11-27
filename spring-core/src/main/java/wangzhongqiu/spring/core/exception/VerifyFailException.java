package wangzhongqiu.spring.core.exception;

/**
 * 验证签名异常,指进行验证的过程中抛出了异常
 * 
 * 
 */
public class VerifyFailException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 2130606388186665799L;

    public VerifyFailException(String msg) {
        super(msg);
    }

    public VerifyFailException(String msg, Exception e) {
        super(msg, e);
    }
}
