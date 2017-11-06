package wangzhongqiu.spring.core.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhongqiu
 *         Created on 29/09/2017.
 * @description:
 */
public abstract class BaseConsumer implements ChannelAwareMessageListener {

    private static Map<String, Integer> RETRY_MAP = new HashMap<String, Integer>();
    private static Integer ALLOW_RETRY_COUNT = 3;

    @Override
    public abstract void onMessage(Message message, Channel channel) throws Exception;

    /**
     * 手动应答，没有异常或是数据体异常不进行处理
     * @param message
     */
    protected void ackForSuccess(Channel channel, Message message, String uuid)throws Exception{
        if(uuid != null) {
            removeCountForMessage(uuid);
        }
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 手动应答，其他异常，重试次数已超则重新发送至队尾
     * @param message
     */
    protected void ackToRePublish(Channel channel, Message message, String uuid)throws Exception{
        removeCountForMessage(uuid);
        //ack返回false，并重新回到队列尾部
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        //重新发送消息到队尾
        channel.basicPublish(message.getMessageProperties().getReceivedExchange(),
                message.getMessageProperties().getReceivedRoutingKey(), MessageProperties.PERSISTENT_TEXT_PLAIN,
                message.getBody());
    }

    /**
     * 手动应答，其他异常，进行重试
     * @param message
     */
    protected void unAck(Channel channel, Message message, String uuid)throws Exception{
        int count = addCountForMessage(uuid);
        if(count >= ALLOW_RETRY_COUNT){
            ackToRePublish(channel, message, uuid);
        }else {
            //ack返回false，并重新回到队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    /**
     * 递增重试次数
     * @param uuid
     * @return
     */
    protected int addCountForMessage(String uuid){
        int count = 0;
        if(RETRY_MAP.containsKey(uuid)){
            count = RETRY_MAP.get(uuid);
        }
        count ++;
        RETRY_MAP.put(uuid, count);
        return count;
    }

    /**
     * 清除重试次数
     * @param uuid
     */
    protected void removeCountForMessage(String uuid){
        if(uuid != null){
            if(RETRY_MAP.containsKey(uuid)){
                RETRY_MAP.remove(uuid);
            }
        }
    }
}
