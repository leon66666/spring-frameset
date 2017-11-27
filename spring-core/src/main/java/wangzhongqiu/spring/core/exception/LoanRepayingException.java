package wangzhongqiu.spring.core.exception;


public class LoanRepayingException extends ServiceException {
    private static final long serialVersionUID = 1173227521494804487L;

    public LoanRepayingException(String msg) {
        super(msg+"标的正在还款，请稍后操作");
    }

}
