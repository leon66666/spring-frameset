package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wangzhongqiu.spring.amqp.CommonProducer;
import wangzhongqiu.spring.core.amqp.ConsumerMessageDTO;

/**
 * @author wangzhongqiu
 * @date 2017/11/2.
 */
public class AmqpConsumerTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-amqp.xml");
        final CommonProducer commonProducer = applicationContext.getBean(CommonProducer.class);
        ConsumerMessageDTO consumerMessageDTO = new ConsumerMessageDTO();
        commonProducer.send(consumerMessageDTO);
    }
}
