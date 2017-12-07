package wangzhongqiu.spring.springmvc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于统计登录和退出的日志
 * 
 */
public class LandingLog4Statistics {
	// 单例
	public static LandingLog4Statistics instance = new LandingLog4Statistics();

	private LandingLog4Statistics() {
	}
	
	// 日志
	private Log log = LogFactory.getLog(LandingLog4Statistics.class);
	
	// 日期格式化
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 线程副本
	private ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();
	
	
	// 获取单例
	public static LandingLog4Statistics getInstance() {
		return instance;
	}
	
	// 登录状态
	public static final String SUCCESS = "SUCCESS";
	public static final String FAIL = "FAILED";
	
	// 操作行为
	public static final String LOGIN = "login";
	public static final String LOGOUT = "logout";

	// 记录登录前的sessionId，并保存到线程副本
	public void addBeforeSessionId(String sessionId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("before_session_id", sessionId);
		threadLocal.set(map);
	}

	// 打印日志
	public void printLog(String action, String sessionId, int userId, String status) {
		// 捕获异常，不要影响业务
		try {
			Map<String,Object> map = threadLocal.get();
			// 如果没有先加入登录前的sessionId直接返回
			if(map == null) {
				return;
			}
			
			// 追加日志内容
			map.put("action", action);
			map.put("after_session_id", sessionId);
			if(userId > 0) {
				map.put("user_id", userId);
			} else {
				map.put("user_id", "");
			}
			map.put("status", status);
			
			// 格式化日志
			Gson gson = new GsonBuilder().serializeNulls().create();
			StringBuilder buff = new StringBuilder();
			buff.append("@@UID-SID-TRACE@@\t");
			buff.append(sdf.format(new Date()) + "\t");
			buff.append(gson.toJson(map));
			
			// 打印日志
			log.info(buff.toString());
		} catch(Throwable t) {
			
		}
	}
}
