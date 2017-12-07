/*
 * 
 * 
 */
package wangzhongqiu.spring.springmvc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import wangzhongqiu.spring.springmvc.utils.LandingLog4Statistics;
import wangzhongqiu.spring.springmvc.vo.MobileJsonResultVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 */
public class AfterLoginFailHandler extends SimpleUrlAuthenticationFailureHandler {
    private static Logger logger = LoggerFactory.getLogger(AfterLoginFailHandler.class);

    /**
     * 登录失败后
     * 
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if (exception.getMessage().equals("User is disabled")) {
            out.println(MobileJsonResultVo.buildErrorVo(3223).toJson());
        } else if (exception.getMessage().equals("specialUser could not login")) {
            out.println(MobileJsonResultVo.buildErrorVo(3224).toJson());
        } else {
            out.println(MobileJsonResultVo.buildErrorVo(3202).toJson());
        }
        out.flush();
        // 记录登录后的日志
        LandingLog4Statistics.getInstance().printLog(LandingLog4Statistics.LOGIN, request.getSession().getId(), 0, LandingLog4Statistics.FAIL);
        return;

    }
}
