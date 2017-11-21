package wangzhongqiu.spring.dubbo.filter;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhongqiu
 * @date 2017/11/20.
 */
@Service
public class IpWhiteList {
    public static ArrayList<String> ipWhiteList = new ArrayList<>();
    public static Boolean enabled = true;

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
