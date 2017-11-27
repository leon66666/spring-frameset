package wangzhongqiu.spring.core.exception;


public class UserInfoLockedException extends ServiceException {

    private static final long serialVersionUID = 1476711079312013610L;

    public UserInfoLockedException(Integer userId, String fieldName) {
        super("User <" + userId + ">'s " + fieldName + " already locked");
    }
}
