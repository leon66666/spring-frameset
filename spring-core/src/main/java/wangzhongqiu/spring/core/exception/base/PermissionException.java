package wangzhongqiu.spring.core.exception.base;

public class PermissionException extends BusinessBaseException {

    private static final long serialVersionUID = -7152861206362985009L;

    public enum Reason {
        USER_CANNOT_TRANSFER_FINLANCEPLAN() {
            @Override
            public String toString() {
                return "用户不能转移理财计划债权";
            }
        },

        LOAN_IN_LOCK_PERIOD() {
            @Override
            public String toString() {
                return "该债权处于转让锁定期，不能转让";
            }
        },

        USER_CANNOT_CANCEL_TRANSFER_FINLANCEPLAN() {
            @Override
            public String toString() {
                return "用户不能取消理财计划转让债权";
            }
        }
    }

    public PermissionException(String cause) {
        super(cause);
    }

    public PermissionException(Reason reason) {
        super(reason.toString());
    }
}
