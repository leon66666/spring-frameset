package wangzhongqiu.spring.springmvc.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import wangzhongqiu.spring.springmvc.utils.EscapeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 防跨站攻击和SQL注入
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private static Log _log = LogFactory.getLog(XssHttpServletRequestWrapper.class);

    HttpServletRequest orgRequest = null;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        orgRequest = request;
    }

    @Override
    public String[] getParameterValues(String name) {
        if (XssHttpServletRequestWrapper.httpParamSkip(name)) {
            return super.getParameterValues(name);
        }
        String[] value = super.getParameterValues(EscapeUtil.escapse(name));
//        System.out.println("xss getParameterValues:" + name + ":" + value);
        String temp = null;
        if (value != null && value.length > 0) {
            for (int i = 0; i < value.length; i++) {
                if (value != null) {
                    temp = value[i];
                    value[i] = EscapeUtil.escapse(value[i]);
//                    if (!temp.equals(value[i])) {
//                        _log.warn("xss getParameter:" + name + ":" + temp + "-->" + value);
//                    }
                }
            }
        }
        return value;
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        if (XssHttpServletRequestWrapper.httpParamSkip(name)) {
            return super.getParameter(name);
        }
        String value = super.getParameter(EscapeUtil.escapse(name));
        if (value != null) {
            String temp = value;
            value = EscapeUtil.escapse(value);
//            if (!temp.equals(value)) {
//                _log.warn("xss getParameter:" + name + ":" + temp + "-->" + value);
//            }
        }
        return value;
    }

    /**
     * Struts2 是通过request.getParameterMap()来获取属性的，要重写这个方法
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
//    @Override
//    public Map<String, String[]> getParameterMap() {
//        Map<String, String[]> map = super.getParameterMap();
//        String[] _value = null;
//        if (map != null && map.size() > 0) {
//            for (Map.Entry<String, String[]> entry: map.entrySet()) {
//                if (XssHttpServletRequestWrapper.httpParamSkip(entry.getKey())) {
//                    continue;
//                }
//                _value = entry.getValue();
//                if (_value != null && _value.length > 0) {
//                    for (String _each : _value) {
//                        if (_each != null) {
//                            String temp = _each;
//                            _each = EscapeUtil.escapse(_each);
////                            if (!temp.equals(_each)) {
////                                _log.warn("xss getParameterMap:" + entry.getKey() + ":" + temp + "-->" + _each);
////                            }
//                        }
//                    }
//                }
//            }
//        }
//        return map;
//    }

//    /**
//     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
//     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
//     * getHeaderNames 也可能需要覆盖
//     */
//    @Override
//    public String getHeader(String name) {
//        if (this.httpParamSkip(name)) {
//            return super.getHeader(name);
//        }
//        String value = super.getHeader(EscapeUtil.escapse(name));
//        System.out.println("xss getHeader:" + name + ":" + value);
//        if (value != null) {
//            value = EscapeUtil.escapse(value);
//        }
//        return value;
//    }

    /**
     * 获取最原始的request
     *
     * @return
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request的静态方法
     *
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) req).getOrgRequest();
        }

        return req;
    }

    /**
     * 跳过一般的http参数
     * @param name
     * @return
     */
    public static boolean httpParamSkip(String name) {
        if (name == null || "".equals(name) || "-".equals(name) || "_".equals(name) || "x-forwarded-for".equals(name) || "Proxy-Client-IP".equals(name) || "WL-Proxy-Client-IP".equals(name)
                || "X-Requested-With".equals(name) || "If-Match".equals(name) || "If-None-Match".equals(name) || "user-agent".equals(name)
                || "Referer".equals(name)) {
            return true;
        }
        return false;
    }
}
