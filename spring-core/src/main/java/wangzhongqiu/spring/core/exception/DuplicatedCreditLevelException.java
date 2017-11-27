package wangzhongqiu.spring.core.exception;


public class DuplicatedCreditLevelException extends ServiceException {

    private static final long serialVersionUID = -8796714694386194086L;

    public DuplicatedCreditLevelException(String name) {
        super("Credit level <" + name + "> already exists");
    }
}
