package wangzhongqiu.spring.core.amqp;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.PostConstruct;

/**
 * @author wangzhongqiu
 *         Created on 2017/10/30.
 * @description:
 */
public abstract class BaseProducter<T> {

    private Logger log = LoggerFactory.getLogger(BaseProducter.class);

    protected RabbitTemplate baseAmqpTemplate;

    protected String routingKey;

    @PostConstruct
    protected abstract void init();

    /**
     *
     * @param rule
     * @param <M>
     */
    public <M extends T> void send(M rule) {
        try {
            ConsumerMessageDTO message = new ConsumerMessageDTO(rule);
            baseAmqpTemplate.convertAndSend(routingKey, message);
            log.info("send:" + JSONObject.toJSONString(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RabbitTemplate getBaseAmqpTemplate() {
        return baseAmqpTemplate;
    }

    public void setBaseAmqpTemplate(RabbitTemplate baseAmqpTemplate) {
        this.baseAmqpTemplate = baseAmqpTemplate;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
}
