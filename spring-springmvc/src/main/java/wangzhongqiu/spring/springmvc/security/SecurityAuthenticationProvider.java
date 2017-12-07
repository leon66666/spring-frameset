/*
 * 
 * 
 */
package wangzhongqiu.spring.springmvc.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import wangzhongqiu.spring.core.model.SecurityUser;
import wangzhongqiu.spring.core.model.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 */
public class SecurityAuthenticationProvider extends DaoAuthenticationProvider {

    private static Log log = LogFactory.getLog(SecurityAuthenticationProvider.class);

    private static Integer[] specialUser = { 1, 2, 33, 92343, 32, 136682 };
//    @Autowired
//    private UserService service;
//    @Autowired
//    private UserLoanRecordService userLoanRecordService;
//
//    @Autowired
//    private UserService userService;

    private static String lastLoginCheckTime;

    static {
        try {
            String path = SecurityAuthenticationProvider.class.getResource("")
                    .getPath();
            String fileName = path + "lastLoginCheckTime.txt";
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String r = br.readLine();
            if (r != null) {
                lastLoginCheckTime = r;
            } else {
                lastLoginCheckTime = new Date().toString();
            }

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token)
            throws AuthenticationException {
        Integer userId = ((SecurityUser) userDetails).getUser().getUserId();
        for (Integer id : specialUser) {
            if (id.equals(userId)) {
                throw new AuthenticationServiceException("specialUser could not login");
            }
        }

        SecurityWebAuthenticationDetails securityWebAuthenticationDetails = (SecurityWebAuthenticationDetails) token
                .getDetails();
        String username = ((SecurityUser) userDetails).getUser().getUsername();
        String mobile = ((SecurityUser) userDetails).getUser().getMobile();
        // 第三方登陆时, username是null, 所以在spring的userdetails里设为mobile
        if (username != null && username.equals(securityWebAuthenticationDetails.getAuthenticatedUsername())) {
            log.info("username:" + username);
        } else if (mobile != null && mobile.equals(securityWebAuthenticationDetails.getAuthenticatedUsername())) {
            // 此请求已经通过非密码方式验证通过,不需要再次进行密码验证
            log.info("mobile:" + mobile);
        } else {
            super.additionalAuthenticationChecks(userDetails, token);
        }

        String ip = securityWebAuthenticationDetails.getRemoteIp();
        if (ip != null && ip.contains(",")) {
            String[] ips = ip.split(",");
            if (ips != null && ips.length != 0) {
                ip = ips[0];
            }
        }
        Date loginTime = new Date();
        log.info("login info:\tusername:" + userDetails.getUsername() + "\tremote ip: " + ip + "\tlogin time:"
                + loginTime);

        if (userDetails instanceof SecurityUser) {
            User user = ((SecurityUser) userDetails).getUser();

            if (!SecurityUser.GUEST.equals(user.getUsername())) {

                Date lastLoginTime = user.getLastLoginTime();
                try {
                    if (lastLoginTime != null
                            && lastLoginTime.before(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                    .parse(lastLoginCheckTime))) { // 在20131014上线时,
                                                                   // 某些用户的数据可能没有被及时更新,
                                                                   // 等用户登录时启动此更新任务
//                        userLoanRecordService.updatePrincipalsNInterests(user.getUserId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("判断上次登录时间异常", e);
                    throw new RuntimeException("判断上次登录时间异常");
                }
                user.setLastLoginIP(ip);
                user.setLastLoginTime(loginTime);
//                service.updateUser(user);
            }
        }

    }
}
