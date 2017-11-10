package wangzhongqiu.spring.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author wangzhongqiu
 *         Created on 2017/10/31.
 * @description:继承RabbitTemplate.ReturnCallback,消息发送失败返回监听器
 */
public class ReturnCallBackListener implements RabbitTemplate.ReturnCallback {

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

    }
}