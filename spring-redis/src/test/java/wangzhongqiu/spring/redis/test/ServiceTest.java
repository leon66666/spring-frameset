package wangzhongqiu.spring.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wangzhongqiu.spring.redis.service.RedisCommonService;

/**
 * @author wangzhongqiu
 * @date 2017/11/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring.xml"})
public class ServiceTest {
    @Autowired
    private RedisCommonService redisCommonService;

    @Test
    public void test() throws Exception {
        try {
            redisCommonService.set("loan:1", "1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
