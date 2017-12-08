package wangzhongqiu.spring.springmvc.validator.impl;

import wangzhongqiu.spring.core.utils.CallbackResult;
import wangzhongqiu.spring.core.utils.CallbackResultUtil;
import wangzhongqiu.spring.redis.service.RedisCommonService;
import wangzhongqiu.spring.springmvc.annotation.Validator;
import wangzhongqiu.spring.springmvc.enums.RequestStatusEnum;
import wangzhongqiu.spring.springmvc.validator.BaseCommonValidator;
import wangzhongqiu.spring.springmvc.validator.ValidateContext;

import javax.annotation.Resource;

@Validator("systemCountingValidator")
public class SystemCountingValidator extends BaseCommonValidator {

    @Resource
    RedisCommonService redisService;

    @Override
    public CallbackResult validate(ValidateContext context) throws Exception {

        if(redisService.getIsCheckingOfAutomatedLoanService()) {
            return CallbackResultUtil.wrapFailure(RequestStatusEnum.SYS_COUNTING);
        }
        return CallbackResult.success();
    }
}
