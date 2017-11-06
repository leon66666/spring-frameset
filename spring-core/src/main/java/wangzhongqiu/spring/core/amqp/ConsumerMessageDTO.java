package wangzhongqiu.spring.core.amqp;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author wangzhongqiu
 *         Created on 29/09/2017.
 * @description:
 */
public class ConsumerMessageDTO implements Serializable{

    private String jsonStr;

    private Class clazz;

    public ConsumerMessageDTO(){

    }

    public ConsumerMessageDTO(Object o){
        this.clazz = o.getClass();
        this.jsonStr = JSONObject.toJSONString(o);
    }

    public Object getObject(){
        return JSONObject.parseObject(jsonStr, clazz);
    }
    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
