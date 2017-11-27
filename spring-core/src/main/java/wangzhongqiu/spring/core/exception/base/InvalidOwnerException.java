package wangzhongqiu.spring.core.exception.base;


public class InvalidOwnerException extends BusinessBaseException {

    private static final long serialVersionUID = -7227335981610967542L;

    public enum Reason {
        LOAN_NOT_BELONG_SUBPOINT() {
            @Override
            public String toString() {
                return "loan %d not belong to subpoint %d";
            }
        },
        LOAN_NOT_BELONG_USER() {
            @Override
            public String toString() {
                return "loan %d not belong to user %d";
            }
        },
        LOANTRANSFER_NOT_BELONG_USER() {
            @Override
            public String toString() {
                return "loantransfer %d not belong to user %d";
            }
        }
    }

    public InvalidOwnerException(Integer objectId, Integer ownerId, Reason reason) {
        super(String.format(reason.toString(), objectId, ownerId));
    }
}
