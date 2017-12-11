package wangzhongqiu.spring.springmvc.spel;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import wangzhongqiu.spring.core.exception.CommonRuntimeExceptionFactory;
import wangzhongqiu.spring.core.utils.CallbackResult;
import wangzhongqiu.spring.springmvc.annotation.ServiceParamCheck;

import java.lang.reflect.Method;
import java.util.List;

public class ServiceParamCheckCacheAspect extends RRDExpressionCompiler{
    
    private static Log LOGGER = LogFactory.getLog(ServiceParamCheckCacheAspect.class);

    public Object doCheck(ProceedingJoinPoint pjp) throws Throwable {
        
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        
        try {
            ServiceParamCheck spc = method.getAnnotation(ServiceParamCheck.class);
            if(StringUtils.isBlank(spc.spel())){
                return pjp.proceed();
            }
            
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            List<SpELCompileResult> compileResult =  compile(spc.spel(), u.getParameterNames(method), pjp.getArgs());
            
            for(SpELCompileResult r : compileResult){
                Boolean b = Boolean.valueOf(r.getResult().toString());
                if(!b){
                    //对详细的失败原因记录错误日志
                    LOGGER.error("方法["+method.getName()+"]的参数验证失败:" + r.getExpression() );
                    //返回可读的异常消息给前端
                    return CallbackResult.failure(CallbackResult.FAILURE,
                            CommonRuntimeExceptionFactory.getException(CallbackResult.FAILURE, "参数不合法."));
                }
            }
        } catch (Exception e) {
            LOGGER.error("Failed to check the service parameter for method" + method.getName() + ":", e);
        }
        
        return pjp.proceed();
    }
}
