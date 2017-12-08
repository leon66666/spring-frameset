package wangzhongqiu.spring.springmvc.validator.impl;


import wangzhongqiu.spring.core.model.User;
import wangzhongqiu.spring.core.utils.CallbackResult;
import wangzhongqiu.spring.core.utils.CallbackResultUtil;
import wangzhongqiu.spring.mybatis.service.UserService;
import wangzhongqiu.spring.springmvc.annotation.Validator;
import wangzhongqiu.spring.springmvc.enums.RequestStatusEnum;
import wangzhongqiu.spring.springmvc.validator.BaseCommonValidator;
import wangzhongqiu.spring.springmvc.validator.ValidateContext;

import javax.annotation.Resource;

@Validator("hasCashPasswordValidator")
public class HasCashPasswordValidator extends BaseCommonValidator {

    @Resource
    UserService userService;

    @Override
    public CallbackResult validate(ValidateContext context) throws Exception {
        User user = this.getUser(context);
        if (null != user) {
            user = userService.getUserById(user.getUserId());
        }
        if (null != user && !user.hasCashPassword()) {
            return CallbackResultUtil.wrapFailure(RequestStatusEnum.HAS_NOT_CASH_PASSWORD);
        }
        return CallbackResult.success();
    }

}
