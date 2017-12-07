package wangzhongqiu.spring.springmvc.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *  拦截防止sql注入
 *
 *  <code>{@link XssHttpServletRequestWrapper}</code>
 */
public class XssFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
            ServletException {

        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        filterChain.doFilter(xssRequest, response);

    }

    @Override
    public void destroy() {
        return;
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        return;
    }

}
