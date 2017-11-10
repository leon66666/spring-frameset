package wangzhongqiu.spring.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

/**
 * @author wangzhongqiu
 *         Created on 2017/10/31.
 * @description:继承RabbitTemplate.ConfirmCallback,消息确认监听器
 */
@Service
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback {
    private Logger log = LoggerFactory.getLogger(CommonProducer.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("收到回调，成功发送到broker");
    }
}