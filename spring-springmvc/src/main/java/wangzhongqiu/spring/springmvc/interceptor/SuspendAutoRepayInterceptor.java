package wangzhongqiu.spring.springmvc.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import wangzhongqiu.spring.mybatis.annotation.ReadOnlyDataSource;
import wangzhongqiu.spring.redis.service.RedisCommonService;
import wangzhongqiu.spring.springmvc.annotation.Maintain;
import wangzhongqiu.spring.springmvc.utils.Message;
import wangzhongqiu.spring.springmvc.vo.JsonResultVo;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 在凌晨清算时暂停所有充值提现购买转让等更新金额的数据库的业务
 *
 * @author luzongwei
 */
public class SuspendAutoRepayInterceptor implements MethodInterceptor {

    /**
     * 类白名单, 不拦截清算
     */
    private List<String> classWhiteList = new ArrayList(Arrays.asList("BaseController", "LoginController", "LogoutController"));

    /**
     * 方法白名单, 不拦截清算
     */
    private List<String> methodWhiteList = new ArrayList(Arrays.asList("setUserId", "setUserAgent", "setPlatform", "setIpAddress", "toString", "isMobile"));

    @Resource
    private RedisCommonService redisService;

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();

        if (method != null
                && !classWhiteList.contains(method.getDeclaringClass().getSimpleName())
                && !methodWhiteList.contains(method.getName())
                && !method.isAnnotationPresent(ReadOnlyDataSource.class) && !method.isAnnotationPresent(Maintain.class)) {
            System.out.println("#############" + method.getName());
            if (redisService.getIsCheckingOfAutomatedLoanService()) {
//                return MobileJsonResultVo.buildErrorVo(JsonResultVo.ERROR, Message.resources.getMessage("1008", null, Locale.getDefault()));
            }
        }
        return methodInvocation.proceed();
    }
}
