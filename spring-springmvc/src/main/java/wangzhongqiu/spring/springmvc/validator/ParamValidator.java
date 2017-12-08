package wangzhongqiu.spring.springmvc.validator;

import com.hoomsun.framework.service.CallbackResult;
import com.hoomsun.mobile.annotation.ParamCheck;

/**
 * Interface for parameter validator 
 * 
 * 
 */
public interface ParamValidator {
    public CallbackResult validate(Object arg, String paramName, String paramType, ParamCheck check) throws Exception;
}

