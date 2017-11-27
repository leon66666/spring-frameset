package wangzhongqiu.spring.core.exception;

/**
 * 调用HttpRequestClient进行处理时的异常
 * 
 * 
 */
public class HttpRequestException extends Exception {
    public HttpRequestException(String msg) {
        super(msg);
    }

    public HttpRequestException(String msg, Exception e) {
        super(msg, e);
    }
}
