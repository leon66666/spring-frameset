package wangzhongqiu.spring.core.exception.base;


public class PointExistsException extends BusinessBaseException {

    private static final long serialVersionUID = -3758531134683953032L;

    public PointExistsException(Integer userId) {
        super(String.format("Point exists for user %d", userId));
    }

}
