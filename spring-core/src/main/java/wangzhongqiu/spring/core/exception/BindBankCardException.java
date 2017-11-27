package wangzhongqiu.spring.core.exception;

public class BindBankCardException extends Exception {

    public BindBankCardException(String msg) {
        super(msg);
    }

    public BindBankCardException(String msg, Throwable cause) {
        super(msg, cause);
    }

    private static final long serialVersionUID = 2453586514425965224L;

}
