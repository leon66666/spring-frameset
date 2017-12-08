package wangzhongqiu.spring.springmvc.validator.impl;

import com.hoomsun.framework.service.CallbackResult;
import com.hoomsun.framework.service.util.CallbackResultUtil;
import com.hoomsun.mobile.annotation.Validator;
import com.hoomsun.mobile.common.CommonUtils;
import com.hoomsun.mobile.enums.RequestStatusEnum;
import com.hoomsun.mobile.validator.BaseCommonValidator;
import com.hoomsun.mobile.validator.ValidateContext;

@Validator("signValidator")
public class SignValidator extends BaseCommonValidator {

    @Override
    public CallbackResult validate(ValidateContext context) throws Exception{
       
        
        if (!CommonUtils.checkSign(context)) {
            return CallbackResultUtil.wrapFailure(RequestStatusEnum.INVALID_SIGN);
        }
        
        return CallbackResult.success();
    }
}
