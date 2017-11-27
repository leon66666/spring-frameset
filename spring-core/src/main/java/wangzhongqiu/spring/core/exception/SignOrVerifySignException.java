package wangzhongqiu.spring.core.exception;

/**
 * 签名或验证签名时异常
 * 
 * 
 */
public class SignOrVerifySignException extends ServiceException {
    public SignOrVerifySignException(String msg, Exception e) {
        super(msg, e);
    }
}
