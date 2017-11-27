package wangzhongqiu.spring.core.exception;


public class AlreadyLogRechargeException extends ServiceException {

    private static final long serialVersionUID = -8970778957040402313L;

    public AlreadyLogRechargeException(String payID) {
        super("Pay <" + payID + "> already logged");
    }
}
