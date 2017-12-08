package wangzhongqiu.spring.springmvc.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Check api version
 * Used to repleace the CheckVersionFilter.java
 * 
 * 
 */
public class CheckVersionInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(CheckVersionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
//        String version = request.getParameter("version");
//        String path = request.getServletPath();
//        if ("/login/noLogin.action".equals(path)
//                || "/account/uploaduseravatar.action".equals(path)
//                || "/login/logout.action".equals(path)
//                || "/easypay/asynNotify.action".equals(path)
//                ||"/quickpay/asyn_notify".equals(path)
//                || "/easypay/returnUrl.action".equals(path)
//                || "/fund/notify".equals(path)
//                || "/usergrowth/createUser".equals(path)) {
//
//        } else if (StringUtil.isEmpty(version)) {
//            response.setContentType("application/json;charset=UTF-8");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().println(MobileJsonResultVo.buildErrorVo(1003).toJson());
//            response.getWriter().close();
//            return false;
//        } else {
//            // 判断版本是否可用
//            boolean availableVersion = false;
//            List<Object> availableVersions = Config.getAsList("AVAILABLE_VERSION");
//            for (Object versionObj : availableVersions) {
//                if (version.equals(versionObj.toString())) {
//                    availableVersion = true;
//                }
//            }
//            if (!availableVersion) {
//                response.setContentType("application/json;charset=UTF-8");
//                response.setCharacterEncoding("UTF-8");
//                response.getWriter().println(MobileJsonResultVo.buildErrorVo(1003).toJson());
//                response.getWriter().close();
//                return false;
//            }
//        }
        return true;
    }

}
