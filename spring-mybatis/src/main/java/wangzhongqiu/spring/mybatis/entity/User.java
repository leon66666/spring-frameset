package wangzhongqiu.spring.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户 user_
 *
 * @author
 */
public class User implements Serializable {
    //串行版本ID
    private static final long serialVersionUID = 1265643181793635458L;

    private Integer userId;

    /**
     * 是否启用
     * enabled
     */
    private Boolean enabled;

    /**
     * 是否通过身份验证
     * id_passed
     */
    private Boolean idPassed;

    /**
     * 是否通过手机验证
     * mobile_passed
     */
    private Boolean mobilePassed;

    /**
     * 注册时间
     * register_time
     */
    private Date registerTime;

    /**
     * 用户名(HXB+随机数)
     * user_name
     */
    private String userName;

    /**
     * 渠道,目前只有至信
     * utm_source
     */
    private String utmSource;

    /**
     * 直接邀请人userId
     * invite_uid
     */
    private Integer inviteUid;

    /**
     * 借款或理财或系统用户
     * intention
     */
    private String intention;

    /**
     * 手机号
     * mobile
     */
    private String mobile;

    /**
     * 注册来源
     * source
     */
    private String source;

    /**
     * 销售人员代码
     * source_value
     */
    private String sourceValue;

    /**
     * 推广来源
     * promotion
     */
    private String promotion;

    /**
     * 托管银行的账户id
     * account_id
     */
    private Integer accountId;

    /**
     * 邮箱
     * email
     */
    private String email;

    /**
     * 外部渠道的user表唯一键
     * ref_id
     */
    private String refId;

    /**
     * 用户角色(普通用户CUSTOMER,员工EMPLOYEE,销售SALES)
     * invite_role
     */
    private String inviteRole;

    /**
     * 邀请码
     * invite_serial
     */
    private String inviteSerial;

    /**
     * 市场来源  app端Android设备区分小米市场、华为市场等
     * market_source
     */
    private String marketSource;

    /**
     * mysql数据库乐观锁
     * version
     * 默认：1
     */
    private Integer version = 0;

    /**
     * 数据库创建时间
     * create_time
     */
    private Date createTime;

    /**
     * 数据库更新时间
     * update_time
     */
    private Date updateTime;

    /**
     * 获取 user_.user_id
     *
     * @return user_.user_id
     */
    public final Integer getUserId() {
        return userId;
    }

    /**
     * 设置 user_.user_id
     *
     * @param userId user_.user_id
     */
    public final void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取 是否启用 user_.enabled
     *
     * @return 是否启用
     */
    public final Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置 是否启用 user_.enabled
     *
     * @param enabled 是否启用
     */
    public final void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取 是否通过身份验证 user_.id_passed
     *
     * @return 是否通过身份验证
     */
    public final Boolean getIdPassed() {
        return idPassed;
    }

    /**
     * 设置 是否通过身份验证 user_.id_passed
     *
     * @param idPassed 是否通过身份验证
     */
    public final void setIdPassed(Boolean idPassed) {
        this.idPassed = idPassed;
    }

    /**
     * 获取 是否通过手机验证 user_.mobile_passed
     *
     * @return 是否通过手机验证
     */
    public final Boolean getMobilePassed() {
        return mobilePassed;
    }

    /**
     * 设置 是否通过手机验证 user_.mobile_passed
     *
     * @param mobilePassed 是否通过手机验证
     */
    public final void setMobilePassed(Boolean mobilePassed) {
        this.mobilePassed = mobilePassed;
    }

    /**
     * 获取 注册时间 user_.register_time
     *
     * @return 注册时间
     */
    public final Date getRegisterTime() {
        return registerTime;
    }

    /**
     * 设置 注册时间 user_.register_time
     *
     * @param registerTime 注册时间
     */
    public final void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取 用户名(HXB+随机数) user_.user_name
     *
     * @return 用户名(HXB+随机数)
     */
    public final String getUserName() {
        return userName;
    }

    /**
     * 设置 用户名(HXB+随机数) user_.user_name
     *
     * @param userName 用户名(HXB+随机数)
     */
    public final void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取 渠道,目前只有至信 user_.utm_source
     *
     * @return 渠道, 目前只有至信
     */
    public final String getUtmSource() {
        return utmSource;
    }

    /**
     * 设置 渠道,目前只有至信 user_.utm_source
     *
     * @param utmSource 渠道,目前只有至信
     */
    public final void setUtmSource(String utmSource) {
        this.utmSource = utmSource == null ? null : utmSource.trim();
    }

    /**
     * 获取 直接邀请人userId user_.invite_uid
     *
     * @return 直接邀请人userId
     */
    public final Integer getInviteUid() {
        return inviteUid;
    }

    /**
     * 设置 直接邀请人userId user_.invite_uid
     *
     * @param inviteUid 直接邀请人userId
     */
    public final void setInviteUid(Integer inviteUid) {
        this.inviteUid = inviteUid;
    }

    /**
     * 获取 借款或理财或系统用户 user_.intention
     *
     * @return 借款或理财或系统用户
     */
    public final String getIntention() {
        return intention;
    }

    /**
     * 设置 借款或理财或系统用户 user_.intention
     *
     * @param intention 借款或理财或系统用户
     */
    public final void setIntention(String intention) {
        this.intention = intention == null ? null : intention.trim();
    }

    /**
     * 获取 手机号 user_.mobile
     *
     * @return 手机号
     */
    public final String getMobile() {
        return mobile;
    }

    /**
     * 设置 手机号 user_.mobile
     *
     * @param mobile 手机号
     */
    public final void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取 注册来源 user_.source
     *
     * @return 注册来源
     */
    public final String getSource() {
        return source;
    }

    /**
     * 设置 注册来源 user_.source
     *
     * @param source 注册来源
     */
    public final void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 获取 销售人员代码 user_.source_value
     *
     * @return 销售人员代码
     */
    public final String getSourceValue() {
        return sourceValue;
    }

    /**
     * 设置 销售人员代码 user_.source_value
     *
     * @param sourceValue 销售人员代码
     */
    public final void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue == null ? null : sourceValue.trim();
    }

    /**
     * 获取 推广来源 user_.promotion
     *
     * @return 推广来源
     */
    public final String getPromotion() {
        return promotion;
    }

    /**
     * 设置 推广来源 user_.promotion
     *
     * @param promotion 推广来源
     */
    public final void setPromotion(String promotion) {
        this.promotion = promotion == null ? null : promotion.trim();
    }

    /**
     * 获取 托管银行的账户id user_.account_id
     *
     * @return 托管银行的账户id
     */
    public final Integer getAccountId() {
        return accountId;
    }

    /**
     * 设置 托管银行的账户id user_.account_id
     *
     * @param accountId 托管银行的账户id
     */
    public final void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取 邮箱 user_.email
     *
     * @return 邮箱
     */
    public final String getEmail() {
        return email;
    }

    /**
     * 设置 邮箱 user_.email
     *
     * @param email 邮箱
     */
    public final void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取 外部渠道的user表唯一键 user_.ref_id
     *
     * @return 外部渠道的user表唯一键
     */
    public final String getRefId() {
        return refId;
    }

    /**
     * 设置 外部渠道的user表唯一键 user_.ref_id
     *
     * @param refId 外部渠道的user表唯一键
     */
    public final void setRefId(String refId) {
        this.refId = refId == null ? null : refId.trim();
    }

    /**
     * 获取 用户角色(普通用户CUSTOMER,员工EMPLOYEE,销售SALES) user_.invite_role
     *
     * @return 用户角色(普通用户CUSTOMER, 员工EMPLOYEE, 销售SALES)
     */
    public final String getInviteRole() {
        return inviteRole;
    }

    /**
     * 设置 用户角色(普通用户CUSTOMER,员工EMPLOYEE,销售SALES) user_.invite_role
     *
     * @param inviteRole 用户角色(普通用户CUSTOMER,员工EMPLOYEE,销售SALES)
     */
    public final void setInviteRole(String inviteRole) {
        this.inviteRole = inviteRole == null ? null : inviteRole.trim();
    }

    /**
     * 获取 邀请码 user_.invite_serial
     *
     * @return 邀请码
     */
    public final String getInviteSerial() {
        return inviteSerial;
    }

    /**
     * 设置 邀请码 user_.invite_serial
     *
     * @param inviteSerial 邀请码
     */
    public final void setInviteSerial(String inviteSerial) {
        this.inviteSerial = inviteSerial == null ? null : inviteSerial.trim();
    }

    /**
     * 获取 市场来源  app端Android设备区分小米市场、华为市场等 user_.market_source
     *
     * @return 市场来源  app端Android设备区分小米市场、华为市场等
     */
    public final String getMarketSource() {
        return marketSource;
    }

    /**
     * 设置 市场来源  app端Android设备区分小米市场、华为市场等 user_.market_source
     *
     * @param marketSource 市场来源  app端Android设备区分小米市场、华为市场等
     */
    public final void setMarketSource(String marketSource) {
        this.marketSource = marketSource == null ? null : marketSource.trim();
    }

    /**
     * 获取 mysql数据库乐观锁 user_.version
     *
     * @return mysql数据库乐观锁
     */
    public final Integer getVersion() {
        return version;
    }

    /**
     * 设置 mysql数据库乐观锁 user_.version
     *
     * @param version mysql数据库乐观锁
     */
    public final void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取 数据库创建时间 user_.create_time
     *
     * @return 数据库创建时间
     */
    public final Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 数据库创建时间 user_.create_time
     *
     * @param createTime 数据库创建时间
     */
    public final void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 数据库更新时间 user_.update_time
     *
     * @return 数据库更新时间
     */
    public final Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 数据库更新时间 user_.update_time
     *
     * @param updateTime 数据库更新时间
     */
    public final void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", userId=").append(userId);
        sb.append(", enabled=").append(enabled);
        sb.append(", idPassed=").append(idPassed);
        sb.append(", mobilePassed=").append(mobilePassed);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", userName=").append(userName);
        sb.append(", utmSource=").append(utmSource);
        sb.append(", inviteUid=").append(inviteUid);
        sb.append(", intention=").append(intention);
        sb.append(", mobile=").append(mobile);
        sb.append(", source=").append(source);
        sb.append(", sourceValue=").append(sourceValue);
        sb.append(", promotion=").append(promotion);
        sb.append(", accountId=").append(accountId);
        sb.append(", email=").append(email);
        sb.append(", refId=").append(refId);
        sb.append(", inviteRole=").append(inviteRole);
        sb.append(", inviteSerial=").append(inviteSerial);
        sb.append(", marketSource=").append(marketSource);
        sb.append(", version=").append(version);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}