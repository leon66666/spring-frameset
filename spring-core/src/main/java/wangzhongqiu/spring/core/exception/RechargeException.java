package wangzhongqiu.spring.core.exception;


public class RechargeException extends ServiceException {

    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public enum Message {

        USER_NOT_EXIST {
            public String msg() {
                return "User not exist.";
            }
            @Override
            public int code() {
                return 1001;
            }
        },
        ACCOUNT_NOT_EXIST{
            public String msg() {
                return "Account not exist.";
            }
            @Override
            public int code() {
                return 1002;
            }
        };

        public abstract String msg();
        public abstract int code();
    }
    private static final long serialVersionUID = 2728028574587853752L;

    public RechargeException(String username, double amount, int errorCode) {
        super("Failed to recharge <" + amount + "> to [" + username + "], error code:" + errorCode);
    }

    public RechargeException(Message msg) {
        super(msg.msg());
        this.code = msg.code();

    }
    public RechargeException(String msg) {
        super(msg);
    }

    public RechargeException(Message msg,String para) {
        super(String.format(msg.msg(),para));
        this.code = msg.code();
    }
}
