package wangzhongqiu.spring.core.utils;

import wangzhongqiu.spring.core.exception.CommonRuntimeException;

public class ServiceException extends CommonRuntimeException {
    private static final long serialVersionUID = 6635209562022209560L;

    public ServiceException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public ServiceException(int errorCode, String message) {
        super(errorCode, message);
    }

    public ServiceException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ServiceException(int errorCode) {
        super(errorCode);
    }

    public ServiceException(int errorCode, Object[] args) {
        super(errorCode, args);
    }

    public ServiceException(int errorCode, String message, Object[] args) {
        super(errorCode, message, args);
    }

    public ServiceException(int errorCode, Throwable cause, Object[] args) {
        super(errorCode, cause, args);
    }

    public ServiceException(int errorCode, String message, Throwable cause, Object[] args) {
        super(errorCode, message, cause, args);
    }
}