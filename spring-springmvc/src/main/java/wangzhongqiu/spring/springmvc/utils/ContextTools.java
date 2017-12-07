package wangzhongqiu.spring.springmvc.utils;

import org.springframework.stereotype.Service;
import wangzhongqiu.spring.core.constants.Constants;
import wangzhongqiu.spring.core.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理redis中user对象的方法
 */
@Service
public class ContextTools {
    /**
     * 返回当前的用户
     *
     * @return
     */
    public static User getSecurityUser(HttpServletRequest request) {
        String userId_s = request.getParameter(Constants.USER_ID);
        if (userId_s != null) {
            Integer userId = Integer.parseInt(userId_s);
            User user = new User();
            user.setUserId(userId);
            return user;
        }
        return null;
    }

    /**
     * 登录成功后将user对象的json存到redis中
     *
     * @param request
     * @param user
     */
    @Deprecated
    public static void setSecurityUser(HttpServletRequest request, User user) {
//        if (user == null) {
//            return;
//        }
//        try {
//            Object _u = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            if (_u != null && _u instanceof SecurityUser) {
//                SecurityUser userDetails = (SecurityUser) _u;
//                userDetails.setUser(user);
//                redisService.set(keyPrefixHome + userDetails.getUser().getUserId(), USER_CHANGE);
//                // 刷新session
//                try {
//                	request.getSession()
//                    .setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
//                            SecurityContextHolder.getContext());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
