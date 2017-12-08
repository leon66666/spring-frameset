package wangzhongqiu.spring.springmvc.validator.impl;

import com.hoomsun.framework.service.CallbackResult;
import com.hoomsun.framework.service.util.CallbackResultUtil;
import com.hoomsun.mobile.annotation.Validator;
import com.hoomsun.mobile.enums.RequestStatusEnum;
import com.hoomsun.mobile.validator.BaseCommonValidator;
import com.hoomsun.mobile.validator.ValidateContext;
import com.hoomsun.model.User;
import com.hoomsun.util.StringUtil;

/**
 * validator implementation for login check
 * 
 * 
 * 
 */
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

