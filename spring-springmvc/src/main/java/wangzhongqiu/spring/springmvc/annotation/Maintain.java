package wangzhongqiu.spring.springmvc.annotation;

import java.lang.annotation.*;

/**
 * 
 * Date: 14/12/2
 * Time: 下午2:09
 * Func:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Maintain {

    String value() default "";
}
