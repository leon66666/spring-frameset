package wangzhongqiu.spring.springmvc.enums;

import wangzhongqiu.spring.core.utils.BusinessResult;
import wangzhongqiu.spring.core.utils.EnumResult;
import wangzhongqiu.spring.springmvc.utils.Message;
import zhongqiu.javautils.JavaEnumUtil;

import java.util.Locale;

/**
 * 参数验证异常消息枚举
 */
public enum ParamStatusEnum implements EnumResult, BusinessResult {

    NOT_BLANK_DEFAULT(10000, Message.resources.getMessage(10000 + "", null, Locale.getDefault())),
    MAX_LEN_DEFAULT(11000, Message.resources.getMessage(11000 + "", null, Locale.getDefault())),
    MIN_LEN_DEFAULT(12000, Message.resources.getMessage(12000 + "", null, Locale.getDefault())),
    REG_DEFAULT(13000, Message.resources.getMessage(13000 + "", null, Locale.getDefault())),
    DEL_BANK_BLANK(3515, Message.resources.getMessage(3515 + "", null, Locale.getDefault())),
    FINACEPLAN_AMOUNT_REQUIRED(3404, Message.resources.getMessage(3404 + "", null, Locale.getDefault())),

    SUCCESS(0, "%1$s"),
    FAILURE(-99, "%1$s"),
    NULL(0, null);


    private int code;

    private String description;

    /**
     * 构造函数
     *
     * @param code        编码
     * @param description 说明
     */
    private ParamStatusEnum(int code, String description) {
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
    public static ParamStatusEnum valueOf(int code) {
        Object obj = JavaEnumUtil.get(ParamStatusEnum.class.getName() + code);
        if (null != obj) {
            return (ParamStatusEnum) obj;
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
        return code == RequestStatusEnum.SUCCESS.getCode() ? true : false;
    }

    @Override
    public boolean isFailure() {
        return !isSuccess();
    }
}
