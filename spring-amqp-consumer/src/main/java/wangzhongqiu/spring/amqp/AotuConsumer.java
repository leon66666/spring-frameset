package wangzhongqiu.spring.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author wangzhongqiu
 *         Created on 2017/10/31.
 * @description:继承MessageListener, onMessage(Message message), 获取消息体并转换
 */
public class AotuConsumer implements MessageListener {
    public static int num = 0;

    @Override
    public void onMessage(Message message) {
        try {
            Thread.sleep(1000 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num++;
        System.out.println("AotuConsumer success,total=" + num);
    }
}
