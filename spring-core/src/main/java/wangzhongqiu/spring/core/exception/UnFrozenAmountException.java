package wangzhongqiu.spring.core.exception;

/**
 * 解冻金额异常
 */
public class UnFrozenAmountException extends ServiceException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UnFrozenAmountException(String msg) {
		super(msg);
	}
}
