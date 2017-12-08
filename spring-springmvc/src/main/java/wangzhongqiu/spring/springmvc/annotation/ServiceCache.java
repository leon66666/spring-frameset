package wangzhongqiu.spring.springmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ServiceCache {

    /**
     * Hash Key for redis if you want to use hases store
     * If the hkey is blank, use plain key-value store by default and will not use hash to store object.
     */
   // String hkey() default ""; //not supported now

    /**
     * Plain Key redis
     */
    String key();
    
    /**
     * Expired time, default is 5 seconds.
     * 
     */
    int expired() default 5;
}
