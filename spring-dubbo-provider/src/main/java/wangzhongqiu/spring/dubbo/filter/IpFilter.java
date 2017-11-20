package wangzhongqiu.spring.dubbo.filter;

import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class IpFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(IpFilter.class);

    private IpWhiteList ipWhiteList;

    //dubbo通过setter方式自动注入
    public void setIpWhiteList(IpWhiteList ipWhiteList) {
        this.ipWhiteList = ipWhiteList;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (!ipWhiteList.isEnabled()) {
            logger.debug("白名单禁用");
            return invoker.invoke(invocation);
        }

        String clientIp = RpcContext.getContext().getRemoteHost();
        logger.debug("访问ip为{}", clientIp);
        if (ipWhiteList.isAllowed(clientIp)) {
            return invoker.invoke(invocation);
        } else {
            return new RpcResult();
        }
    }
}