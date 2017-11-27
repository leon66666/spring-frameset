package wangzhongqiu.spring.core.exception.base;

public class InvalidShareException extends BusinessBaseException {

    private static final long serialVersionUID = 412780760041309614L;

    public enum Reason {
        SHARE_NOT_ENOUGH() {
            @Override
            public String toString() {
                return "Share not enough";
            }
        }
    }

    public InvalidShareException(Reason reason) {
        super(reason.toString());
    }

}
