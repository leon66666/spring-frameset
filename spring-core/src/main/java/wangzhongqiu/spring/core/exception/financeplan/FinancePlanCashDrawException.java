/**
 * 
 * 
 */
package wangzhongqiu.spring.core.exception.financeplan;

/**
 * 理财计划自动提现 exception
 * 
 * 
 */
public class FinancePlanCashDrawException extends RuntimeException {

    private static final long serialVersionUID = -576761749020227512L;

    public FinancePlanCashDrawException() {
        super();
    }

    public FinancePlanCashDrawException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public FinancePlanCashDrawException(String arg0) {
        super(arg0);
    }

    public FinancePlanCashDrawException(Throwable arg0) {
        super(arg0);
    }

}
