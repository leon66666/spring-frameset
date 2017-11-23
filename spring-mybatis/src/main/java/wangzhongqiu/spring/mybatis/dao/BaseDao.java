package wangzhongqiu.spring.mybatis.dao;

import java.util.List;

/**
 * @author wangzhongqiu
 *         Created on 2017/11/17.
 * @description:
 */
public interface BaseDao<T> {
    /**
     * 根据ID删除
     * @param id 主键ID
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加对象所有字段
     * @param t 插入字段对象(必须含ID）
     */
    int insert(T t);

    /**
     * 添加对象对应字段
     * @param t 插入字段对象(必须含ID）
     */
    int insertSelective(T t);

    /**
     * 根据ID查询
     * @param id 主键ID
     */
    T selectByPrimaryKey(Integer id);

    /**
     *
     */
    List<T> selectAll();

    /**
     * 根据ID修改所有字段(必须含ID）
     * @param t 修改字段对象(必须含ID）
     */
    int updateByPrimaryKey(T t);

    /**
     * 根据ID修改对应字段
     * @param t 修改字段对象(必须含ID）
     */
    int updateByPrimaryKeySelective(T t);
}
