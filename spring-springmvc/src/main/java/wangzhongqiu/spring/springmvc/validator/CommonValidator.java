package wangzhongqiu.spring.springmvc.validator;

import wangzhongqiu.spring.core.utils.CallbackResult;

/**
 * Interface for common validator 
 * 
 * 
 */
public interface CommonValidator {
    
    public CallbackResult validate(ValidateContext context) throws Exception;
}

