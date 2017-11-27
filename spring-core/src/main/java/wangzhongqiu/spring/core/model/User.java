package wangzhongqiu.spring.core.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * 用户对象
 */
public class User implements java.io.Serializable, Cloneable {

    private static final long serialVersionUID = -7373867083892302327L;
    /**
     * 返现抵用金
     */
    public static final int cashBackMarketingUserID = 136678;
    /**
     * 优惠劵抵用金
     */
    public static final int couponMarketingUserID = 136679;

    /**
     * 市场活动账户用户 加息金(目前不用)
     */
    public static final int increaseInterestRateMarketingUserID = 136680;
    /**
     * 市场活动账户用户 体验金(目前不用)
     */
    public static final int experienceMarketingUserID = 136681;
    /**
     * 市场活动账户用户 抵用金
     */
    public static final int MarketingUserID = 136682;
    /**
     * 保证金账户用户
     */
    public static final int GuaranteeUserID = 92343;
    /**
     * 平衡金账户用户
     */
    public static final int BalanceUserID = 32;
    /**
     * 至信账户用户
     */
    public static final int zhixinUserID = 33;
    /**
     * 红小宝账户用户
     */
    public static final int hoomsunUserID = 2;
    /**
     * 存管自有子账户
     */
    public static final int equityFundUserID = 9;
    /**
     * 红上XX帐户用户 不用了,改为红上至信
     */
    public static final int HxbLenderUserID = 8;
    /**
     * 系统用户
     */
    public static final int SystemUserID = 1;
    /**
     * 红小宝账户用户的支出账户
     */
    public static final int hoomsunUserID_Expense = 7;

    public static final Integer[] SPECIAL_USERS = new Integer[]{MarketingUserID, experienceMarketingUserID, increaseInterestRateMarketingUserID, GuaranteeUserID, BalanceUserID,
            zhixinUserID, hoomsunUserID, SystemUserID, hoomsunUserID_Expense, equityFundUserID, couponMarketingUserID, cashBackMarketingUserID};

    public static boolean isSpecialUser(int userId) {
        for (int isSpecialUser : User.SPECIAL_USERS) {
            if (isSpecialUser == userId) {
                return true;
            }
        }
        return false;
    }

    // 这几个用户的pointlog单独放在point_log_special表中
    public static final Integer[] SPECIAL_POINT_LOG_USERS = new Integer[]{GuaranteeUserID, BalanceUserID,
            hoomsunUserID, SystemUserID, MarketingUserID, experienceMarketingUserID, increaseInterestRateMarketingUserID, zhixinUserID, hoomsunUserID_Expense, equityFundUserID, couponMarketingUserID, cashBackMarketingUserID};

    public static boolean isSpecialPointLogUser(int userId) {
        for (int isSpecialUser : User.SPECIAL_POINT_LOG_USERS) {
            if (isSpecialUser == userId) {
                return true;
            }
        }
        return false;
    }

    /**
     * 平台账户
     */
    public static final Integer[] PLATFORM_USERS = new Integer[]{GuaranteeUserID, BalanceUserID,
            hoomsunUserID, MarketingUserID, hoomsunUserID_Expense, equityFundUserID, couponMarketingUserID, cashBackMarketingUserID};

    /**
     * 是否是平台账户
     *
     * @param userId
     * @return
     */
    public static boolean isPlatformUser(int userId) {
        for (int isPlatformUser : User.PLATFORM_USERS) {
            if (isPlatformUser == userId) {
                return true;
            }
        }
        return false;
    }

    /**
     * 类似账户(平台账户互转，不调用存管接口,红小宝账户用户,红上XX帐户用户,至信账户用户,平衡金账户用户,存管自有子账户)
     */
    public static final Integer[] SIMILAR_USERS = new Integer[]{hoomsunUserID, HxbLenderUserID, zhixinUserID, BalanceUserID, equityFundUserID, couponMarketingUserID, cashBackMarketingUserID};

    /**
     * 是否是类似账户
     *
     * @param userId
     * @return
     */
    public static boolean isSimilarUser(int userId) {
        for (int isSimilarUser : User.SIMILAR_USERS) {
            if (isSimilarUser == userId) {
                return true;
            }
        }
        return false;
    }

    private Integer userId;
    private String username;
    private String mobile;
    private String password;
    private Date registerTime;
    private boolean enabled = true;
    // private UserCreditLevel creditLevel; // hibernate关联的方式速度太慢, 已经删掉
    private boolean mobilePassed; // 手机验证（成为借入<出>者）
    private boolean idPassed; // 身份证验证（成为借入<出>者）
    private boolean detailInfoFinished; // 详细信息(成为借入者)
    private boolean basicInfoFinished;// 个人基本信息(注册完后的第一件事情)

    private String utm_source; // 合作伙伴
    private boolean forbidLoaned; // 禁止贷款
    private boolean forbidComment;// 禁止评论
    private String passwordToken;// 密码重置token
    private String products;
    /**
     * 直接邀请用户Id
     */
    private Integer inviteUid;
    private String lastLoginIP;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 销售人员代码
     */
    private String sourceValue;
    private int version = 0;

    /**
     * 交易密码
     */
    private String cashPassword;

    /**
     * 借款人对应的客服人员用户名
     */
    private String customerName;

    /**
     * 网站推广来源
     */
    private String promotion;

    /**
     * 登录密码对应的盐串
     */
    private String passwordSalt;

    /**
     * 交易密码对应的盐串
     */
    private String cashPasswordSalt;

    /**
     * 存管托管用户表外键
     */
    private Integer accountId;

    /**
     * 外部渠道的user表唯一键
     */
    private String refId;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 市场来源  app 端 Android 区分小米市场  华为市场 ……
     */
    private String marketSource;


    public void setMarketSource(String marketSource) {
        this.marketSource = marketSource;
    }

    /**
     * 邀请码
     */
    private String inviteSerial;

    /**
     * 用户角色(普通用户CUSTOMER,员工EMPLOYEE,销售SALES)
     */
    private String inviteRole;


    public String getInviteSerial() {
        return inviteSerial;
    }

    public void setInviteSerial(String inviteSerial) {
        this.inviteSerial = inviteSerial;
    }

    public String getInviteRole() {
        return inviteRole;
    }

    public void setInviteRole(String inviteRole) {
        this.inviteRole = inviteRole;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterTime() {
        return this.registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public boolean isMobilePassed() {
        return mobilePassed;
    }

    public void setMobilePassed(boolean mobilePassed) {
        this.mobilePassed = mobilePassed;
    }

    public boolean isBasicInfoFinished() {
        return basicInfoFinished;
    }

    public void setBasicInfoFinished(boolean basicInfoFinished) {
        this.basicInfoFinished = basicInfoFinished;
    }

    public boolean isDetailInfoFinished() {
        return detailInfoFinished;
    }

    public void setDetailInfoFinished(boolean detailInfoFinished) {
        this.detailInfoFinished = detailInfoFinished;
    }

    public boolean isIdPassed() {
        return idPassed;
    }

    public void setIdPassed(boolean idPassed) {
        this.idPassed = idPassed;
    }

    public String getUtm_source() {
        return utm_source;
    }

    public void setUtm_source(String utm_source) {
        this.utm_source = utm_source;
    }

    public boolean isForbidComment() {
        return forbidComment;
    }

    public void setForbidComment(boolean forbidComment) {
        this.forbidComment = forbidComment;
    }

    public boolean isForbidLoaned() {
        return forbidLoaned;
    }

    public void setForbidLoaned(boolean forbidLoaned) {
        this.forbidLoaned = forbidLoaned;
    }


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getPasswordToken() {
        return passwordToken;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
    }

    public Integer getInviteUid() {
        return inviteUid;
    }

    public void setInviteUid(Integer inviteUid) {
        this.inviteUid = inviteUid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId && (this.userId == null || !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.userId != null ? this.userId.hashCode() : 0);
        return hash;
    }

    /**
     * 是否是每月还息到期还本用户
     *
     * @return
     */
    public boolean isFXHB() {
        if (getUtm_source() != null && getUtm_source().contains("fxhb-")) {
            return true;
        } else {
            return false;
        }
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue;
    }

    public String getCashPassword() {
        return cashPassword;
    }

    public void setCashPassword(String cashPassword) {
        this.cashPassword = cashPassword;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getCashPasswordSalt() {
        return cashPasswordSalt;
    }

    public void setCashPasswordSalt(String cashPasswordSalt) {
        this.cashPasswordSalt = cashPasswordSalt;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * 身份认证、手机认证、交易密码、昵称是否通过
     *
     * @return
     */
    public boolean isRolePassed() {
        return this.isIdPassed() && this.isMobilePassed() && hasCashPassword();
    }

    /**
     * 判断是否有交易密码
     *
     * @return
     */
    public boolean hasCashPassword() {
        return cashPassword != null && !cashPassword.trim().equals("");
    }

    /**
     * 判断是否有密码
     *
     * @return
     */
    public boolean hasPassword() {
        return password != null && !password.trim().equals("");
    }

    /**
     * 身份认证、手机认证、昵称是否通过
     *
     * @return
     */
    public boolean isNewRolePassed() {
        return this.isIdPassed() && this.isMobilePassed();
    }

}
