package wangzhongqiu.spring.core.exception.base;


public class LockedException extends BusinessBaseException {

    private static final long serialVersionUID = -6387433220699322407L;

    public enum Reason {
    	/** 系统清算加锁 */
        LOCKED_BY_SYSTEM() {
            @Override
            public String toString() {
                return "system liquidation";
            }
        },
        /** 正在购买债权加锁 */
        LOCKED_BY_TRANSFER() {
            @Override
            public String toString() {
                return " loan transfer";
            }
        },
        /** 正在还款加锁 */
        LOCKED_BY_REPAY() {
            @Override
            public String toString() {
                return " loan repay";
            }
        },
        /** 正在还款子账户加锁 */
        LOCKED_BY_REPAY_SUBPOINT() {
            @Override
            public String toString() {
                return " loan repay subpoint";
            }
        }
    }

    public LockedException(Integer loanId, Reason reason) {
        super(String.format("Loan %d is locked by %s", loanId, reason));
    }

    public LockedException(Reason reason) {
        super(String.format("Locked by %s", reason));
    }

    public LockedException(String reason) {
        super(reason);
    }
}
