package wangzhongqiu.spring.mybatis.service;

/**
 * 
 * 
 *
 * Description: 服务层基础接口
 *
 * @author tianye
 * @version V1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年8月29日下午3:07:04    Administrator       V1.0        
 * </pre>
 */
public interface BaseService<T> {

    /**
     * 根据主键删除
     * @param id
     */
	int deleteByPrimaryKey(Integer id);

    /**
     * 插入
     * @param t
     */
    int insert(T t);

    /**
     * 插入
     * @param t
     */
    int insertSelective(T t);

    /**
     * 更新
     * @param t
     */
    int updateByPrimaryKey(T t);

    /**
     * 更新
     * @param t
     */
    int updateByPrimaryKeySelective(T t);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    T selectByPrimaryKey(Integer id);

    /**
     * init
     */
    void init();

}
