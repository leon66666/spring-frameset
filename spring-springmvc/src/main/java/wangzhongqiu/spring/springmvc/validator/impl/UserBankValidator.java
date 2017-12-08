package wangzhongqiu.spring.springmvc.validator.impl;

import wangzhongqiu.spring.core.utils.CallbackResult;
import wangzhongqiu.spring.springmvc.annotation.Validator;
import wangzhongqiu.spring.springmvc.validator.BaseCommonValidator;
import wangzhongqiu.spring.springmvc.validator.ValidateContext;

@Validator("userBankValidator")
public class UserBankValidator extends BaseCommonValidator {


    @Override
    public CallbackResult validate(ValidateContext context) throws Exception {
//
//        /**
//         * 一个银行只能绑定一个银行卡
//         */
//        if( StringUtil.getString("payBatch").equals("1")){
//            List<UserBank> userBankList = userBankService.getUserBankByEasypayBankCode(this.getUser(context).getUserId(), StringUtil.getString("bankCode"),
//                    PayType.QUICKPAY_MOBILE.name());
//            if (null != userBankList && userBankList.size() >= 1) {
//                UserBank ub = userBankList.get(0);
//                return CallbackResultUtil.wrapFailure(EasyPayStatusEnum.QUICKPAY_BIND_ALREADY,ub.getBankType(),StringUtil.dealCardId(ub.getCardId()));
//            }
//        }
        return CallbackResult.success();
    }

}
