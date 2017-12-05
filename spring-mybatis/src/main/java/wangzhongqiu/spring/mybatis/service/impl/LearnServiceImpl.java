package wangzhongqiu.spring.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wangzhongqiu.spring.mybatis.dao.LearnResouceDao;
import wangzhongqiu.spring.mybatis.entity.LearnResouce;
import wangzhongqiu.spring.mybatis.service.LearnService;

import java.util.List;
import java.util.Map;

/**
 * Created by tengj on 2017/4/7.
 */
@Service
@Transactional
public class LearnServiceImpl implements LearnService {

    @Autowired
    LearnResouceDao learnResouceDao;

    @Override
    public int add(LearnResouce learnResouce) {
        return this.learnResouceDao.add(learnResouce);
    }

    @Override
    public int update(LearnResouce learnResouce) {
        return this.learnResouceDao.update(learnResouce);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.learnResouceDao.deleteByIds(ids);
    }

    @Override
    public LearnResouce queryLearnResouceById(Long id) {
        return this.learnResouceDao.queryLearnResouceById(id);
    }

    @Override
    public List<LearnResouce> queryLearnResouceList(Map<String, Object> params) {
//        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return this.learnResouceDao.queryLearnResouceList(params);
    }
}
