package wangzhongqiu.spring.springmvc.validator.impl;

import com.hoomsun.common.Constants;
import com.hoomsun.framework.service.CallbackResult;
import com.hoomsun.framework.service.util.CallbackResultUtil;
import com.hoomsun.mobile.annotation.Validator;
import com.hoomsun.mobile.enums.AccountStatusEnum;
import com.hoomsun.mobile.enums.EasyPayStatusEnum;
import com.hoomsun.mobile.validator.BaseCommonValidator;
import com.hoomsun.mobile.validator.ValidateContext;
import com.hoomsun.model.User;
import com.hoomsun.service.UserService;
import com.hoomsun.service.cache.RedisService;
import com.hoomsun.util.TimeUtil;
import org.apache.commons.lang3.math.NumberUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * The user whose age under 18 cannot recharge.
 */
@Validator("rechargeAgeValidator")
public class RechargeAgeValidator extends BaseCommonValidator {

    @Resource
    private RedisService redisService;
    @Resource
	UserService userService;

    @Override
    public CallbackResult validate(ValidateContext context) throws Exception {

    	User user = userService.getUserById(this.getUser(context).getUserId());
    	String idCard = user.getUserInfo().getIdNo();

        if (idCard == null || idCard.length() < 15) {
            return CallbackResultUtil.wrapFailure(AccountStatusEnum.ID_NUMBER_ERROR);
        }

        if (idCard.length() != 18) {
            return CallbackResultUtil.wrapFailure(AccountStatusEnum.NOT_NEW_GEN_ID);
        }

        String birthday = idCard.substring(6, 14);
        Date birth = TimeUtil.parse(birthday, TimeUtil.FMT_YYYYMMDD, null);
        if (birth == null) {
            return CallbackResultUtil.wrapFailure(AccountStatusEnum.ID_NUMBER_ERROR);
        }

        String ageStr;
        try {
            ageStr = TimeUtil.getAge(birth);
        } catch (Exception e) {
            return CallbackResultUtil.wrapFailure(AccountStatusEnum.ID_NUMBER_ERROR);
        }
        int age = NumberUtils.toInt(ageStr);
        if (age < 18) {
            //年龄小于18,需要再检查之前是否有过充值,如果有过,还是允许其继续充值
            boolean exists = redisService.sismember(Constants.RECHARGE_AND_AGE_LESSTHAN_18, user.getUserId() + "");
            if (!exists) {
                return CallbackResultUtil.wrapFailure(EasyPayStatusEnum.RECHARGE_AGE_UNDER_18);
            }
        }
        return CallbackResult.success();
    }
}
