package wangzhongqiu.spring.core.exception;

/**
 * 目前用于测试hibernate lazy loading的自定义异常
 * 属于uncheck exception
 * 
 */
public class InvalidValueRuntimeException extends ServiceRuntimeException {

    private static final long serialVersionUID = 4520182559115841108L;

    public InvalidValueRuntimeException() {
        super();
    }

    public InvalidValueRuntimeException(String value, Class<?> type) {
        super("<" + value + "> is not a valid value of type <" + type + ">");
    }
}
