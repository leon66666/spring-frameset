package wangzhongqiu.spring.springmvc.enums;

import wangzhongqiu.spring.core.utils.BusinessResult;
import wangzhongqiu.spring.core.utils.EnumResult;
import wangzhongqiu.spring.springmvc.utils.Message;
import zhongqiu.javautils.JavaEnumUtil;

import java.util.Locale;

/**
 * 
 * Date: 14/12/22
 * Time: 下午2:58
 * Func:
 */
public enum AboutStatusEnum implements EnumResult, BusinessResult {

    PLATFORM_NULL(3301, Message.resources.getMessage(3301 + "", null, Locale.getDefault())),

    CLIENT_VERSION_NULL(3311, Message.resources.getMessage(3311 + "", null, Locale.getDefault())),

    CHECK_VERSION_ERROR(3303, Message.resources.getMessage(3303 + "", null, Locale.getDefault())),

    CHANNEL_CODE_NULL(3302, Message.resources.getMessage(3302 + "", null, Locale.getDefault())),
    CHANNEL_CODE_ERROR(3315, Message.resources.getMessage(3315 + "", null, Locale.getDefault())),
    ABOUT_SUCCESS(3316, Message.resources.getMessage(3316 + "", null, Locale.getDefault())),

    HTTP_METHOD_ERROR(3313, Message.resources.getMessage(3313 + "", null, Locale.getDefault())),
    SUGGEST_NULL(3304, Message.resources.getMessage(3304 + "", null, Locale.getDefault())),
    CONTACT_NULL(3305, Message.resources.getMessage(3305 + "", null, Locale.getDefault())),
    SPECIAL_CHARACTERS(1005, Message.resources.getMessage(1005 + "", null, Locale.getDefault())),
    SUGGEST_OVER_200(1013, Message.resources.getMessage(1013 + "", null, Locale.getDefault())),
    FEED_BACK_ERROR(3306, Message.resources.getMessage(3306 + "", null, Locale.getDefault())),
    FEED_BACK_SUCCESS(33061, Message.resources.getMessage(33061 + "", null, Locale.getDefault())),

    MODEL_NUMBER_NULL(3309, Message.resources.getMessage(3309 + "", null, Locale.getDefault())),
    ADTYPE_NULL(3310, Message.resources.getMessage(3310 + "", null, Locale.getDefault())),
    SYS_VERSION_NULL(3312, Message.resources.getMessage(3312 + "", null, Locale.getDefault())),
    SCREEN_WIDTH_OR_HEIGHT_NULL(3313, Message.resources.getMessage(3313 + "", null, Locale.getDefault())),
    APP_IMG_LIST_ERROR(3308, Message.resources.getMessage(3308 + "", null, Locale.getDefault())),

    GET_NOTICE_ERROR(3314, Message.resources.getMessage(3314 + "", null, Locale.getDefault())),

    SUCCESS(0, "成功"),

    FAILURE(-99, "失败");


    private int code;

    private String description;

    @Override
    public String getDescription(Object... objects) {
        return String.format(description,objects);
    }


    /**
     * 构造函数
     *
     * @param code        编码
     * @param description 说明
     */
    private AboutStatusEnum(int code, String description) {
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
    public static AboutStatusEnum valueOf(int code) {
        Object obj = JavaEnumUtil.get(AboutStatusEnum.class.getName() + code);
        if (null != obj) {
            return (AboutStatusEnum) obj;
        }
        return FAILURE;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isSuccess() {
        return code == AboutStatusEnum.SUCCESS.code ? true : false;
    }

    @Override
    public boolean isFailure() {
        return !isSuccess();
    }
}
