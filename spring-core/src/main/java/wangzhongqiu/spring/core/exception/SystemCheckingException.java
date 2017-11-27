package wangzhongqiu.spring.core.exception;


public class SystemCheckingException extends ServiceException {

    private static final long serialVersionUID = 4889173257815436320L;

    public SystemCheckingException() {
        super("系统正在结算中，请稍后进行还款操作");
    }
}
