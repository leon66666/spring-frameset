package wangzhongqiu.spring.springmvc.interceptor;

import com.hoomsun.common.Constants;
import com.hoomsun.exception.RedisConnectException;
import com.hoomsun.service.cache.RedisService;
import com.hoomsun.service.cache.SyncRedisService;
import com.hoomsun.util.RequestUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserActivityMonitorInterceptor extends HandlerInterceptorAdapter {

    public static final String DELIMITER = ":";

    /**
     * MAX_ACTIVE_INTERVAL时间内允许的刷新次数
     */
    public static final int MAX_ALLOW_REFRESH_TIMES = 20000;
    /**
     * 每MAX_ACTIVE_INTERVAL时间为一个限制周期,过了MAX_ACTIVE_INTERVAL时间则清零
     */
    public static final int MAX_ACTIVE_INTERVAL = 180;

    private static final Log UAM_LOG = LogFactory.getLog(UserActivityMonitorInterceptor.class);

    @Autowired
    private RedisService redisService;
    @Autowired
    private SyncRedisService syncRedisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String userId_s = request.getParameter(Constants.USER_ID);
            // 已经登陆的用户 按每个页面的刷新次数精确判断
            if (userId_s != null) {
                Integer userId = Integer.parseInt(userId_s);
                String path = request.getServletPath();
                // 打算分为三组进行控制: lend, transfer, financeplan
                if (path != null) {
                    if (path.indexOf("/lend/") >= 0) {
                        path = "/lend/";
                    } else if (path.indexOf("/transfer/") >= 0) {
                        path = "/transfer/";
                    } else if (path.indexOf("/financeplan/") >= 0) {
                        path = "/financeplan/";
                    }
                }
                String key = Constants.USER_ACTIVITY_MONITOR + DELIMITER + userId + DELIMITER + path;
                String ip = RequestUtil.getRemoteIPAddress(request);
                String ifExist = syncRedisService.getValue(key);
                if (ifExist == null) {
                    redisService.expire(key, MAX_ACTIVE_INTERVAL);
                }else {
                    Long counter = syncRedisService.incrBy(key, 1);
                    if (counter != null && counter > MAX_ALLOW_REFRESH_TIMES) {
                        // 这段代码是为了防止过了getValue后在incrBy之前过期, 导致出现过期时间为永远不过期的低概率事件而写的
                        Long ttl = redisService.ttl(key);
                        if (ttl != null && ttl == -1L) {
                            if (syncRedisService.getValue(key) != null) {
                                redisService.expire(key, MAX_ACTIVE_INTERVAL);
                            }
                        }
                        response.setStatus(503);
                        response.sendRedirect("/err/refresh-too-fast-trade.jsp");
                    }
                    UAM_LOG.info("UserActivityMonitor KEY:" + key + ", IP:" + ip + ", COUNTER:" + counter);
                }
            }
        } catch (RedisConnectException e) {
            UAM_LOG.error("RedisConnectException", e);
        } catch (Exception e) {
            UAM_LOG.error("System error", e);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // do nothing
    }
}
