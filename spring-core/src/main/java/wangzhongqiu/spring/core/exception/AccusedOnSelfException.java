package wangzhongqiu.spring.core.exception;


import wangzhongqiu.spring.core.model.User;

public class AccusedOnSelfException extends ServiceException {

    private static final long serialVersionUID = 5498391407336735664L;

    public AccusedOnSelfException(User user) {
        super("Cannot accused on itself <" + user.getUserId() + ">");
    }
}
