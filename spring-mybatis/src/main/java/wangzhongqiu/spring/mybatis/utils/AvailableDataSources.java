package wangzhongqiu.spring.mybatis.utils;

public enum AvailableDataSources {
    READ() {
        @Override
        public String toString() {
            return "READ";
        }
    },

    WRITE() {
        @Override
        public String toString() {
            return "WRITE";
        }
    }
}
