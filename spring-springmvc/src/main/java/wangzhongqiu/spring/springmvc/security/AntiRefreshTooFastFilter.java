/*
 * 
 * 
 */
package wangzhongqiu.spring.springmvc.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import wangzhongqiu.spring.core.model.User;
import wangzhongqiu.spring.core.utils.RequestUtil;
import wangzhongqiu.spring.springmvc.utils.ContextTools;
import wangzhongqiu.spring.springmvc.vo.JsonResultVo;
import wangzhongqiu.spring.springmvc.vo.MobileJsonResultVo;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 防多次刷新的filter
 *
 * 
 */
public class AntiRefreshTooFastFilter implements Filter {
    private static Log log = LogFactory.getLog(AntiRefreshTooFastFilter.class);

    // 高频率访问url
    private static String[] HIGH_FREQUENCY_ACCESS_URL = { "usersecurityinfo", "userpointlog",
            "userinvestinfo", "userloanlist", "userfinanceplanlist", "userbackaccount",
            "loanlenderpage", "loanindex", "financeindex", "prersvplandetail",
            "financeplanlist", "financeplandetail", "prebuyplandetail" };

    // 低频率访问url
    private static String[] LOW_FREQUENCY_ACCESS_URL = { "feedback", "register",
            "uploaduseravatar", "updateuseravatar" };

    /**
     * 在REFRESH_INTERVAL时间内只能刷新MAX_ALLOW_REFRESH_TIMES次
     */
    public static Map<String, Result> periodTimes = new ConcurrentHashMap<String, Result>();

    /**
     * 任何两次点击之间间隔都不能小于REFRESH_INTERVAL_SINGLE_TIME_LOGINED秒
     */
    public static Map<String, Long> singleTime = new ConcurrentHashMap<String, Long>();

    public static long CURRENT_TIMESTAMP = System.currentTimeMillis();

    /**
     * 计时器在内存的存活时间
     */
    public static int MAX_ACTIVE_INTERVAL = 10 * 1000;

    /**
     * 如下参数组合起来控制未登录用户在REFRESH_INTERVAL时间内只能刷新MAX_ALLOW_REFRESH_TIMES次,
     * 如果超出则在MAX_ACTIVE_INTERVAL时间内无法再次登录
     */
    public static int REFRESH_INTERVAL = 120 * 1000;
    public static int MAX_ALLOW_REFRESH_TIMES = 120;

    /**
     * 如下参数组合起来控制已登录用户在REFRESH_INTERVAL_LOGINED时间内只能刷新MAX_ALLOW_REFRESH_TIMES_LOGINED次
     * , 如果超出则在MAX_ACTIVE_INTERVAL时间内无法再次登录
     */
    public static int REFRESH_INTERVAL_LOGINED = 30 * 1000;
    public static int MAX_ALLOW_REFRESH_TIMES_LOGINED = 60;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getServletPath();

        boolean ishighFrequencyAccessUrl = false;
        boolean islowFrequencyAccessUrl = false;
        for (String url : HIGH_FREQUENCY_ACCESS_URL) {
            if (path.endsWith(url)) {
                ishighFrequencyAccessUrl = true;
                break;
            }
        }
        for (String url : LOW_FREQUENCY_ACCESS_URL) {
            if (path.endsWith(url)) {
                islowFrequencyAccessUrl = true;
                break;
            }
        }

        Integer userId = -1;
        try {
            User user = ContextTools.getSecurityUser(request);
            userId = null == user ? -1 : ContextTools.getSecurityUser(request).getUserId();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String remoteIP = RequestUtil.getRemoteIPAddress(request);
        if (RequestUtil.isIpInWhiteList(remoteIP)) { // 检查ip是否在白名单
            filterChain.doFilter(request, response);
            return;
        }
        String key = remoteIP + ":" + userId + ":" + path;

        try {
            long currTime = System.currentTimeMillis();
            if (currTime - CURRENT_TIMESTAMP >= MAX_ACTIVE_INTERVAL) {
                CURRENT_TIMESTAMP = currTime;
                periodTimes.clear();
                singleTime.clear();
            }

            Result r = periodTimes.get(key);
            Long lastTime = singleTime.get(key);
            // log.info("anti-refresh-key:" + key + ", r:" + r);
            // log.info("anti-refresh-singleTime-key:" + key + ", lastTime:" +
            // lastTime);

            if (null != r && null != r.lastStartTime && null != r.totalNumber) {
                r.totalNumber += 1;
                if (currTime - r.lastStartTime > REFRESH_INTERVAL) {
                    r.lastStartTime = currTime;
                    r.totalNumber = 1;
                }
                periodTimes.put(key, r);
                singleTime.put(key, currTime);

                int max_allow_refresh_times;
                int max_allow_refresh_times_logined;
                if (ishighFrequencyAccessUrl) {// 高频率访问url
                    max_allow_refresh_times = MAX_ALLOW_REFRESH_TIMES * 4;
                    max_allow_refresh_times_logined = MAX_ALLOW_REFRESH_TIMES_LOGINED * 4;
                } else if (islowFrequencyAccessUrl) {// 低频率访问url
                    max_allow_refresh_times = MAX_ALLOW_REFRESH_TIMES / 5;
                    max_allow_refresh_times_logined = MAX_ALLOW_REFRESH_TIMES_LOGINED / 5;
                } else {
                    max_allow_refresh_times = MAX_ALLOW_REFRESH_TIMES;
                    max_allow_refresh_times_logined = MAX_ALLOW_REFRESH_TIMES_LOGINED;
                }

                if ((userId == -1 && currTime - r.lastStartTime <= REFRESH_INTERVAL && r.totalNumber >= max_allow_refresh_times) // 用户未登录情况
                        || (userId != -1 && currTime - r.lastStartTime <= REFRESH_INTERVAL_LOGINED && r.totalNumber >= max_allow_refresh_times_logined)) { // 用户已登录情况
//                    String accept = request.getHeader("Accept");
                    // if (accept != null && accept.indexOf("application/json")
                    // != -1) {
                    try {
                        MobileJsonResultVo json = MobileJsonResultVo.buildSuccessVo();
                        json.setStatus(JsonResultVo.ERROR);
                        json.setMessage("您的操作过于频繁，请稍后再试");
                        response.setContentType("text/html;charset=UTF-8");
                        response.getWriter().write(json.toJson());
                    } catch (Exception e) {
                        e.printStackTrace();
                        // log.error("Ajax请求响应刷新频繁出错：" + e.getMessage());
                        // do nothing
                    }
                    // } else {
                    // MobileJsonResultVo json =
                    // MobileJsonResultVo.buildSuccessVo();
                    // json.setStatus(JsonResultVo.ERROR);
                    // json.setMessage("您刷新太频繁了，请登录后等60秒后继续访问。若是刷新投标信息，请设置刷新频率为半分钟以上。若有访问困难，请提供有关登录信息，联系红小宝客服，谢谢合作！");
                    //
                    // response.setStatus(503);
                    // response.sendRedirect("/exceptions/refresh-too-fast.jsp");
                    // }
                    return;
                }
            } else { // 初始化
                r = new Result();
                r.lastStartTime = currTime;
                r.totalNumber = 1;
                periodTimes.put(key, r);
                singleTime.put(key, currTime);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

    class Result {
        Long lastStartTime;
        Integer totalNumber;

        @Override
        public String toString() {
            return "Result [lastAccessTime=" + lastStartTime + ", totalNumber=" + totalNumber + "]";
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public void destroy() {
        if (null != periodTimes) {
            periodTimes.clear();
        }
    }
}
