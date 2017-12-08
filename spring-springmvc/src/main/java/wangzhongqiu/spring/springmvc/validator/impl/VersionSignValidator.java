package wangzhongqiu.spring.springmvc.validator.impl;

import wangzhongqiu.spring.core.utils.CallbackResult;
import wangzhongqiu.spring.core.utils.CallbackResultUtil;
import wangzhongqiu.spring.springmvc.annotation.Validator;
import wangzhongqiu.spring.springmvc.enums.RequestStatusEnum;
import wangzhongqiu.spring.springmvc.validator.BaseCommonValidator;
import wangzhongqiu.spring.springmvc.validator.ValidateContext;

@Validator("versionSignValidator")
public class VersionSignValidator extends BaseCommonValidator {

	@Override
	public CallbackResult validate(ValidateContext context) throws Exception {
		if (!"1.0".equals(context.getParameters().get("version").toString())) {
			return CallbackResultUtil.wrapFailure(RequestStatusEnum.INVALID_SIGN);
		}
		return CallbackResult.success();
	}

}
