/**
 * 
 * 
 */
package wangzhongqiu.spring.core.exception;


public class UsedWrongMethodException extends ServiceException {

    private static final long serialVersionUID = 4889173257815436320L;

    public UsedWrongMethodException() {
        super("对不起，方法调用失败，使用了错误的方法！");
    }
}
