package wangzhongqiu.spring.mybatis.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import wangzhongqiu.spring.mybatis.entity.LearnResouce;
import wangzhongqiu.spring.mybatis.service.LearnService;

/**
 * @author wangzhongqiu
 * @date 2017/12/5.
 */
@Service
public class LearnCacheService {
    @Autowired
    LearnService learnService;

    @Cacheable(value = "default")
    public LearnResouce queryLearnResouceById(Long id) {
        return this.learnService.queryLearnResouceById(id);
    }
}
