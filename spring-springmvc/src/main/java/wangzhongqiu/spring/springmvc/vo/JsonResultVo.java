package wangzhongqiu.spring.springmvc.vo;

import zhongqiu.javautils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Json结果
 */
public class JsonResultVo {

    /* 成功状态码 */
    public static final int SUCCESS = 0;
    /* 失败状态码 */
    public static final int ERROR = 1;

    public static final int INPROCESS=2;

    /* 状态码 */
    protected int status = SUCCESS;
    /* 消息 */
    protected String message;
    /* 数据集 */
    protected Map<String, Object> data = new HashMap<String, Object>();

    private Object businessData;

    /**
     * 构建一个成功结果的对象
     *
     * @return
     */
    public static JsonResultVo buildSuccessVo(String message) {
        JsonResultVo vo = new JsonResultVo();
        vo.setStatus(SUCCESS);
        vo.setMessage(message);
        return vo;
    }

    /**
     * 构建一个成功结果的对象
     *
     * @return
     */
    public static JsonResultVo buildSuccessVo() {
        return buildSuccessVo(null);
    }

    /**
     * 构建一个失败结果的对象
     *
     * @param status
     * @param message
     * @return
     */
    public static JsonResultVo buildErrorVo(int status, String message) {
        JsonResultVo vo = new JsonResultVo();
        vo.setStatus(status);
        vo.setMessage(message);
        return vo;
    }

    /**
     * 构建一个失败结果的对象
     *
     * @param message
     * @return
     */
    public static JsonResultVo buildErrorVo(String message) {
        return buildErrorVo(ERROR, message);
    }

    /**
     * 构建一个失败结果的对象
     *
     * @return
     */
    public static JsonResultVo buildErrorVo() {
        return buildErrorVo(ERROR, null);
    }

    /**
     * 增加数据
     *
     * @param key
     * @param value
     */
    public JsonResultVo addData(String key, Object value) {
        data.put(key, value);
        return this;
    }

    /**
     * 增加分页信息
     *
     * @param pageIndex
     * @param totalPage
     */
    public JsonResultVo addPageInfo(int pageIndex, int totalPage) {
        if (data == null) {
            data = new HashMap<String, Object>();
        }
        data.put("pageIndex", pageIndex);
        data.put("totalPage", totalPage);
        return this;
    }
    
    public JsonResultVo addPageInfo(int pageIndex, int totalPage, int totalCount) {
        if (data == null) {
            data = new HashMap<String, Object>();
        }
        data.put("pageIndex", pageIndex);
        data.put("totalPage", totalPage);
        data.put("totalCount", totalCount);
        return this;
    }

    /**
     * 返回JSON字符串
     *
     * @return
     */
    public String toJson() {
        return JsonUtil.beanToJson(this, false);
    }
    
    /**
     * 返回JSON字符串，并将日期格式化为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public String toJsonFormatDate() {
    	return JsonUtil.beanWithDateToJson(this, "yyyy-MM-dd HH:mm:ss");
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Object getBusinessData() {
        return businessData;
    }

    public void setBusinessData(Object businessData) {
        this.businessData = businessData;
    }
}
