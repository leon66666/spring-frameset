package wangzhongqiu.spring.core.exception;


public class InternalMgmtSystemException extends ServiceException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1596666569389811668L;

    public InternalMgmtSystemException(String msg, Throwable cause) {
        super("INTERNAL_MGMT_SYSTEM_EXCEPTION: " + msg, cause);
    }
}
