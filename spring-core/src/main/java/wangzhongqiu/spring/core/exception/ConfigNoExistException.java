package wangzhongqiu.spring.core.exception;

@SuppressWarnings("serial")
public class ConfigNoExistException extends Exception {
    public ConfigNoExistException(String partner) {
        super(partner + "的配置文件未找到！");
    }
}
