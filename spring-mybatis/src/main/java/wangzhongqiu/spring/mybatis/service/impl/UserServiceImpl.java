package wangzhongqiu.spring.mybatis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wangzhongqiu.spring.mybatis.dao.UserDAO;
import wangzhongqiu.spring.mybatis.entity.User;
import wangzhongqiu.spring.mybatis.service.UserService;

import javax.annotation.PostConstruct;

/**
 * @author zhengdongwen
 * Created on 2017/11/17.
 * @description:
 */
@Service
public class UserServiceImpl extends AbstractBaseServiceImpl<User> implements UserService {
    @Autowired
    private UserDAO userDAO;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @PostConstruct
    public void init() {
        this.baseDao = userDAO;
    }


}