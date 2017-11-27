package wangzhongqiu.spring.core.exception;


public class InvalidSystemSettingValueException extends RuntimeException {

    private static final long serialVersionUID = 6370182559115846408L;

    public InvalidSystemSettingValueException(String value, Class<?> type) {
        super("<" + value + "> is not a valid value of type <" + type + ">");
    }
}
