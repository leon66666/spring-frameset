package wangzhongqiu.spring.core.exception;

/**
 * 
 * @Titile: RequestStatusException
 * @
 * @Description: <br>
 * <br>
 * 
 * 
 */
public class RequestStatusException extends BusinessException {

    private static final long serialVersionUID = 4423214006341068055L;

    public RequestStatusException(STATUS statusEnum) {
        super(statusEnum.message());
        this.code = statusEnum.code();
    }

    public enum STATUS {
        REQUEST_TOO_OFTEN {
            @Override
            public int code() {
                return 1000;
            }

            @Override
            public String message() {
                return "您的交易请求过于频繁";
            }
        };

        public abstract int code();

        public abstract String message();
    }

    /**
       1000 :请求过于频繁
     */
    private int code;

    public RequestStatusException() {
        super("");
    }

    public RequestStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestStatusException(String message) {
        super(message);
    }

    public RequestStatusException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
