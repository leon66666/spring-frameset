package wangzhongqiu.spring.springmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD})
public @interface ServiceParamCheck {

    /**
     * SpEL expression
     */
    public String spel() default "";

}
