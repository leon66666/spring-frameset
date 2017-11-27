package wangzhongqiu.spring.core.exception;


public class IDNoExistedException extends ServiceException {

    private static final long serialVersionUID = -6093458508601622663L;

    public IDNoExistedException(String idNo) {
        super("idNo already existed: [" + idNo + "]");
    }
}
