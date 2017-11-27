package wangzhongqiu.spring.core.exception;

/**
 * 用户银行卡信息找不到
 * 
 * 
 */
public class UserBankNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7299728237809375044L;

    public UserBankNotFoundException() {
        super();
    }

    public UserBankNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserBankNotFoundException(String message) {
        super(message);
    }

    public UserBankNotFoundException(Throwable cause) {
        super(cause);
    }

}
