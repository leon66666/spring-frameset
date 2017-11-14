package wangzhongqiu.spring.amqp.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wangzhongqiu.spring.amqp.CommonProducer;
import wangzhongqiu.spring.core.amqp.ConsumerMessageDTO;

/**
 * @author wangzhongqiu
 * @date 2017/11/2.
 */
public class AmqpProducerTest {
    public static int num = 0;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-amqp-producer.xml");
        final CommonProducer commonProducer = applicationContext.getBean(CommonProducer.class);
        for (int i = 0; i < 1; i++) {
            ConsumerMessageDTO consumerMessageDTO = new ConsumerMessageDTO();
            commonProducer.send(consumerMessageDTO);
            num++;
            System.out.println("send success,total=" + num);
        }
    }
}
