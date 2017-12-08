package wangzhongqiu.spring.springmvc.enums;

import wangzhongqiu.spring.core.utils.BusinessResult;
import wangzhongqiu.spring.core.utils.EnumResult;
import wangzhongqiu.spring.springmvc.utils.Message;
import zhongqiu.javautils.JavaEnumUtil;

import java.util.Locale;

/**
 * @Title: RequestStatusEnum
 * @Description: <br>
 * <br>
 */
public enum RequestStatusEnum implements EnumResult, BusinessResult {

    //999=抱歉，该产品已经被抢光了
    AMOUNT_ZREO(999, Message.resources.getMessage(999 + "", null, Locale.getDefault())),
    //1001=对不起，请先登录。
    NOT_LOGIN(1001, Message.resources.getMessage(1001 + "", null, Locale.getDefault())),
    //1002=对不起，服务器异常。
    SERVER_ERROR(1002, Message.resources.getMessage(1002 + "", null, Locale.getDefault())),
    //1005=请勿输入特殊字符。
    ILLEGAL_CHAR(1005, Message.resources.getMessage(1005 + "", null, Locale.getDefault())),
    //1006=验证签名失败。
    INVALID_SIGN(1006, Message.resources.getMessage(1006 + "", null, Locale.getDefault())),
    //1008=系统正在结算中，请稍后再试
    SYS_COUNTING(1008, Message.resources.getMessage(1008 + "", null, Locale.getDefault())),
    //3011=对不起，请输入提现密码。
    INPUT_CASH_PASSWORD_EMPTY(3011, Message.resources.getMessage(3011 + "", null, Locale.getDefault())),
    //3019=对不起，由于提现密码输入频繁，您的提现密码已锁定，请明日再试。
    INPUT_CASH_PASSWORD_TOO_OFTEN(3019, Message.resources.getMessage(3019 + "", null, Locale.getDefault())),
    //3219=手机验证码不正确！
    VALIDATESTATUS_ERROR(3219, Message.resources.getMessage(3219 + "", null, Locale.getDefault())),
    //3408=账户余额不足，请先充值
    MONEY_NOT_ENOUGH(3408, Message.resources.getMessage(3408 + "", null, Locale.getDefault())),
    //购买过于频繁
    BUY_FREQUENT(3413, Message.resources.getMessage(3413 + "", null, Locale.getDefault())),
    //操作过于频繁
    REQUEST_TOO_OFTEN(1012, Message.resources.getMessage(1012 + "", null, Locale.getDefault())),
    //3501=请选择银行。
    BANK_EMPTY(3501, Message.resources.getMessage(3501 + "", null, Locale.getDefault())),
    //3502=请输入银行卡号。
    CARDID_EMPTY(3502, Message.resources.getMessage(3502 + "", null, Locale.getDefault())),
    //3506=%1$s
    NOT_ROLE_PASS(3506, Message.resources.getMessage(3506 + "", null, Locale.getDefault())),
    //3511=请选择银行卡。
    BANKID_EMPTY(3511, Message.resources.getMessage(3511 + "", null, Locale.getDefault())),
    //3513=请确保身份认证、手机认证、提现密码、昵称已通过。
    ROLE_NOT_PASSED(3513, Message.resources.getMessage(3513 + "", null, Locale.getDefault())),
    //351301=请先设置正确的用户昵称
    NICKNAME_NOT_PASSED(351301, Message.resources.getMessage(351301 + "", null, Locale.getDefault())),
    //351302=未设置交易密码
    HAS_NOT_CASH_PASSWORD(351302, Message.resources.getMessage(351302 + "", null, Locale.getDefault())),
    //3518=银行卡验证失败，请确认后重新输入。
    CARDID_VERITF_FALSE(3518, Message.resources.getMessage(3518 + "", null, Locale.getDefault())),
    //32251=验证码获取过于频繁，请在%1$s秒后重试。
    VALIDATE_CODE_TOO_OFTEN(32251, Message.resources.getMessage(32251 + "", null, Locale.getDefault())),
    //3802=访问论坛失败
    ACCESS_DISCUZ_ERROR(3802, Message.resources.getMessage(3802 + "", null, Locale.getDefault())),
    //30222=该用户未实名认证
    NOT_ID_AUTH(30222, Message.resources.getMessage(30222 + "", null, Locale.getDefault())),
    //3211=未绑定手机。
    NOT_BIND_MOBILE(3211, Message.resources.getMessage(3211 + "", null, Locale.getDefault())),
    //1007=参数不合法
    PARAM_ERROR(1007, Message.resources.getMessage(1007 + "", null, Locale.getDefault())),
    //1014=该版本不支持，请升级。
    UNSUPPORT_CLIENT_VERSION(1014, Message.resources.getMessage(1014 + "", null, Locale.getDefault())),
    AFTER_VARIFY(4002, Message.resources.getMessage(4002 + "", null, Locale.getDefault())),


    SUCCESS(0, "%1$s"),
    FAILURE(-99, "%1$s");


    private int code;

    private String description;

    /**
     * 构造函数
     *
     * @param code        编码
     * @param description 说明
     */
    private RequestStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
        JavaEnumUtil.put(this.getClass().getName() + code, this);
    }

    /**
     * <pre>
     * 一个便利的方法，方便使用者通过code获得枚举对象，
     * 对于非法状态，以个人处理&lt;/b&gt;
     * </pre>
     *
     * @param code
     * @return
     */
    public static RequestStatusEnum valueOf(int code) {
        Object obj = JavaEnumUtil.get(AboutStatusEnum.class.getName() + code);
        if (null != obj) {
            return (RequestStatusEnum) obj;
        }
        return FAILURE;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getDescription(Object... objects) {
        return String.format(description, objects);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isSuccess() {
        return code == RequestStatusEnum.SUCCESS.code ? true : false;
    }

    @Override
    public boolean isFailure() {
        return !isSuccess();
    }
}
