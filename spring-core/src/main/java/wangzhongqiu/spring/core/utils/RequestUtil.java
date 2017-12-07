package wangzhongqiu.spring.core.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import zhongqiu.javautils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

/**
 * 在讨论获取客户端IP 地址前，我们首先下弄明白的是以下三个的具体含义：REMOTE_ADDR，HTTP_CLIENT_IP，HTTP_X_FORWARDED_FOR REMOTE_ADDR 是你的客户端跟你的服务器“握手”时候的IP。如果使用了“匿名代理”，REMOTE_ADDR将显示代理服务器的IP。 HTTP_CLIENT_IP 是代理服务器发送的HTTP头。如果是“超级匿名代理”，则返回none值。同样，REMOTE_ADDR也会被替换为这个代理服务器的IP。
 *
 * $_SERVER['REMOTE_ADDR']; //访问端（有可能是用户，有可能是代理的）IP
 * $_SERVER['HTTP_CLIENT_IP'];   //代理端的（有可能存在，可伪造）
 * $_SERVER['HTTP_X_FORWARDED_FOR']; //用户是在哪个IP使用的代理（有可能存在，也可以伪造）
 * 一、没有使用代理服务器的情况：
 *
 * REMOTE_ADDR = 您的 IP
 * HTTP_CLIENT_IP = 没数值或不显示
 * HTTP_X_FORWARDED_FOR = 没数值或不显示
 *
 *
 * 二、使用透明代理服务器的情况：Transparent Proxies
 *
 * REMOTE_ADDR = 最后一个代理服务器 IP
 * HTTP_CLIENT_IP = 代理服务器 IP
 * HTTP_X_FORWARDED_FOR = 您的真实 IP ，经过多个代理服务器时，这个值类似如下：203.98.182.163, 203.98.182.163, 203.129.72.215。
 * 这类代理服务器还是将您的信息转发给您的访问对象，无法达到隐藏真实身份的目的。
 *
 * 三、使用普通匿名代理服务器的情况：Anonymous Proxies
 *
 * REMOTE_ADDR = 最后一个代理服务器 IP
 * HTTP_CLIENT_IP = 代理服务器 IP
 * HTTP_X_FORWARDED_FOR = 代理服务器 IP ，经过多个代理服务器时，这个值类似如下：203.98.182.163, 203.98.182.163, 203.129.72.215。
 * 隐藏了您的真实IP，但是向访问对象透露了您是使用代理服务器访问他们的。
 *
 * 四、使用欺骗性代理服务器的情况：Distorting Proxies
 *
 * REMOTE_ADDR = 代理服务器 IP
 * HTTP_CLIENT_IP = 代理服务器 IP
 * HTTP_X_FORWARDED_FOR = 随机的 IP ，经过多个代理服务器时，这个值类似如下：203.98.182.163, 203.98.182.163, 203.129.72.215。
 *
 * 告诉了访问对象您使用了代理服务器，但编造了一个虚假的随机IP代替您的真实IP欺骗它。
 *
 * 五、使用高匿名代理服务器的情况：High Anonymity Proxies (Elite proxies)
 *
 * REMOTE_ADDR = 代理服务器 IP
 * HTTP_CLIENT_IP = 没数值或不显示
 * HTTP_X_FORWARDED_FOR = 没数值或不显示 ，经过多个代理服务器时，这个值类似如下：203.98.182.163, 203.98.182.163, 203.129.72.215。
 * 完全用代理服务器的信息替代了您的所有信息，就象您就是完全使用那台代理服务器直接访问对象。

 * 
 */
public final class RequestUtil {

    private static final Log _log = LogFactory.getLog(RequestUtil.class);

    public static final String UNKNOWN = "unknown";

    /**
     *IP白名单
     */
    public static String IP_WHITELIST = null;

    static {
        try {
            // 配置文件路径,加载配置文件
           InputStream fis = RequestUtil.class.getClassLoader().getResourceAsStream("ip_whitelist.properties");
           Properties prop = new Properties();
           if (fis != null) {
               prop.load(fis);
               // 加载配置
               if (prop.getProperty("ip_whitelist") != null) {
                   IP_WHITELIST = (String) prop.getProperty("ip_whitelist");
               }
           }
       } catch (Exception e) {
//           _log.error(e.getMessage());
       }
    }

    public static String getRemoteIPAddress(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || RequestUtil.UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || RequestUtil.UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || RequestUtil.UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                        ipAddress = inet.getHostAddress();
                    } catch (Exception e) {
                        ipAddress = "127.0.0.1";
                    }
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            // do nothing
            return "";
        }
        return ipAddress;
    }

    /**
     * 检查ip是否在白名单
     * @param ip
     * @return
     */
    public static boolean isIpInWhiteList(String ip) {
        if (StringUtil.isEmpty(ip)) {
            return true;
        }

        if (IP_WHITELIST != null) {
            if (IP_WHITELIST.indexOf("," + ip + ",") >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取用户请求时的user-agent
     *
     * @param request
     * @return
     */
    public static String getUseragent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    /**
     * 判断是否在某个ip段或两个ip在一个子网
     *
     * System.out.println(isInSameSegment("192.168.1.127", "192.168.1.64/26"));
     * System.out.println(isInSameSegment("192.168.1.2", "192.168.0.0/23"));
     * System.out.println(isInSameSegment("192.168.0.1", "192.168.0.0/24"));
     * System.out.println(isInSameSegment("192.168.0.100", "192.168.0.0/32")); // false
     * System.out.println(isInSameSegment("localhost", "127.0.0.0"));
     *
     * @param ip
     * @param cidr
     * @return
     */
    public static boolean isInSameSegment(String ip, String cidr) {
        if (StringUtil.isEmpty(ip) || StringUtil.isEmpty(cidr)) {
            return false;
        }
        if ("localhost".equals(ip)) {
            ip = "127.0.0.1";
        }
        if ("localhost".equals(cidr)) {
            cidr = "127.0.0.1";
        }
        if (ip.equals(cidr)) {
            return true;
        }
        String[] ips = ip.split("\\.");
        int ipAddr = (Integer.parseInt(ips[0]) << 24)
                | (Integer.parseInt(ips[1]) << 16)
                | (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);
        int type = Integer.parseInt(cidr.indexOf("/") > 0 ? cidr.replaceAll(".*/", "") : "255");
        int mask = 0xFFFFFFFF << (32 - type);
        String cidrIp = cidr.replaceAll("/.*", "");
        String[] cidrIps = cidrIp.split("\\.");
        int cidrIpAddr = (Integer.parseInt(cidrIps[0]) << 24)
                | (Integer.parseInt(cidrIps[1]) << 16)
                | (Integer.parseInt(cidrIps[2]) << 8)
                | Integer.parseInt(cidrIps[3]);

        return (ipAddr & mask) == (cidrIpAddr & mask);
    }

}
