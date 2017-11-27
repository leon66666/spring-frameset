/**
 * 
 * 
 */
package wangzhongqiu.spring.core.exception;

/**
 * 贷后业务处理异常
 */
public class AmqueAmountException extends RuntimeException {

    public AmqueAmountException() {
        super();
    }

    public AmqueAmountException(String msg) {
        super(msg);
    }

    public AmqueAmountException(String msg, Exception cause) {
        super(msg, cause);
    }
}
