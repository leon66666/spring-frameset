package wangzhongqiu.spring.core.model;

/**
 * User表roles字段
 * 当roles=5时表明 普通用户+借出者
 * 当roles=7时表明 普通用户+借出者+借入者
 */
public enum UserRole {
    BORROWER() {

        @Override
        public String toString() {
            return "借款人";
        }
    },
    LENDER() {

        @Override
        public String toString() {
            return "理财人";
        }
    },
    PLATFORM() {
        @Override
        public String toString() {
            return "平台用户";
        }
    },
    CHANNEL() {
        @Override
        public String toString() {
            return "机构用户";
        }
    };

    @Override
    public abstract String toString();
}
