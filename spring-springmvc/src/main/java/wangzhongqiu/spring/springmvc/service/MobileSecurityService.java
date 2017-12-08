package wangzhongqiu.spring.springmvc.service;


import wangzhongqiu.spring.core.model.User;
import wangzhongqiu.spring.core.utils.CallbackResult;

public interface MobileSecurityService {

    public CallbackResult checkCashPassword(User user, String cashPassword, String version);

}
