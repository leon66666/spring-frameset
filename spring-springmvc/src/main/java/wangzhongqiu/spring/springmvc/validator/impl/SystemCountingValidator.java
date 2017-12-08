package wangzhongqiu.spring.springmvc.validator.impl;

import com.hoomsun.framework.service.CallbackResult;
import com.hoomsun.framework.service.util.CallbackResultUtil;
import com.hoomsun.mobile.annotation.Validator;
import com.hoomsun.mobile.enums.RequestStatusEnum;
import com.hoomsun.mobile.service.impl.MobileServicePoolCacheService;
import com.hoomsun.mobile.validator.BaseCommonValidator;
import com.hoomsun.mobile.validator.ValidateContext;

import javax.annotation.Resource;

/**
 * 
 * @Titile: IsCheckingValidator
 * 
 * @Description: <br>
 * <br>
 * 
 * 
 */
@Validator("systemCountingValidator")
public class SystemCountingValidator extends BaseCommonValidator {

    @Resource
    MobileServicePoolCacheService redisService;

    @Override
    public CallbackResult validate(ValidateContext context) throws Exception {

        if(redisService.getIsCheckingOfAutomatedLoanService())
            return CallbackResultUtil.wrapFailure(RequestStatusEnum.SYS_COUNTING);
        return CallbackResult.success();
    }
}
