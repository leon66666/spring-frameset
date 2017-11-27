package wangzhongqiu.spring.core.exception;

/**
 * 冻结金额异常
 */
public class OrderRetryException extends ServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OrderRetryException(String msg) {
		super(msg);
	}
}
