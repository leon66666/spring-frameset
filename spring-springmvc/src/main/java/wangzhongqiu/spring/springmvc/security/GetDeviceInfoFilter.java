package wangzhongqiu.spring.springmvc.security;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wangzhongqiu.spring.core.model.DeviceInfo;
import wangzhongqiu.spring.core.model.User;
import wangzhongqiu.spring.springmvc.utils.ContextTools;
import zhongqiu.javautils.JsonUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 获取请求信息中的设备信息
 * 
 *
 */
public class GetDeviceInfoFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(GetDeviceInfoFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        //获取请求参数中的设备信息
        String deviceInfo = httpRequest.getParameter("deviceInfo");
        if(StringUtils.isEmpty(deviceInfo)){
            deviceInfo = httpRequest.getAttribute("deviceInfo")==null?null:httpRequest.getAttribute("deviceInfo").toString();
        }

        //打印请求参数
//        Map<String, String[]> params = httpRequest.getParameterMap();
//        String queryString = "";
//        for (String key : params.keySet()) {
//            String[] values = params.get(key);
//            for (int i = 0; i < values.length; i++) {
//                String value = values[i];
//                queryString += key + "=" + value + "&";
//            }
//        }
//        logger.info("------>request:"+httpRequest.getRequestURL()+"  参数:"+queryString);


        //如果设备信息不为空
        if(StringUtils.isNotBlank(deviceInfo)){

            //如果有用户的时候 打印log
            User user = ContextTools.getSecurityUser(httpRequest);
            if(user!=null){
                try{

                    DeviceInfo di = JsonUtil.jsonToBean(deviceInfo, DeviceInfo.class);
                    if (di != null) {

                        logger.info("用户:" + user.getUserId() + "访问路径:[" + httpRequest.getServletPath() + "] "
                                + di.toString());

                        /**
                         * log for statistics
                         * 
                         * 
                         * 
                         * remove this log
                         * 
                         * 
                         *
                         */
//                        logger.info(new Log4Statistics(new Date(), "mobile", CategoryType.CLICK, "getDeviceInfo",
//                                StringUtil.isNotEmpty(user.getUsername()) == true ? user.getUsername() : user.getMobile(),
//                                request.getParameter("channelCode"), httpRequest.getHeader("User-Agent"), RequestUtil
//                                        .getRemoteIPAddress(httpRequest), di.getModel().toString(),request.getParameter("clientVersion")).toString());
                    }

                }catch(Exception e){

                    //打印请求参数
                    Map<String, String[]> params = httpRequest.getParameterMap();
                    String queryString = "";
                    for (String key : params.keySet()) {
                        String[] values = params.get(key);
                        for (int i = 0; i < values.length; i++) {
                            String value = values[i];
                            queryString += key + "=" + value + "&";
                        }
                    }

                    logger.info("设备信息转换失败(设备信息可能不合法)------>request内容:"+httpRequest.getRequestURL()+"  参数:"+queryString);
                    e.printStackTrace();
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
