package wangzhongqiu.spring.core.exception.base;


public class InvalidBorrowerException extends BusinessBaseException {

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
        NOT_MATCH_LOAN() {
            @Override
            public String toString() {
                return "not match loan";
            }
        }
    }

    public InvalidBorrowerException(Integer userId, Reason reason) {
        super(String.format("Borrower %d is %s", userId, reason.toString()));
    }

    public InvalidBorrowerException(Integer userId, Integer loanId, Reason reason) {
        super(String.format("Borrower %d is %s %d", userId, reason.toString(), loanId));
    }

}
