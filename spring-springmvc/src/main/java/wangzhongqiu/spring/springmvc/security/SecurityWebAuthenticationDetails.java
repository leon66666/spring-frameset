package wangzhongqiu.spring.springmvc.security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import wangzhongqiu.spring.core.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 */
public class SecurityWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 8582195614700870080L;

    public static final String AUTHENTICATED_USERNAME = "AUTHENTICATED_USERNAME";

    private String remoteIp;
    private String authenticatedUsername;

    public SecurityWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        this.remoteIp = RequestUtil.getRemoteIPAddress(request);
        this.authenticatedUsername = (String) request.getSession().getAttribute(AUTHENTICATED_USERNAME);
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public String getAuthenticatedUsername() {
        return authenticatedUsername;
    }
}
