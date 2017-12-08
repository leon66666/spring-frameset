/*
 * 
 * 
 */
package wangzhongqiu.spring.springmvc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zhongqiu.javautils.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 */
public class LoginValidateFilter extends HttpServlet implements Filter {
    private static Logger logger = LoggerFactory.getLogger(LoginValidateFilter.class);
    /**
     * 判断用户输入的验证码是否正确
     */
    private static final long serialVersionUID = 2939171822694365370L;

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String version = request.getParameter("version");
        if (StringUtil.isEmpty(version)) {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
//            out.println(MobileJsonResultVo.buildErrorVo(1003).toJson());
            out.flush();
            return;
        }

        if (!"POST".equals(request.getMethod())) {
            String method = request.getMethod();
            String j_username = request.getParameter("username");
            String j_password = request.getParameter("password");
            if ((!"".equals(j_username) && null != j_username) && (!"".equals(j_password) && null != j_password)) {
                logger.info("请求接口方式:" + method);
                logger.info("拦截明文请求登陆借口:" + j_username);
                response.setContentType("application/json;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
//                out.println(MobileJsonResultVo.buildErrorVo(3201).toJson());
                out.flush();
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
