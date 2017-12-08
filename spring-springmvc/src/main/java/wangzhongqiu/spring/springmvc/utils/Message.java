package wangzhongqiu.spring.springmvc.utils;

import org.springframework.context.MessageSource;

public class Message {

    public static MessageSource resources;

    public void setResources(MessageSource resources) {
        Message.resources = resources;
    }

}
