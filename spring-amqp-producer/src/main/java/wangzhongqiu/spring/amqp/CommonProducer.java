package wangzhongqiu.spring.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wangzhongqiu.spring.core.amqp.BaseProducter;
import wangzhongqiu.spring.core.amqp.ConsumerMessageDTO;

public class CommonProducer extends BaseProducter<ConsumerMessageDTO> {

    private Logger log = LoggerFactory.getLogger(CommonProducer.class);

    @Override
    protected void init() {

    }
}
