package wangzhongqiu.spring.springmvc.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import wangzhongqiu.spring.core.model.SecurityUser;
import wangzhongqiu.spring.core.model.User;
import wangzhongqiu.spring.core.utils.RequestUtil;
import wangzhongqiu.spring.springmvc.utils.LandingLog4Statistics;
import wangzhongqiu.spring.springmvc.vo.MobileJsonResultVo;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AfterLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(MobileJsonResultVo.buildSuccessVo().toJson());
        out.flush();
        String remoteIp = RequestUtil.getRemoteIPAddress(request);
        User user = null;
        if (authentication != null) {
            Object _u = authentication.getPrincipal();
            if (_u != null && _u instanceof SecurityUser) {
                SecurityUser userDetails = (SecurityUser) _u;
                user = userDetails.getUser();
            }
        }
        
        String cookieName = "UID";
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
//        cookie.setDomain(SessionUtil.SEESION_ID_DOMAIN); // 已经放到配置文件中 api.we.com和m.we.com和*.we.com都可使用, 但是IP访问不可以使用
        response.addCookie(cookie);

        /**
         * 清除盈米的cookie
         */
        Cookie ymCookie = new Cookie("JSESSIONID",request.getSession().getId());
        ymCookie.setDomain(".yingmi.cn");
        ymCookie.setMaxAge(0);
        ymCookie.setPath("/");
        response.addCookie(ymCookie);

        // 记录登出后的日志
        LandingLog4Statistics.getInstance().printLog(LandingLog4Statistics.LOGOUT, "", 0, LandingLog4Statistics.SUCCESS);
        return;
    }

}
