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

@Validator("roleValidator")
public class RoleValidator extends BaseCommonValidator {

    @Resource
    UserService userService;

    @Override
    public CallbackResult validate(ValidateContext context) throws Exception {
        User user = this.getUser(context);
        /*if (null != user) {
            user = userService.selectByPrimaryKey(user.getUserId());
        }*/
        if (null != user && !user.isRolePassed()) {
            return CallbackResultUtil.wrapFailure(RequestStatusEnum.ROLE_NOT_PASSED);
        }
        return CallbackResult.success();
    }

}
