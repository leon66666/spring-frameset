package wangzhongqiu.spring.amqp.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wangzhongqiu
 * @date 2017/11/2.
 */
public class AmqpConsumerTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-amqp-consumer.xml");
        System.out.println("启动成功");
    }
}
