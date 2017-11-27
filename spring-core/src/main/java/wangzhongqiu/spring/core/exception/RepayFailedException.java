/**
 * 
 * 
 */
package wangzhongqiu.spring.core.exception;

/**
 * 还款失败
 * 
 * 
 */
public class RepayFailedException extends ServiceException {
    private static final long serialVersionUID = -6039464260718088033L;

    public RepayFailedException(String msg) {
        super(msg);
    }
}
