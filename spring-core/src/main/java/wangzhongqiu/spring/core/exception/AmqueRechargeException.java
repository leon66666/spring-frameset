/**
 * 
 * 
 */
package wangzhongqiu.spring.core.exception;

/**
 * 贷后充值失败异常
 */
public class AmqueRechargeException extends RuntimeException {
    
    public AmqueRechargeException() {
        super();
    }
    
    public AmqueRechargeException(String msg) {
        super(msg);
    }
    
    public AmqueRechargeException(String msg, Exception cause) {
        super(msg, cause);
    }
}
