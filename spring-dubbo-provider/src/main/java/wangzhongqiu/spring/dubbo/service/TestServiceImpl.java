package wangzhongqiu.spring.dubbo.service;

import wangzhongqiu.spring.core.service.TestService;

/**
 * @author wangzhongqiu
 * @date 2017/11/20.
 */
public class TestServiceImpl implements TestService {
    @Override
    public void println(String str) {
        System.out.println(str);
    }
}
