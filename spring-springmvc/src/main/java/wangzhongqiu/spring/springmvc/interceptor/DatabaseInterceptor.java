package wangzhongqiu.spring.springmvc.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wangzhongqiu.spring.mybatis.annotation.ReadOnlyDataSource;
import wangzhongqiu.spring.mybatis.datasource.AvailableDataSources;
import wangzhongqiu.spring.mybatis.datasource.DataSourceProvider;

import java.lang.reflect.Method;

/**
 * 只读库选择
 * @author luzongwei
 */
public class DatabaseInterceptor implements MethodInterceptor {

    private static final Logger DB_INC_LOG = LoggerFactory.getLogger(DatabaseInterceptor.class);
//    @Resource
//    private RedisService redisService;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        try {
            Method method = methodInvocation.getMethod();
            if (method != null && method.getAnnotation(ReadOnlyDataSource.class) != null) {
                DataSourceProvider.setDataSource(AvailableDataSources.READ);
//                DB_INC_LOG.info("The method 【" + method.getName() + "】 will use read-only datasource.");
            }
//            if (method != null && !method.isAnnotationPresent(Maintain.class)) {
//                if (redisService.isExist(HxbMobileConstants.MAINTENACE_STATES)) {
//                    String isMaintain = redisService.get(HxbMobileConstants.MAINTENACE_STATES);
//                    if (StringUtils.isNotBlank(isMaintain) && "true".equalsIgnoreCase(isMaintain)) {
//                        return MobileJsonResultVo.buildErrorVo(-99, Message.resources.getMessage("9999", null, Locale.getDefault()));
//                    }
//                }
//            }
            return methodInvocation.proceed();
        } finally {
            DataSourceProvider.clearDataSource();
        }
    }
}
