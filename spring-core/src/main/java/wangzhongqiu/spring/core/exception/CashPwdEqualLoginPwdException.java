package wangzhongqiu.spring.core.exception;


public class CashPwdEqualLoginPwdException extends ServiceException {

    private static final long serialVersionUID = -7979933196831843141L;

    public CashPwdEqualLoginPwdException() {
        super("交易密码不能与登录密码一致。");
    }
}
