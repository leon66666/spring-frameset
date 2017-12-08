package wangzhongqiu.spring.springmvc.controller;

/**
 * 提供userId对象用来接收前端传递的登录用户
 */
public class BaseController {

    private ThreadLocal<Integer> userId = new ThreadLocal();

    /**
     * PC购买或是移动端购买(对应数据库tradeMethod字段)
     * 默认为PC
     */
    private ThreadLocal<String> platform = new ThreadLocal();
    /**
     * 用户客户端IP地址(用于防刷)
     */
    private ThreadLocal<String> ipAddress = new ThreadLocal();
    /**
     * 用户浏览器信息(用于统计)
     */
    private ThreadLocal<String> userAgent = new ThreadLocal();

    public void setUserId(Integer userId) {
        this.userId.set(userId);
    }

    public void setPlatform(String platform) {
        this.platform.set(platform);
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress.set(ipAddress);
    }

    public void setUserAgent(String userAgent) {
        this.userAgent.set(userAgent);
    }

    public Integer getUserId() {
        if (userId != null) {
            return userId.get();
        }
        return null;
    }

    public boolean isMobile() {
        return false;
    }

    public String getIpAddress() {
        if (ipAddress != null) {
            return ipAddress.get();
        }
        return null;
    }

    public String getUserAgent() {
        if (userAgent != null) {
            return userAgent.get();
        }
        return null;
    }

    public String getPlatform() {
        if (platform != null) {
            return platform.get();
        }
        return null;
    }
}
