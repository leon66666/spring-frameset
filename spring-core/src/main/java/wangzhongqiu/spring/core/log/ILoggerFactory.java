package wangzhongqiu.spring.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ILoggerFactory {
    public static ILogger getLogger(String name) {
        Logger logger = LoggerFactory.getLogger(name);
        ILoggerImpl ilogger = new ILoggerImpl();
        ilogger.setLogger(logger);
        return ilogger;
    }

    public static ILogger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }
}