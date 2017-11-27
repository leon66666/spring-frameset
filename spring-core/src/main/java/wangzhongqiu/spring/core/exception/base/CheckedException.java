package wangzhongqiu.spring.core.exception.base;

public class CheckedException extends BaseException {

    private static final long serialVersionUID = -7152861206362985009L;

    public CheckedException(Throwable cause) {
        super("Checked exception found", cause);
    }

}
