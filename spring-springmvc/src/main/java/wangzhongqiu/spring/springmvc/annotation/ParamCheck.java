package wangzhongqiu.spring.springmvc.annotation;

import wangzhongqiu.spring.springmvc.enums.ParamStatusEnum;
import wangzhongqiu.spring.springmvc.validator.BaseParamValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface ParamCheck {

    /**
     * 验证错误错误消息枚举
     */
    public ParamStatusEnum msg() default ParamStatusEnum.NULL;

    /**
     * 参数验证器
     *
     * @return
     */
    public Class<? extends BaseParamValidator> validator();

    /**
     * 是否能为Null ， 为true表示不能为Null ， false表示能够为Null
     */
    public boolean notNull() default false;

    /**
     * 是否能为空 ， 为true表示不能为Null ， false表示能够为空
     */
    public boolean notBlank() default false;

    /**
     * 正则验证
     */
    public String regStr() default "";

    /**
     * 最大长度 ， 用户验证字符串
     */
    public int maxLen() default -1;

    /**
     * 最小长度 ， 用户验证字符串
     */
    public int minLen() default -1;

}
