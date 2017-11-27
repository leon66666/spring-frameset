package wangzhongqiu.spring.core.exception.base;

/**
 * Created by wzq on 2017/6/6.
 */
public class SecurityCertificationException extends BusinessBaseException{
    private static final long serialVersionUID = 5381818909078435263L;

    public SecurityCertificationException() {
        super("安全认证失败");
    }
}
