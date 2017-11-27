package wangzhongqiu.spring.core.exception;


public class InBlackListException extends ServiceException {

    private static final long serialVersionUID = 7646371608667706975L;

    public InBlackListException(String value) {
        super("ID / Mail / Mobile in black list: [" + value + "]");
    }
}
