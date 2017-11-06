package wangzhongqiu.spring.amqp;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import wangzhongqiu.spring.core.amqp.BaseConsumer;
import wangzhongqiu.spring.core.amqp.ConsumerMessageDTO;

/**
 * @author tianye
 *         Created on 2017/10/31.
 * @description:
 */

public class InvesterConsumer extends BaseConsumer {
    public static int num = 0;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String msg = new String(message.getBody(), "UTF-8");
        ConsumerMessageDTO consumerMessageDTO = JSONObject.parseObject(msg, ConsumerMessageDTO.class);
        ackForSuccess(channel, message, message.getMessageProperties().getConsumerTag());
        num++;
        System.out.println("consumer success,total=" + num);
    }
}
