package wangzhongqiu.spring.core.exception;


public class AlreadyFavoritedException extends ServiceException {

    private static final long serialVersionUID = 178861044126289205L;

    public AlreadyFavoritedException(int userID) {
        super("Already favorited by user <" + userID + ">");
    }
}
