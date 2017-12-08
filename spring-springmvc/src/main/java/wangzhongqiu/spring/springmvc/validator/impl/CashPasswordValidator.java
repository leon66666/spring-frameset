package wangzhongqiu.spring.springmvc.validator.impl;


import wangzhongqiu.spring.core.utils.CallbackResult;
import wangzhongqiu.spring.springmvc.annotation.Validator;
import wangzhongqiu.spring.springmvc.service.MobileSecurityService;
import wangzhongqiu.spring.springmvc.validator.BaseCommonValidator;
import wangzhongqiu.spring.springmvc.validator.ValidateContext;
import zhongqiu.javautils.StringUtil;

import javax.annotation.Resource;

@Validator("cashPasswordValidator")
public class CashPasswordValidator extends BaseCommonValidator {

    @Resource
    private MobileSecurityService mobileSecurityService;

    @Override
    public CallbackResult validate(ValidateContext context) throws Exception {

        return mobileSecurityService.checkCashPassword(this.getUser(context),
                StringUtil.getString(context.getParameters().get("cashPassword")),
                StringUtil.getString(context.getParameters().get("version")));

    }

}
