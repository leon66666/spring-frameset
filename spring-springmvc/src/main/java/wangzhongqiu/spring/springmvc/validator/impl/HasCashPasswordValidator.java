package wangzhongqiu.spring.springmvc.validator.impl;

import com.hoomsun.framework.service.CallbackResult;
import com.hoomsun.framework.service.util.CallbackResultUtil;
import com.hoomsun.mobile.annotation.Validator;
import com.hoomsun.mobile.enums.RequestStatusEnum;
import com.hoomsun.mobile.validator.BaseCommonValidator;
import com.hoomsun.mobile.validator.ValidateContext;
import com.hoomsun.model.User;
import com.hoomsun.service.UserService;

import javax.annotation.Resource;

@Validator("hasCashPasswordValidator")
public class HasCashPasswordValidator extends BaseCommonValidator {
	
	@Resource
	UserService userService;
    @Override
    public CallbackResult validate(ValidateContext context) throws Exception {
    	User user = this.getUser(context);
    	if(null != user){
    		user = userService.getUserById(user.getUserId());
    	}
        if (null != user && !user.hasCashPassword()) {
            return CallbackResultUtil.wrapFailure(RequestStatusEnum.HAS_NOT_CASH_PASSWORD);
        }
        return CallbackResult.success();
    }

}
