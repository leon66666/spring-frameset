package wangzhongqiu.spring.mybatis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wangzhongqiu.spring.mybatis.cache.LearnCacheService;
import wangzhongqiu.spring.mybatis.entity.LearnResouce;
import wangzhongqiu.spring.mybatis.entity.User;
import wangzhongqiu.spring.mybatis.service.LearnService;
import wangzhongqiu.spring.mybatis.service.UserService;

/**
 * @author wangzhongqiu
 * @date 2017/11/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring.xml"})
public class ServiceTest {
    @Autowired
    LearnService learnService;
    @Autowired
    LearnCacheService learnCacheService;

    @Test
    public void test() throws Exception {
        try {
            Long id = 999L;
            LearnResouce learnResouce = learnCacheService.queryLearnResouceById(id);
            System.out.println(learnResouce);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
