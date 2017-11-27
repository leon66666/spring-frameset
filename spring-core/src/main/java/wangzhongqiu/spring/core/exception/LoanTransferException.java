/**
 * 
 * 
 */
package wangzhongqiu.spring.core.exception;

/**
 * 债权转让的所有异常
 * 
 * 
 */
public class LoanTransferException extends ServiceException {
    private static final long serialVersionUID = 4037948635965497068L;

    public LoanTransferException(String message) {
        super(message);
    }
}
