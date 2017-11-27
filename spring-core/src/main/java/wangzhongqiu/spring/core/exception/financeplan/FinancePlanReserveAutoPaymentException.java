/**
 * 
 * 
 */
package wangzhongqiu.spring.core.exception.financeplan;

/**
 * 理财计划预定，自动扣款exception
 */
public class FinancePlanReserveAutoPaymentException extends RuntimeException {

    private static final long serialVersionUID = -576761749020227512L;

    public FinancePlanReserveAutoPaymentException() {
        super();
    }

    public FinancePlanReserveAutoPaymentException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public FinancePlanReserveAutoPaymentException(String arg0) {
        super(arg0);
    }

    public FinancePlanReserveAutoPaymentException(Throwable arg0) {
        super(arg0);
    }

}
