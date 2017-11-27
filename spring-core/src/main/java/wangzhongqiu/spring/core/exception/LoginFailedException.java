package wangzhongqiu.spring.core.exception;


public class LoginFailedException extends ServiceException {

    private static final long serialVersionUID = -629573310097896909L;

    public LoginFailedException(String username) {
        super("Login failed: [" + username + "]");
    }
}
