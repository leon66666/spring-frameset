package wangzhongqiu.spring.core.exception;

/**
 * 业务状态不统一, 一般是由于不在同一个事务内更新导致
 *
 */
public class InconsistentStatusException extends RuntimeException {

    private static final long serialVersionUID = -7238129426029231029L;

    public InconsistentStatusException(String msg) {
        super(msg);
    }

}
