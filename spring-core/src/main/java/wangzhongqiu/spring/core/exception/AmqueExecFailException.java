/**
 * 
 * 
 */
package wangzhongqiu.spring.core.exception;

/**
 * 贷后业务处理异常<p/>
 * 出此异常后,贷后业务流进入"执行失败"状态,进入复审列表,供审核人员重新审核执行<p/>
 * 异常信息可以记录备注
 */
public class AmqueExecFailException extends RuntimeException {
    
    public AmqueExecFailException() {
        super();
    }
    
    public AmqueExecFailException(String msg) {
        super(msg);
    }
    
    public AmqueExecFailException(String msg, Exception cause) {
        super(msg, cause);
    }
}
