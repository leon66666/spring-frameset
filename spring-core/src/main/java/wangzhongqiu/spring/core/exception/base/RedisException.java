package wangzhongqiu.spring.core.exception.base;

public class RedisException extends SystemBaseException {

    private static final long serialVersionUID = 4695878947691187973L;

    public enum Action {
        LOAD_SCRIPT() {
            @Override
            public String toString() {
                return "load script ";
            }
        },
        EXECUTE_SCRIPT() {
            @Override
            public String toString() {
                return "execute script ";
            }
        },
        ROLLBACK_LOAN_BIDDING() {
            @Override
            public String toString() {
                return "rollback loan bidding ";
            }
        },
        ROLLBACK_BUY_LOANTRANSFER() {
            @Override
            public String toString() {
                return "rollback buy loantransfer ";
            }
        },
        GET_KEY() {
            @Override
            public String toString() {
                return "get key ";
            }
        },
        PARSE_VALUE() {
            @Override
            public String toString() {
                return "parse value ";
            }
        },
        NIL_VALUE() {
            @Override
            public String toString() {
                return "miss value ";
            }
        },
        SETNX_VALUE() {
            @Override
            public String toString() {
                return "setnx value ";
            }
        },
        INSUFFICIENT() {
            @Override
            public String toString() {
                return "less than zero ";
            }
        },
        LESS_THAN_ZERO() {
            @Override
            public String toString() {
                return "insufficient ";
            }
        },
        LEFT_AMOUNT_LESS_THAN_100() {
            @Override
            public String toString() {
                return "left amount less than 100 ";
            }
        },
        EXPIRE_FAILED() {
            @Override
            public String toString() {
                return "expire failed ";
            }
        }
    }
    public RedisException(String action){
        super("Redis failed to " + action);
    }

    public RedisException(Throwable cause) {
        super("Redis failed ", cause);
    }

    public RedisException(String action, Throwable cause) {
        super("Redis failed to " + action, cause);
    }

    public RedisException(String action, String key, Throwable cause) {
        super("Redis failed to " + action + " " + key, cause);
    }

}
