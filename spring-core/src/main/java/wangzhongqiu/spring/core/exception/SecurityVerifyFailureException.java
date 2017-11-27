package wangzhongqiu.spring.core.exception;

/**
 * 验证不通过异常
 * 
 * 
 */
public class SecurityVerifyFailureException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -2867872558042348425L;

    public SecurityVerifyFailureException(String msg) {
        super(msg);
    }
}
