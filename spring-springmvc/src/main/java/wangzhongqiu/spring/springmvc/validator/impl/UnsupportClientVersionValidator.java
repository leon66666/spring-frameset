package wangzhongqiu.spring.springmvc.validator.impl;

import com.hoomsun.framework.service.CallbackResult;
import com.hoomsun.framework.service.util.CallbackResultUtil;
import com.hoomsun.mobile.annotation.Validator;
import com.hoomsun.mobile.enums.RequestStatusEnum;
import com.hoomsun.mobile.validator.BaseCommonValidator;
import com.hoomsun.mobile.validator.ValidateContext;
import org.apache.commons.lang3.StringUtils;

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
