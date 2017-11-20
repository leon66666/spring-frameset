package wangzhongqiu.spring.dubbo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wangzhongqiu.spring.core.service.TestService;

/**
 * @author wangzhongqiu
 * @date 2017/11/20.
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-dubbo-consumer.xml");
        TestService testService = (TestService) context.getBean("testService");
        testService.println("hello");
    }
}
