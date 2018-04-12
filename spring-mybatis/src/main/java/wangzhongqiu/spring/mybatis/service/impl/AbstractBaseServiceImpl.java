package wangzhongqiu.spring.mybatis.service.impl;

import org.apache.log4j.Logger;
import wangzhongqiu.spring.mybatis.dao.BaseDao;
import wangzhongqiu.spring.mybatis.service.BaseService;

public abstract class AbstractBaseServiceImpl<T> implements BaseService<T> {

	protected BaseDao<T> baseDao;

	protected Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public int deleteByPrimaryKey(Integer id){
		return baseDao.deleteByPrimaryKey(id);
	}

    @Override
	public T selectByPrimaryKey(Integer id){
    	return baseDao.selectByPrimaryKey(id);
    }

	@Override
	public int updateByPrimaryKey(T t){
		return baseDao.updateByPrimaryKey(t);
	}

	@Override
	public int insert(T t){
		return baseDao.insert(t);
	}

	@Override
	public int insertSelective(T t) {
		return baseDao.insertSelective(t);
	}

	@Override
	public int updateByPrimaryKeySelective(T t) {
		return baseDao.updateByPrimaryKeySelective(t);
	}
}
