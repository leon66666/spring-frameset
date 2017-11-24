package wangzhongqiu.spring.mybatis.datasource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class DataSourceRouter extends AbstractRoutingDataSource {

    private static Log log = LogFactory.getLog(DataSourceRouter.class);

    @Autowired
    private JedisPool pool;

    /**
     * redis的读写分离标记在每个服务器上的缓存时间间隔
     */
    private static final long RW_SPLITTING_CLOSED_TIMEOUT = 10 * 1000L;

    /**
     * redis的读写分离标记在每个服务器上的缓存
     */
    private boolean RWSplittingClosed_cache = false;

    /**
     * 上一次的缓存时间
     */
    private long RWSplittingClosed_cache_time = 0L;

    /*    @Override
        protected Object determineCurrentLookupKey() {
            if (System.currentTimeMillis() - RWSplittingClosed_cache_time <= RW_SPLITTING_CLOSED_TIMEOUT) {
                if (RWSplittingClosed_cache) {
                    return AvailableDataSources.WRITE;
                }
                return DataSourceProvider.getDataSource();
            }
            Jedis jedis = null;
            try {
                jedis = pingJedis();
                if (jedis == null) {
                    return AvailableDataSources.WRITE;
                }
                Boolean masterForced = Boolean.valueOf(jedis.get("RWSplittingClosed")); // TODO
                RWSplittingClosed_cache = masterForced;
                RWSplittingClosed_cache_time = System.currentTimeMillis();
                if (masterForced) {
                    return AvailableDataSources.WRITE;
                }
                return DataSourceProvider.getDataSource();
            } catch (Exception e) {
                log.error(e.getMessage());
                return AvailableDataSources.WRITE;
            } finally {
                if (jedis != null) {
                    pool.returnResource(jedis);
                }
            }
        }*/
    @Override
    protected Object determineCurrentLookupKey() {
        return AvailableDataSources.WRITE;
//        return DataSourceProvider.getDataSource();
    }

    /**
     * 为了减少redis压力,不再ping了
     *
     * @return
     */
    private Jedis pingJedis() {
        try {
            Jedis jedis = pool.getResource();
            jedis.connect();
            return jedis;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
