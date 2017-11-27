package wangzhongqiu.spring.core.exception;

public class AlreadyBeLenderException extends ServiceException {

    private static final long serialVersionUID = 1019535317861432290L;

    public AlreadyBeLenderException(String userId) {
        super("用户id[" + userId + "]已经有成为理财人的优惠券，请修改优惠券类型再添加！");
    }
}
