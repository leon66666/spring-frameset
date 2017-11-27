package wangzhongqiu.spring.core.exception.base;


public class InvalidBuyerException extends BusinessBaseException {

    private static final long serialVersionUID = -7227335981610967542L;

    public enum Reason {
        FORBIDDEN() {
            @Override
            public String toString() {
                return "forbidden";
            }
        },
        NOT_QUALIFIED() {
            @Override
            public String toString() {
                return "not qualified";
            }
        },
        NOT_PASSED() {
            @Override
            public String toString() {
                return "not pass role";
            }
        },
        BUY_OWN_TRANSFER() {
            @Override
            public String toString() {
                return "can not buy own loan";
            }
        },
        BUY_OWN_LOAN_TRANSFER() {
            @Override
            public String toString() {
                return "can not buy own loan";
            }
        }
    }

    public InvalidBuyerException(Integer buyer, Reason reason) {
        super(String.format("Buyer %d is %s", buyer, reason.toString()));
    }

}
