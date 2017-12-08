package wangzhongqiu.spring.springmvc.validator.impl;

import wangzhongqiu.spring.core.model.User;
import wangzhongqiu.spring.core.utils.CallbackResult;
import wangzhongqiu.spring.core.utils.CallbackResultUtil;
import wangzhongqiu.spring.springmvc.annotation.Validator;
import wangzhongqiu.spring.springmvc.enums.RequestStatusEnum;
import wangzhongqiu.spring.springmvc.validator.BaseCommonValidator;
import wangzhongqiu.spring.springmvc.validator.ValidateContext;
import zhongqiu.javautils.StringUtil;

@Validator("loginValidator")
public class LoginValidator extends BaseCommonValidator {

    @Override
    public CallbackResult validate(ValidateContext context) throws Exception{
        
        User user = this.getUser(context);
        if (StringUtil.isEmpty(user)) {
            return CallbackResultUtil.wrapFailure(RequestStatusEnum.NOT_LOGIN);
        }
        return CallbackResult.success();
    }
}

