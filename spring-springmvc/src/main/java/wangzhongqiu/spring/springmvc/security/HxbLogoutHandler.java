package wangzhongqiu.spring.springmvc.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import wangzhongqiu.spring.springmvc.utils.LandingLog4Statistics;
import zhongqiu.javautils.UtilTools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 */
public class HxbLogoutHandler extends SecurityContextLogoutHandler {

    private static Log log = LogFactory.getLog(HxbLogoutHandler.class);

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        Cookie cookie = new Cookie("jforumUserInfo", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
//        cookie.setDomain(SessionUtil.SEESION_ID_DOMAIN); // 已经放到配置文件中 api.we.com和m.we.com和*.we.com都可使用, 但是IP访问不可以使用
        response.addCookie(cookie);

        String cookieName = "rrd_key";
        Cookie cookie2 = new Cookie(cookieName, null);
        cookie2.setMaxAge(0);
        cookie2.setPath("/");
//        cookie.setDomain(SessionUtil.SEESION_ID_DOMAIN); // 已经放到配置文件中 api.we.com和m.we.com和*.we.com都可使用, 但是IP访问不可以使用
        response.addCookie(cookie2);

        try {
            String returnUrl = request.getParameter("returnUrl");
            if (!UtilTools.isNullOrEmpty(returnUrl)) {
                response.sendRedirect(returnUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 记录销毁前的sessionId
        LandingLog4Statistics.getInstance().addBeforeSessionId(request.getSession().getId());
        super.logout(request, response, authentication);
    }
}
