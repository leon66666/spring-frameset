package wangzhongqiu.spring.amqp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wangzhongqiu.spring.core.amqp.ConsumerMessageDTO;

/**
 * @author wangzhongqiu
 * @date 2017/11/2.
 */
public class AmqpProducerTest {
    public static int num = 0;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-amqp-producer.xml");
        final CommonProducer commonProducer = (CommonProducer) applicationContext.getBean("commonProducer");
        for (int i = 0; i < 10; i++) {
            ConsumerMessageDTO consumerMessageDTO = new ConsumerMessageDTO();
            commonProducer.send(consumerMessageDTO);
            num++;
            System.out.println("send success,total=" + num);
        }
    }
}
