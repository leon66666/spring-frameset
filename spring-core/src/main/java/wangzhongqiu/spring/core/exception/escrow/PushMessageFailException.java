package wangzhongqiu.spring.core.exception.escrow;

/**
 * 存管信息实时推送接口推送失败异常类
 */
public class PushMessageFailException extends Exception {

	private static final long serialVersionUID = -2874903229644681764L;
	
    public PushMessageFailException(String message) {
        super(message);
    }
}
