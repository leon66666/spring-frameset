package wangzhongqiu.spring.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author wangzhongqiu
 *         Created on 2017/10/31.
 * @description:继承RabbitTemplate.ReturnCallback,消息发送失败返回监听器
 */
@Service
public class ReturnCallBackListener implements RabbitTemplate.ReturnCallback {
    private Logger log = LoggerFactory.getLogger(CommonProducer.class);

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("收到回调");
    }
}