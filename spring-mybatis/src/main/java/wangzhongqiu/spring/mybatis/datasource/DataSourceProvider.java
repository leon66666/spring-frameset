package wangzhongqiu.spring.mybatis.datasource;

public class DataSourceProvider {
    private static final ThreadLocal<AvailableDataSources> dataSourceHolder = new ThreadLocal<AvailableDataSources>();

    public static void setDataSource(final AvailableDataSources customerType) {
        dataSourceHolder.set(customerType);
    }

    public static AvailableDataSources getDataSource() {
        AvailableDataSources availableDataSources = (AvailableDataSources) dataSourceHolder.get();
        return availableDataSources;
    }

    public static void clearDataSource() {
        dataSourceHolder.remove();
    }

}
