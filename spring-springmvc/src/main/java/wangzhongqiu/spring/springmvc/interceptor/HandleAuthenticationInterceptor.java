package wangzhongqiu.spring.springmvc.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import wangzhongqiu.spring.core.constants.Constants;
import wangzhongqiu.spring.springmvc.controller.BaseController;
import zhongqiu.javautils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 从前端获得request中的userId并在这里赋值给baseController的userId的ThreadLocal对象
 * 业务处理完毕时需要清除userId的ThreadLocal对象因为tomcat的nio线程池需要重用这个线程
 */
public class HandleAuthenticationInterceptor extends HandlerInterceptorAdapter {

    private static final Log log = LogFactory.getLog(HandleAuthenticationInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 这个主要是判断当前连接的是不是业务类的控制器，
         * 如果是springmvc静态资源的handler，
         * 在转成HandlerMethod时就会报错.
         *
         */
        if (HandlerMethod.class.equals(handler.getClass())) {
            HandlerMethod method = (HandlerMethod) handler;
            Object controller = method.getBean();
            if (controller instanceof BaseController) {
                String userId = request.getParameter(Constants.USER_ID);
                String ipAddress = request.getParameter(Constants.IP_ADDRESS);
                String userAgent = request.getParameter(Constants.USER_AGRENT);
                String platform = request.getParameter(Constants.PLATFORM);
                if (StringUtil.isNotEmpty(ipAddress)) {
                    ((BaseController) method.getBean()).setIpAddress(ipAddress);
                }
                if (StringUtil.isNotEmpty(userAgent)) {
                    ((BaseController) method.getBean()).setUserAgent(userAgent);
                }
                if (StringUtil.isNotEmpty(platform)) {
                    ((BaseController) method.getBean()).setPlatform(platform);
                }
                if (StringUtil.isEmpty(userId) || "0".equals(userId)) {
                    log.error(Thread.currentThread().getName() + " userId not found");
                } else {
                    ((BaseController) method.getBean()).setUserId(Integer.parseInt(userId));
                    log.info(Thread.currentThread().getName() + " set userId:" + userId);
                }
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (HandlerMethod.class.equals(handler.getClass())) {
            HandlerMethod method = (HandlerMethod) handler;
            Object controller = method.getBean();
            if (controller instanceof BaseController) {
                ((BaseController) controller).getUserId();
                ((BaseController) method.getBean()).setUserId(null);
                ((BaseController) method.getBean()).setIpAddress(null);
                ((BaseController) method.getBean()).setUserAgent(null);
                ((BaseController) method.getBean()).setPlatform(null);
                log.info(Thread.currentThread().getName() + " del userId");
            }
        }
        super.afterCompletion(request, response, handler, ex);
    }
}
