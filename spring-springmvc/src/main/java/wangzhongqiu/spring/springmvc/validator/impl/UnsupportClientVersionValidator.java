package wangzhongqiu.spring.springmvc.validator.impl;


import org.apache.commons.lang3.StringUtils;
import wangzhongqiu.spring.core.utils.CallbackResult;
import wangzhongqiu.spring.core.utils.CallbackResultUtil;
import wangzhongqiu.spring.springmvc.annotation.Validator;
import wangzhongqiu.spring.springmvc.enums.RequestStatusEnum;
import wangzhongqiu.spring.springmvc.validator.BaseCommonValidator;
import wangzhongqiu.spring.springmvc.validator.ValidateContext;

@Validator("unsupportClientVersionValidator")
public class UnsupportClientVersionValidator extends BaseCommonValidator {

    @Override
    public CallbackResult validate(ValidateContext context) throws Exception {

        String clientVersion = context.getParameters().get("clientVersion");
        if (StringUtils.isNumericSpace(clientVersion) && Integer.valueOf(clientVersion) >= 30300) {
            return CallbackResult.success();
        } else {
            return CallbackResultUtil.wrapFailure(RequestStatusEnum.UNSUPPORT_CLIENT_VERSION);
        }
    }
}
