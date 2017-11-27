package wangzhongqiu.spring.core.exception;


public class UsernameExistedException extends ServiceException {

    private static final long serialVersionUID = -5747820702095702746L;

    public UsernameExistedException(String username) {
        super("Username already existed: [" + username + "]");
    }
}
