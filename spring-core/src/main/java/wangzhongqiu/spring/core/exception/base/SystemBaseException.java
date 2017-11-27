package wangzhongqiu.spring.core.exception.base;

public class SystemBaseException extends BaseException {

    private static final long serialVersionUID = 2339727888309259052L;

    protected SystemBaseException(String message) {
        super(message);
    }

    protected SystemBaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
