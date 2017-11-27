package wangzhongqiu.spring.core.exception;

public class CouponBusinessException extends BusinessException {

    private static final long serialVersionUID = -8115479978371047893L;

    /**
     * 5001    "业务类型不能为空"
     * 5002    "业务类型不合法"
     * 5003    "参数不能为空"
     * 5004    "用户不存在"
     * 5005    "优惠券和批次不能同时为空"
     * 5006    "优惠券批次不存在"
     * 5007    "优惠券不存在"
     * 5008    "使用用户与优惠券兑换用户不匹配"
     * 5009    "优惠券状态不可用"
     * 5010    "优惠券不在有效期内"
     * 5011    "您输入的优惠券密码验证次数超过上限，请明日再试"
     * 5012    "您的投资金额低于优惠券最小限额"
     * 5013    "您的优惠券不能在该设备上使用，请在电脑/手机上使用"
     * 5014    "您输入的优惠券密码有误，请输入正确的密码"
     * 5015    "您使用的优惠券不适用于本产品，本次投资不能使用"
     * 5016    "批次过期时间早于当前时间"
     * 5017    "批次不可用"
     * 5018    "您已兑换该优惠券，请去我的优惠券进行查看"
     * 5019    "您的加息券不适用于该期理财计划"
     * 5020    "加息券不适用于理财计划追加"
     */
    private int code;

    public CouponBusinessException() {
        super("");
    }

    public CouponBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public CouponBusinessException(String message) {
        super(message);
    }

    public CouponBusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
