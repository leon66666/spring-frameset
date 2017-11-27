/**
 * 
 * 
 */
package wangzhongqiu.spring.core.exception.financeplan;

/**
 * 理财计划自动提现 业务异常
 */
public class FinancePlanCashDrawBusinessException extends RuntimeException {

    private static final long serialVersionUID = -5985949920711776798L;

    public FinancePlanCashDrawBusinessException() {
        super();
    }

    public FinancePlanCashDrawBusinessException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public FinancePlanCashDrawBusinessException(String arg0) {
        super(arg0);
    }

    public FinancePlanCashDrawBusinessException(Throwable arg0) {
        super(arg0);
    }
    
    

}
