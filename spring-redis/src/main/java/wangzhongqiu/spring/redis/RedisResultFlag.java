package wangzhongqiu.spring.redis;

/**
 * 用于返回redis的值
 * 
 * 
 */
public class RedisResultFlag {

    private Boolean result = false;

    public RedisResultFlag() {
        super();
    }

    public RedisResultFlag(Boolean result) {
        this.result = result;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

}
