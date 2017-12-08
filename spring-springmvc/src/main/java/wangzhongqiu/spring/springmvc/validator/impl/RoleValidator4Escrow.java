package wangzhongqiu.spring.springmvc.validator.impl;

import com.hoomsun.framework.service.CallbackResult;
import com.hoomsun.framework.service.util.CallbackResultUtil;
import com.hoomsun.mobile.annotation.Validator;
import com.hoomsun.mobile.enums.RequestStatusEnum;
import com.hoomsun.mobile.validator.BaseCommonValidator;
import com.hoomsun.mobile.validator.ValidateContext;
import com.hoomsun.model.User;
import com.hoomsun.service.UserService;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;

@Validator("roleValidator4Escrow")
public class RoleValidator4Escrow extends BaseCommonValidator {
    @Resource
    UserService userService;

    @Override
    public CallbackResult validate(ValidateContext context) throws Exception {
        User user = this.getUser(context);
        if(null != user){
            user = userService.getUserById(user.getUserId());
        }
        if (null != user && !isPassed(user)) {
            return CallbackResultUtil.wrapFailure(RequestStatusEnum.NICKNAME_NOT_PASSED);
        }
        return CallbackResult.success();
    }

    /**
     * 目前只需要验证昵称
     * @param user
     * @return
     */
    private boolean isPassed(User user){
        return StringUtils.isNotEmpty(user.getUsername());
    }
}
