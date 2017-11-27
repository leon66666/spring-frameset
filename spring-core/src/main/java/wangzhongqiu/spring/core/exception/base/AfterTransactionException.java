package wangzhongqiu.spring.core.exception.base;

public class AfterTransactionException extends SystemBaseException {

    private static final long serialVersionUID = 7235084056035460940L;

    public enum Reason {
        COMMIT {
            @Override
            public String toString() {
                return "failed after commit excute in transaction[%s]";
            }
        },
        ROLLBACK {
            @Override
            public String toString() {
                return "failed after rollback excute in transaction[%s]";
            }
        };
    }

    public AfterTransactionException(Reason reason, String currentTransactionName) {
        super(String.format(reason.toString(), currentTransactionName));
    }

}
