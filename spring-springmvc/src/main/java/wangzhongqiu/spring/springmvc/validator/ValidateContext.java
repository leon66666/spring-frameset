package wangzhongqiu.spring.springmvc.validator;

import java.util.HashMap;
import java.util.Map;

public class ValidateContext {

    private Map<String, Object> rootContext = new HashMap<String, Object>();
    private Map<String, String> parameters;

    public Object getObj(String key) {
        return rootContext.get(key);
    }

    public void setObj(String key, Object value) {
        rootContext.put(key, value);
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
    
}
