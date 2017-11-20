package wangzhongqiu.spring.dubbo.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhongqiu
 * @date 2017/11/20.
 */
public class IpWhiteList {
    public static ArrayList<String> ipWhiteList;
    public static Boolean enabled;

    static {
        ipWhiteList.add("192.168.1.189");
        enabled = true;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<String> getAllowedIps() {
        return ipWhiteList;
    }

    public boolean isAllowed(String ip) {
        return ipWhiteList.contains(ip);
    }
}
