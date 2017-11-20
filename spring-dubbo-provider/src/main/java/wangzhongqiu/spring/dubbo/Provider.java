package wangzhongqiu.spring.dubbo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wangzhongqiu
 */
public class Provider {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dubbo-provider.xml");
        LockSupport.park();
    }
}
