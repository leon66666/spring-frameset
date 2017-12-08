package wangzhongqiu.spring.springmvc.validator.impl;


import wangzhongqiu.spring.core.utils.CallbackResult;
import wangzhongqiu.spring.springmvc.annotation.Validator;
import wangzhongqiu.spring.springmvc.validator.BaseParamValidator;
import wangzhongqiu.spring.springmvc.validator.ValidateContext;

/**
 * This is an default validator for annotation @ParamCheck initialization,
 * cannot be extended or updated.
 * 
 * 
 * 
 */
@Validator("defaultValidator")
public final class DefaultValidator extends BaseParamValidator {

    @Override
    public CallbackResult validate(ValidateContext context) throws Exception {
        return CallbackResult.success();
    }
}
