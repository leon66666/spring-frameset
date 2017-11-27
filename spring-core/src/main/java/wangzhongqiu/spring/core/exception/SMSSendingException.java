package wangzhongqiu.spring.core.exception;


public class SMSSendingException extends ServiceException {

    private static final long serialVersionUID = 2853809932739464857L;
    private String errorCode;

    public SMSSendingException(String mobileNo, String errorCode, String seqId) {
        super("Filed to send SMS to <" + mobileNo + ">, error code <" + errorCode + ">, seqId:" + seqId);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
