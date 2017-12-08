package wangzhongqiu.spring.springmvc.validator.impl;

import com.hoomsun.common.CouponType;
import com.hoomsun.framework.service.CallbackResult;
import com.hoomsun.framework.service.util.CallbackResultUtil;
import com.hoomsun.mobile.annotation.Validator;
import com.hoomsun.mobile.enums.RequestStatusEnum;
import com.hoomsun.mobile.validator.ServiceValidator;
import com.hoomsun.mobile.validator.ValidateContext;
import com.hoomsun.model.Coupon;
import com.hoomsun.model.CouponBatch;
import com.hoomsun.model.Point;
import com.hoomsun.service.PointService;
import com.hoomsun.service.coupon.CouponServices;
import com.hoomsun.util.StringUtil;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Titile: PointsNotEnoughValidator
 * 
 * @Description: <br>
 * <br>
 * 
 * 
 */
@Validator("pointsNotEnoughValidator")
public class PointsNotEnoughValidator extends ServiceValidator {

    @Resource
    private PointService pointService;
    @Resource
    private CouponServices couponServices;

    enum Paras{
        amount,userId,couponId
    }

    public static ValidateContext buildContext(String userId,String couponId,String amount){
        ValidateContext context = new ValidateContext();
        Map<String,String> parameters = new HashMap();
        parameters.put(Paras.amount.name(),amount);
        parameters.put(Paras.userId.name(),userId);
        parameters.put(Paras.couponId.name(),couponId);
        context.setParameters(parameters);
        return context;
    };

    @Override
    public CallbackResult validate(ValidateContext context) throws Exception {

        BigDecimal amount = new BigDecimal(StringUtil.getString(context.getParameters().get(Paras.amount.name())));
        Point p = pointService.getPointByUserId(Integer.valueOf(context.getParameters().get(Paras.userId.name())));
        String couponId = context.getParameters().get(Paras.couponId.name());

        BigDecimal couponValue = BigDecimal.ZERO;
        if(StringUtils.isNotBlank(couponId)){

            Coupon coupon = couponServices.getCouponById(Integer.parseInt(couponId));
            CouponBatch cb = couponServices.getCouponBatchByCoupon(Integer.parseInt(couponId));

            //如果是抵扣券 则计算抵扣金额
            if(CouponType.DISCOUNT.equals(cb.getCouponType())){
                //  注释因为数据库修改字段报错的属性  couponValue = CalculateUtil.caculateActualValueForDiscountCoupon(cb.getDiscountRate(), amount, coupon.getValue());
            }
            //如果是现金券 则计算金额
            if(CouponType.VOUCHER.equals(cb.getCouponType())){
                couponValue = couponServices.getCouponValue(Integer.parseInt(couponId));
                couponValue = couponValue.compareTo(amount)>=0 ? amount : couponValue;
            }
        }
        //可用余额+红包金额 < 投资金额
        if(couponValue.add(new BigDecimal(p.getAvailablePoints()+"")).compareTo(amount) < 0){
            return CallbackResultUtil.wrapFailure(RequestStatusEnum.MONEY_NOT_ENOUGH);
        }
        return CallbackResult.success();
    }
}
