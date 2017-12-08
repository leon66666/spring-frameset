package wangzhongqiu.spring.springmvc.validator;

import wangzhongqiu.spring.core.utils.CallbackResult;
import wangzhongqiu.spring.springmvc.annotation.ParamCheck;

/**
 * Interface for parameter validator 
 * 
 * 
 */
public interface ParamValidator {
    public CallbackResult validate(Object arg, String paramName, String paramType, ParamCheck check) throws Exception;
}

