package wangzhongqiu.spring.core.exception;

/**
 * 旧版手机客户端校验身份验证用(已废弃)
 * 
 */
public class ServiceSecurityException extends ServiceException {

    private static final long serialVersionUID = 4889173257815436320L;

    public ServiceSecurityException() {
        super("Security checking failed in service invocation");
    }
}
