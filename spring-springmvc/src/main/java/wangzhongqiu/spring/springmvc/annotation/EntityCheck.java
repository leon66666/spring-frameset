package wangzhongqiu.spring.springmvc.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.PARAMETER)  
public @interface EntityCheck {  
    Class<?> value() default Object.class;
}  
