package wangzhongqiu.spring.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import wangzhongqiu.spring.core.log.ILogger;
import wangzhongqiu.spring.core.log.ILoggerFactory;

public abstract class AbstractServiceImpl {
    protected final ILogger logger = ILoggerFactory.getLogger(getClass());

    @Autowired
    protected ServiceTemplate serviceTemplate;
}