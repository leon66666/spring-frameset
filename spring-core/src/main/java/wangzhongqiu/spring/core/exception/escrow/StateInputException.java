package wangzhongqiu.spring.core.exception.escrow;


import wangzhongqiu.spring.core.exception.ServiceException;

public class StateInputException extends ServiceException {

    private static final long serialVersionUID = 1312481695721166859L;
    private String code;

    public enum Message {

        INVALID_PARAMETER {
            public String msg() {
                return "Parameter missed:%1$s";
            }

            @Override
            public String code() {
                return "SIE-001";
            }
        };

        public abstract String msg();

        public abstract String code();

    };

    public StateInputException(String msg) {
        super(msg);
    }

    public StateInputException(Message msg) {
        super(msg.msg());
        this.code = msg.code();
    }

    public StateInputException(Message msg,String para) {
        super(String.format(msg.msg(),para));
        this.code = msg.code();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
