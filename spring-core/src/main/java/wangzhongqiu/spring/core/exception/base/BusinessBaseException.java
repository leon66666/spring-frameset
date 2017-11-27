package wangzhongqiu.spring.core.exception.base;

public class BusinessBaseException extends BaseException {

    private static final long serialVersionUID = 2339727888309259052L;

    protected BusinessBaseException(String message) {
        super(message);
    }

    protected BusinessBaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
