package wangzhongqiu.spring.core.exception;

/**
 * 目前用于检查优惠券使用/冻结时的异常
 * 属于uncheck exception
 * 
 */
public class CouponRuntimeException extends ServiceRuntimeException {

    private static final long serialVersionUID = 4520182559115841108L;

    public CouponRuntimeException() {
        super();
    }

    public CouponRuntimeException(String msg) {
        super(msg);
    }
}
