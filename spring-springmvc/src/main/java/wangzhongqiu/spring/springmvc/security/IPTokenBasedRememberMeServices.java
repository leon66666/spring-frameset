package wangzhongqiu.spring.springmvc.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.util.DigestUtils;
import wangzhongqiu.spring.core.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * 为了安全RememberMe增强为支持校验IP的cookie方式
 *
 * 
 */
public class IPTokenBasedRememberMeServices extends TokenBasedRememberMeServices {

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public HttpServletRequest getContext() {
        return requestHolder.get();
    }

    public void setContext(HttpServletRequest context) {
        requestHolder.set(context);
    }

    protected String getUserIPAddress(HttpServletRequest request) {
        return RequestUtil.getRemoteIPAddress(request);
    }

    @Override
    public void onLoginSuccess(HttpServletRequest request, HttpServletResponse response,
                               Authentication successfulAuthentication) {
        try {
            setContext(request);
            super.onLoginSuccess(request, response, successfulAuthentication);
        } finally {
            setContext(null);
        }
    }

    @Override
    protected String makeTokenSignature(long tokenExpiryTime, String username, String password) {
        return DigestUtils
                .md5DigestAsHex((username + ":" + tokenExpiryTime + ":" + password + ":" + getKey() + ":" + getUserIPAddress(getContext()))
                        .getBytes());
    }

    @Override
    protected void setCookie(String[] tokens, int maxAge, HttpServletRequest request, HttpServletResponse response) {
        // append the IP adddress to the cookie
        String[] tokensWithIPAddress = Arrays.copyOf(tokens, tokens.length + 1);
        tokensWithIPAddress[tokensWithIPAddress.length - 1] = getUserIPAddress(request);
        super.setCookie(tokensWithIPAddress, maxAge, request, response);
    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            setContext(request);
            // take off the last token
            String ipAddressToken = cookieTokens[cookieTokens.length - 1];
            if (!getUserIPAddress(request).equals(ipAddressToken)) {
                throw new InvalidCookieException("Cookie IP Address did not  contain a matching IP (contained '"
                        + ipAddressToken + "' )");
            }

            return super
                    .processAutoLoginCookie(Arrays.copyOf(cookieTokens, cookieTokens.length - 1), request, response);
        } finally {
            setContext(null);
        }
    }
}
