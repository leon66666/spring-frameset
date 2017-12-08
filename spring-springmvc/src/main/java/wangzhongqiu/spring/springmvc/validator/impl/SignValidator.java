package wangzhongqiu.spring.springmvc.validator.impl;


import wangzhongqiu.spring.core.utils.CallbackResult;
import wangzhongqiu.spring.core.utils.CallbackResultUtil;
import wangzhongqiu.spring.springmvc.annotation.Validator;
import wangzhongqiu.spring.springmvc.enums.RequestStatusEnum;
import wangzhongqiu.spring.springmvc.validator.BaseCommonValidator;
import wangzhongqiu.spring.springmvc.validator.ValidateContext;

@Validator("signValidator")
public class SignValidator extends BaseCommonValidator {

    @Override
    public CallbackResult validate(ValidateContext context) throws Exception {
        return CallbackResult.success();
    }
}
