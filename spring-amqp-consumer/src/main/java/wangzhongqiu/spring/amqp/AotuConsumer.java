package wangzhongqiu.spring.amqp;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import wangzhongqiu.spring.core.amqp.BaseConsumer;

/**
 * @author tianye
 *         Created on 2017/10/31.
 * @description:
 */
public class AotuConsumer extends BaseConsumer {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("AotuConsumer receive");
    }
}
