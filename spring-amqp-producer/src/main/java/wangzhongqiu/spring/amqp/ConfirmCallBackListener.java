package wangzhongqiu.spring.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

/**
 * @author wangzhongqiu
 *         Created on 2017/10/31.
 * @description:继承RabbitTemplate.ConfirmCallback,消息确认监听器
 */
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

    }
}