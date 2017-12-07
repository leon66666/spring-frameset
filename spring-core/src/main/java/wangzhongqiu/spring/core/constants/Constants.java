package wangzhongqiu.spring.core.constants;

import wangzhongqiu.spring.core.utils.accessor.AccessorFactory;
import wangzhongqiu.spring.core.utils.accessor.PropertiesAccessor;

import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

/**
 * 系统常量
 */
public class Constants {

    public static final String Y = "Y";

    public static final String N = "N";

    private static PropertiesAccessor propertiesAccessor = AccessorFactory.createPropertiesAccessor(new String[]{"config.properties",
            "sms-zhuwang.properties"});

    public static String getPropertyValue(String key) {
        return propertiesAccessor.getValue(key);
    }

    public static String getPropertyValue(String key, Object... arguments) {
        return propertiesAccessor.getValue(key, arguments);
    }



    /**
     * 从前端获得request中的userId(PC和mobile用)
     */
    public static final String USER_ID = "userId";
    /**
     * 从前端request中获得
     * 用户客户端IP地址(用于防刷)
     */
    public static final String IP_ADDRESS = "ipAddress";
    /**
     * 从前端request中获得
     * 用户浏览器信息(用于统计)
     */
    public static final String USER_AGRENT = "userAgent";
    /**
     * 从前端request中获得
     * PC购买或是移动端购买(对应数据库tradeMethod字段)
     * 默认为PC
     */
    public static final String PLATFORM = "platform";

    /**
     * 从前端获得request中的staffId(mgmt后台用)
     */
    public static final String STAFF_NAME = "staffName";

    /**
     * redis中保存的user相关信息json(前台用)
     * u:{userid}
     */
    public static final String REDIS_KEY_U = "u:";
    public static final String REDIS_KEY_ESCROW = "ESCROW_TIME:";

    public static final String REDIS_WHITE_LIST = "white_list";

    //债转分支  绑卡接口兼容标示
    public static final String LOAN_TRANSFER_BIND_CARD_COMPATIBLE = "BIND_CARD_COMPATIBLE";

    public static final String PLATFORM_ANDRORID = "from-android";
    public static final String PLATFORM_IOS = "from-ios";
    public static final String PLATFORM_WEBSITE = "from-website";
    public static final String PLATFORM_H5 = "from-h5";

//    /**
//     * 用户未读站内信数量
//     */
//    public static final String REDIS_USER_UNREAD_COUNT = "unread:";
//    public static final String REDIS_USER_UNREAD_COUNT_TEST = "unread_test:";

    /**
     * 用户实名认证时间
     */
    public static final String REDIS_KEY_USER_AUTH_TIMES = "u_auth:";

    /**
     * redis中保存的user相关信息json(前台用)
     * u_login:{userid}
     * {"mobile_sessionid":mobile_sessionid,"platform":platform}
     */
    public static final String REDIS_KEY_USER_LOGIN = "u_login:";

    /**
     * 用户是否在20160731之前投资过
     */
    public static final String IS_HAS_INVEST_WITHIN_20160731 = "is_has_invest_within_20160731_";

    /**
     * 新人活动 注册24小时之内是否投资
     */
    public static final String HAS_EVEN_INVEST_WITHIIN_24HOUR = "HAS_EVEN_INVEST_WITHIIN_24HOUR_";
    /**
     * 新人活动 注册24小时之内是否充值
     */
    public static final String HAS_RECHARGE_WITHIIN_24HOUR = "HAS_RECHARGE_WITHIIN_24HOUR_";
    /**
     * 新人活动 注册24小时之内是否开户
     */
    public static final String HAS_CREATE_ACCOUNT_WITHIIN_24HOUR = "HAS_CREATE_ACCOUNT_WITHIIN_24HOUR_";

    /**
     * 理财计划表现缓存时间
     */
    public static final String FINANCEPLAN_RESULTS_CACHE_TIME = "FINANCEPLAN_RESULTS_CACHE_TIME";

    /**
     * 理财计划在Redis中的缓存时间
     */
    public static final String FINANCEPLAN_REDIS_CACHE_TIME = "FINANCEPLAN_REDIS_CACHE_TIME";

    /**
     * 标的在Redis中的缓存时间
     */
    public static final String LOAN_REDIS_CACHE_TIME = "LOAN_REDIS_CACHE_TIME";

    /**
     * ucode最大额度在Redis中的缓存时间
     */
    public static final String UCODE_MAX_VALUE_REDIS_CACHE_TIME = "UCODE_MAX_VALUE_REDIS_CACHE_TIME";

    /**
     * ucode并发锁在Redis中的缓存时间
     */
    public static final String UCODE_SYNC_REDIS_CACHE_TIME = "UCODE_SYNC_REDIS_CACHE_TIME";

    /**
     * 散标列表和债权转让列表在Redis中的缓存时间(单位：秒)
     */
    public static final String LOAN_LIST_REDIS_CACHE_TIME = "LOAN_LIST_REDIS_CACHE_TIME";

    /**
     * 自动检查发送邮件列表
     */
    public static final String AUTOCHECKSERVICE_SENDEMAIL_LIST = "AUTOCHECKSERVICE_SENDEMAIL_LIST";

    /**
     * 封禁黑名单的redis缓存
     */
    public static final String BLACK_LIST_ANTIPLUGIN = "BLACK_LIST_ANTIPLUGIN";
    /**
     * 离线平台地址
     */
    public static final String OFFLINE_PLATFORM_URL = "OFFLINE_PLATFORM_URL";

    /**
     * 数据平台接口地址
     */
    public static final String DATA_HUB_BASIC_URL = "DATA_HUB_BASIC_URL";

    /** 系统常量 **/
    /**
     * 4个 landing page url
     **/
    public static final String HEZUO_URL = "/event/hezuo2/index.jsp";

    public static final String REG_PAGE_LANDING_URL = "/regPageLanding1.action";

    public static final String REG_PAGE_LANDING_URL2 = "/regPageLanding2.action";

    public static final String REG_PAGE_LANDING_URL3 = "/regPageLanding3.action";

    public static final String REG_PAGE_LANDING_URL4 = "/regPageLanding4.action";

    public static final String REG_PAGE_LANDING_URL5 = "/regPageLanding5.action";

    public static final String REG_PAGE_LANDING_URL6 = "/regPageLanding6.action";

    public static final String REG_PAGE_LANDING_URL7 = "/regPageLanding7.action";

    public static final String REG_PAGE_LANDING_URL8 = "/regPageLanding8.action";

    public static final String REG_PAGE_LANDING_URLU = "/regPageLandingU.action";

    /**
     * 通用外部合作商注册接口中的图形验证码记录
     */
    public static final String REGISTER_INTERFACE_CAPTCHA = "REGISTER_INTERFACE_CAPTCHA_";

    /**
     * 自动任务默认延迟执行时间变量
     **/
    public static final String AUTO_TASK_DELAY_DATE_KEY = "delayDate";

    /**
     * 自动任务定时执行任务变量
     **/
    public static final String AUTO_TASK_TIMING_DATE_KEY = "timingDate";

    /**
     * 自动任务默认延迟执行时间
     **/
    public static final Long AUTO_TASK_DEFAULT_DELAY_DATE_VALUE = 0l;

    /**
     * 自动任务默认间隔执行时间变量
     **/
    public static final String AUTO_TASK_INTERVAL_DATE_KEY = "intervalDate";

    /**
     * 自动任务默认间隔执行时间
     **/
    public static final Long AUTO_TASK_DEFAULT_INTERVAL_DATE_VALUE = 2 * 1000L;// 2X1000毫秒

    /**
     * 自动任务执行状态
     **/
    public static final int TIMER_TASK_DEFAULT_STATE = 0;// 0为未执行

    /**
     * Redis中多个值的分隔符
     */
    public static final String REDIS_VALUE_SEPARATOR = "_";

    /** Redis中的Key */
    /**
     * 理财计划锁
     */
    public static final String REDIS_KEY_LOCK_FINANCEPLAN = "financeplan:lock_FinancePlan";

    /**
     * 理财计划
     */
    public static final String REDIS_KEY_FINANCEPLAN = "financeplan:FinancePlan";

    /**
     * 定投计划锁
     */
    public static final String REDIS_KEY_LOCK_AUTOINVESTPLAN = "financeplan:lock_AutoInvestPlan";

    /**
     * 定投计划加入人数
     */
    public static final String REDIS_KEY_AUTOINVESTPLAN_SUB_POINT_COUNT = "financeplan:AutoInvestPlanSubPointCount";

    /**
     * 理财计划内ucode最大额度锁
     */
    public static final String REDIS_KEY_UCODE_MAX_VALUE_IN_FINANCEPLAN = "financeplan:UcodeMaxValueInFinancePlan";

    /**
     * 理财计划内ucode最大额度锁
     */
    public static final String REDIS_KEY_LOCK_UCODE_MAX_VALUE_IN_FINANCEPLAN = "financeplan:lock_UcodeMaxValueInFinancePlan";

    /**
     * ucode并发锁
     */
    public static final String REDIS_KEY_UCODE_SYNC = "financeplan:UcodeSync";

    /**
     * 投标
     */
    public static final String REDIS_KEY_BID_LOAN = "loan:BidLoan";

    /**
     * 投标防超发锁
     */
    public static final String REDIS_KEY_BIDAMOUNT = "loan:BidAmount";
    /**
     * 买债权防超发锁
     */
    public static final String REDIS_KEY_TRANSFER_LEFT_AMOUNT = "transfer:leftAmount";
    /**
     * 买债转占用锁-当一个债转正在被购买时，其他债转暂时不允许购买（上锁状态下每日/还款更新债权价值时需要等待锁释放后更新）
     */
    public static final String REDIS_KEY_TRANSFER_ONLY_KEY = "transfer:onlykey:transferId_";
    /**
     * 当每日/还款更新债权价值执行时，发现transferId的购买债转占用锁有值会跳过债权价值更新处理，接着会在redis中做一个标记，当购买债转占用锁释放锁时根据标记对相应transferId做债权价值更新补充操作
     */
    public static final String REDIS_KEY_TRANSFER_NEED_UPDATE_KEY = "transfer:needupdatekey:transferId_";
    /**
     * 买债转占用锁常量值-TRUE代表已上锁
     */
    public static final String TRANS_ONLY_KEY_TRUE = "TRUE";
    /**
     * 债转和还款互斥锁
     */
    public static final String REDIS_KEY_TRANSANDREPAY = "loan:TransAndRepay";
    /**
     *生成放款任务锁，防止重复生成
     */
    public static final String REDIS_KEY_ApproveLoan = "loan:Approve";
    /**
     * 债转和还款互斥锁常量
     * 债转中
     */
    public static final String TRANSANDREPAY_TRANS = "TRANS";
    /**
     * 债转和还款互斥锁常量
     * 还款中
     */
    public static final String TRANSANDREPAY_REPAY = "REPAY";
    /**
     * 计划退出-已挂出到前端页债权金额
     */
    public static final String REDIS_KEY_PLAN_TO_WEB_AMOUNT = "transfer:planToWebAmount";

    /**
     * Redis中没有投标金额，需要修复的标ID
     */
    public static final String REDIS_KEY_REPAIR_BIDAMOUNT = "REPAIR_BIDAMOUNT";

    /**
     * 理财计划表现缓存
     */
    public static final String REDIS_KEY_PLANRESULTSCACHE = "PlanResultsCache";

    /**
     * 理财计划统计缓存
     */
    public static final String REDIS_KEY_FINANCEPLANSTATISTICS = "FinancePlanStatistics";

    /**
     * 定投计划表现缓存
     */
    public static final String REDIS_KEY_AUTOINVESTPLAN_RESULTSCACHE = "AutoInvestPlanResultsCache";

    /**
     * 定投计划统计缓存
     */
    public static final String REDIS_KEY_AUTOINVESTPLAN_STATISTICS = "AutoInvestPlanStatistics";

    /**
     * 债权转让购买和还款的同步锁
     */
    public static final String REDIS_KEY_TRANSACTIONLOCK = "loantransfer:TransactionLock";

    /**
     * 检查是否满标锁
     */
    public static final String REDIS_KEY_CHECKFULLLOANLOCK = "loan:CheckFullLoanLock";

    /**
     * 理财计划子账户的本金补偿
     */
    public static final String REDIS_KEY_SUB_POINT_PRINCIPAL_DIFF = "SubPointPrincipalDiff";

    /**
     * 关闭读写分离标记
     */
    public static final String RW_SPLITTING_CLOSED = "RWSplittingClosed";

    /**
     * 维护状态标识
     */
    public static final String MAINTENACE_STATES = "MaintenaceStates";

    /**
     * 银行logo 图片上传路径
     */
    public static final String BANK_LOGO_UPLOAD_PATH = "/upload/paychannel/image/";

    /**
     * 自动任务配置文件名称
     */
    public static final String TASK_CONFIG_FILE_NAME = "schedule";

    /**
     * 自动任务是否为主机key（配置文件）
     */
    public static final String TASK_IS_MASTER_KEY = "CurrentHost";

    /**
     * 放在slave执行的自动任务（配置文件）
     */
    public static final String SLAVE_JOBS = "SlaveJobs";

    /**
     * 多线程还款任务
     */
    public static final String BORROW_REPAY_TASK_LAUNCH_JOB = "borrowRepayTaskLaunchJob";

    /**
     * master标识
     */
    public static final String SCHEDULE_JOB_MASTER = "master";

    /**
     * slave标识
     */
    public static final String SCHEDULE_JOB_SLAVE = "slave";

    /**
     * 凌晨清算自动扫描isChicking
     */
    public static final String REDIS_KEY_IS_CHECKING = "isChecking";

    /**
     * 凌晨清算自动扫描isChicking和多线程还款的任务调度切换
     */
    public static final String REDIS_KEY_IS_CHECKING_AND_BORROW_REPAY_TASK_SWITCH = "isCheckingOrBorrowRepayTask";

    /**
     * 凌晨清算自动扫描isChicking和数据备份任务调度切换
     * redis值为0时数据组将可以操作数据
     * 操作完毕后数据组将值设为1,然后清算继续跑并将这个值删除
     */
    public static final String REDIS_KEY_IS_CHECKING_AND_DB_BACKUP_SWITCH = "isCheckingOrDBBackUpTask";

    /**
     * 自动任务是否正在运行（守护任务是否在“运行”标识）
     */
    public static final String KEYPREFIX_STOP_FLAG_IN_REDIS_ = "isStop:";

    /**
     * 自动投标人员队列
     */
    public static final String USER_AUTO_BID_CONDITION = "autoBid:userAutoBidCondition";

    /**
     * 自动投标自动投标的标列表
     */
    public static final String USER_PROGRESS_AUTO_BID_LOANS = "autoBid:progressAutoBidLoans";

    /**
     * 自动投标放标后，等待自动投标的标
     */
    public static final String WAIT_FOR_AUTO_BID_LOANS = "autoBid:waitForAutoBidLoans";

    /**
     * 理财计划预售标队列
     */
    public static final String PRE_SALES_FOR_FINANCE_LOANS = "autoBid:preSalesForFinanceLoans";

    /**
     * 理财计划债券转让队列
     */
    public static final String LOAN_TRANSFER_FOR_FINANCE = "autoBid:loanTransferForFinance";

    /**
     * 理财计划投标 长期
     */
    public static final String UPLAN_QUEUE_L = "autoBid:uplanQueue_L";

    /**
     * 理财计划投标 中期
     */
    public static final String UPLAN_QUEUE_M = "autoBid:uplanQueue_M";

    /**
     * 理财计划投标 短期
     */
    public static final String UPLAN_QUEUE_S = "autoBid:uplanQueue_S";

    /**
     * 理财计划投标 rate值全部排序
     */
    public static final String UPLAN_QUEUE_LOOP = "autoBid:uplanQueue_LOOP";

    /**
     * 计划子账户已经投标的情况,防止重复投标
     */
    public static final String BEEN_BID_BY_SUBPOINT = "autoBid:beenBidBySubPoint_";

    /**
     * 散标列表在Redis中的缓存前缀
     */
    public static final String LOAN_LIST_REDIS_CACHE = "loanList_";

    /**
     * 债权转让列表在Redis中的缓存前缀
     */
    public static final String TRANSFER_LIST_REDIS_CACHE = "transferList_";

    /**
     * 理财计划预定，自动支付扫描
     */
    public static final String REDIS_KEY_IS_FINANCE_PLAN_RESERVE_AUTO_PAYMENT = "isFinancePlanReserveAutoPayment";

    /**
     * 定投计划自动充值扫描
     */
    public static final String REDIS_KEY_IS_AUTO_INVEST_PLAN_RECHARGE_REMIND = "isAutoInvestPlanRechargeRemind";

    /**
     * 自动任务是否正在运行（配置文件）
     */
    public static final String RUNNING_KEYPREFIX_IN_REDIS = "isTimerRunning:";

    /**
     * 红小宝发生的累计收益 accumulatedIncome
     */
    public static final String REDIS_KEY_ACCUMULATED_INCOME = "ACCUMULATED_INCOME";

    /**
     * 红小宝发生交易人次 accumulatedTimes
     */
    public static final String REDIS_KEY_ACCUMULATED_TIMES = "ACCUMULATED_TIMES";

    /** Redis中散标字段KEY start */
    /**
     * 散标累计成交总金额
     */
    public static final String REDIS_KEY_LOAN_TRANSACTION_AMOUNT = "LOAN_TRANSACTION_AMOUNT";

    /**
     * 散标累计成交总笔数
     */
    public static final String REDIS_KEY_LOAN_TRANSACTION_COUNT = "LOAN_TRANSACTION_COUNT";

    /** Redis中散标字段KEY end */

    /** Redis中债券转让字段KEY start */
    /**
     * 债券转让累计转出总金额
     */
    public static final String REDIS_KEY_TRANSFER_TRANSACTION_AMOUNT = "TRANSFER_TRANSACTION_AMOUNT";

    /**
     * 债券转让累计成交总笔数
     */
    public static final String REDIS_KEY_TRANSFER_TRANSACTION_COUNT = "TRANSFER_TRANSACTION_COUNT";

    /**
     * 债券转让平均耗时（用转让结束时间-发布时间得出单笔耗时，之后做平均）单位：X分X秒
     */
    public static final String REDIS_KEY_TRANSFER_TIME = "TRANSFER_TIME";

    /**
     * 签名密钥
     */
    public static final String SIGN_KEY = "SIGN_KEY";

    /**
     * Redis中债券转让字段KEY end
     */

    // 回账查询
    // 系统上线时间
    public static final String ONLINE_TIME = "2010-01-01";

    // 回账查询 查询类别 普通查询
    public static final String ACCOUNT_TYPE_NORMAL = "normal";

    // 回账查询 查询类别 高级查询
    public static final String ACCOUNT_TYPE_SENIOR = "senior";

    // 统计注册流程每日发送短信验证码条数
    public static final String REDIS_REGIST_PER_DAY_COUNT_ = "REGIST:PER_DAY_COUNT_";

    // 统计注册流程每日发送短信验证码条数(第三方接口,不分具体手机号,一旦启用图验,所有手机号都启用)
    public static final String REDIS_REGIST_PER_DAY_COUNT_REGISTER_INTERFACE = "REGIST:PER_DAY_COUNT_REGISTER_INTERFACE";

    /** 手机短信及语音 次数统计 start */
    /**
     * 注册手机号前缀 redis中key为REDIS_REGIST_MOBILE_NO_PREFIX_ + 注册电话 value为当天的发送短信次数
     */
    public static final String REDIS_REGISTER_SMS_NUM = "REGIST";
    public static final String REDIS_RESET_PWD_SMS_NUM = "RESETPWD";
    public static final String REDIS_UPDATE_MOBILE_SMS_NUM = "UPDATEMOBILE";
    public static final String REDIS_RESET_CASH_SMS_NUM = "RESETCASHPWD";
    public static final String REDIS_IP_NUM = "SMS_IP:";
    public static final String SMS_NUM = "SMS:";
    public static final String SMS_WRONG = "SMS_WRONG:";
    public static final String SMS_PER_MIN = "SMS_PER_MIN:";
    /**
     * 发送短信验证码 次数
     */
    public static final int NUM_EXPIRATION_TIMES = 24 * 60 * 60;
    public static final int AUTH_EXPIRATION_TIMES = 24 * 60 * 60;
    public static final int NUM_IP_TIMES = 10 * 60;

    /**
     * 其他操作用户手机号前缀 redis中key为REDIS_OTHER_USER_PREFIX_ + userId value 为当天的发送短信次数
     */
    public static final String REDIS_OTHER_SMS_TIME = "OTHER";

    /**
     * 身份认证 第三方token 有效期一天
     */
    public static final String AUTH_NUM = "AUTH:";
    public static final String AUTH_TOKEN = "AUTH_TOKEN";

    public static final String BANK_NAME = "BANK_NAME";
    public static final String RISK_EVALUATION = "riskEvaluationScore";
    public static final String RISK_EVALUATION_TIME = "riskEvaluationTime";
    public static final String RISK_EXPIRE_TIME = "riskEvaluationExpireTime";

    public static final String RISK_ESCROW_ACC = "isCreateEscrowAcc";

    /** 手机短信及语音 次数统计 start */
    /**
     * 注册手机号前缀 redis中key为REDIS_REGIST_MOBILE_NO_PREFIX_ + 注册电话 value为当天的发送短信次数
     */
    public static final String REDIS_REGIST_MOBILE_NO_PREFIX_ = "REGIST:MOBILE_NO_PREFIX_";
    public static final String REDIS_RESETPWD_MOBILE_NO_PREFIX_ = "REGIST:RESETPWD_NO_PREFIX_";

    /**
     * 其他操作用户手机号前缀 redis中key为REDIS_OTHER_USER_PREFIX_ + userId value 为当天的发送短信次数
     */
    public static final String REDIS_OTHER_USER_PREFIX_ = "OTHER_USER_PREFIX_";


    /**
     * 手机短信及语音 次数统计 end
     */

    public static final String CACHE_POINTLOG_COUNT = "CACHE_POINTLOG_COUNT";

    public static final String CACHE_POINTLOG_GRTREPAID = "CACHE_POINTLOG_GRTREPAID";

    /**
     * 批量提现 - 最后一次提现申请时间
     */
    public static final String CASHDRAW_LAST_APPLY_TIME = "CASHDRAW_LAST_APPLY_TIME";

    /**
     * 批量提现 - 最后一次状态查询时间
     */
    public static final String CASHDRAW_LAST_STATUSQUERY_TIME = "CASHDRAW_LAST_STATUSQUERY_TIME";

    /**
     * 批量提现 - 最后一次退票查询时间
     */
    public static final String CASHDRAW_LAST_REFUND_TIME = "CASHDRAW_LAST_REFUND_TIME";

    /**
     * 批量提现批次并发锁(富友)
     */
    public static final String REDIS_KEY_CASH_DRAW_BATCH_FUYOU_SYNC = "cashDrawBatchFuyouSync";

    /**
     * 批量提现批次充值并发锁
     */
    public static final String REDIS_KEY_CAHS_DRAW_BATCH_RECHARGE_SYNC = "cashDrawBatchRechargeSyn";

    /**
     * 批量提现 - 每天生成的批次序号
     */
    public static final String CASHDRAW_BATCH_FUYOU = "CASHDRAW_BATCH_FUYOU";

    /**
     * 批量提现锁
     */
    public static final String CASHDRAW_BATCH_LOCK_KEY = "BATCH_";

    // /** 批量提现记录列表并发锁 */
    // public static final String REDIS_KEY_CASH_DRAW_LOG_SYNC
    // ="cashDrawLogSync";
    /**
     * 批量提现批次并发锁在Redis中的缓存时间
     */
    public static final int CASH_DRAW_BATCH_REDIS_CACHE_TIME = 180;

    // /** 批量提现记录列表并发锁在Redis中的缓存时间 */
    // public static final String CASH_DRAW_LOG_REDIS_CACHE_TIME =
    // "CASH_DRAW_LOG_REDIS_CACHE_TIME";
    /*** 防刷限制 start */
    /**
     * 注册手机号前缀 redis中key为REDIS_REGIST_MOBILE_NO_PREFIX_IP_ + IP为当天的发送短信次数
     */
    public static final String REDIS_REGIST_MOBILE_NO_PREFIX_IP_ = "REGIST:MOBILE_NO_PREFIX_IP_";

    /**
     * 交易手机号前缀 redis中key为REDIS_TRADE_MOBILE_NO_PREFIX_ + 业务ID + 用户ID 为当天的发送短信次数
     */
    public static final String REDIS_TRADE_MOBILE_NO_PREFIX_ = "TRADE_MOBILE_NO_PREFIX_";

    /***
     * 防刷散表投资限制
     */
    public static final String REDIS_ANTI_BRUSH_LOANLEND_ = "ANTI_BRUSH_LOANLEND_";

    /***
     * 用户行为监控
     */
    public static final String USER_ACTIVITY_MONITOR = "USER_ACTIVITY_MONITOR_";

    /***
     * 防刷理财计划
     */
    public static final String REDIS_ANTI_BRUSH_FINANCEPLAN_ = "ANTI_BRUSH_FINANCEPLAN_";

    /***
     * 防刷定投计划
     */
    public static final String REDIS_ANTI_BRUSH_AUTOINVESTPLAN_ = "ANTI_BRUSH_AUTOINVESTPLAN_";

    /***
     * 防刷债权转让
     */
    public static final String REDIS_ANTI_BRUSH_TRANSFER_ = "ANTI_BRUSH_TRANSFER_";

    /***
     * 防刷理财计划提前退出
     */
    public static final String REDIS_ANTI_BRUSH_ADVANCE_QUIT_UPLAN_ = "ANTI_BRUSH_ADVANCE_QUIT_UPLAN_";

    /***
     * 防刷理财计划续期
     */
    public static final String REDIS_ANTI_BRUSH_ROLL_OVER_UPLAN_ = "ANTI_BRUSH_ROLL_OVER_UPLAN_";

    /**
     * 防止提现重复提交
     */
    public static final String REDIS_ANTI_BRUSH_CASHDRAW = "ANTI_BRUSH_CASHDRAW_";

    /** 防刷限制 end */

    /**
     * 用户提醒状态
     **/
    public static final String REDIS_KEY_USER_NOTIFICATION_STATUS = "USER_NOTIFICATION_STATUS";

    /**
     * 借款申请信息完成度
     **/
    public static final String REDIS_KEY_PREFIX_USER_LOAN_APPLY_INFO_FINISH_RATIO = "USER_LOAN_APPLY_INFO_FINISH_RATIO_";

    /**
     * 等待审核倒计时前缀 key_loanId
     **/
    public static final String REDIS_PREFIX_WAIT_FOR_AUDIT_COUNTDOWN_ = "PREFIX_WAIT_FOR_AUDIT_COUNTDOWN_";

    /**
     * 账单信息
     */
    public static final String BILLINFO = "BILLINFO_";

    /**
     * 自动发送账单邮件线程数配置
     */
    public static final String AUTOSENDBILLMAILJOB_THREAD_NUM = "AUTOSENDBILLMAILJOB_THREAD_NUM";

    /**
     * 自动发送账单邮件休眠时间配置
     */
    public static final String AUTOSENDBILLMAILJOB_SLEEP_MILLIS = "AUTOSENDBILLMAILJOB_SLEEP_MILLIS";

    /**
     * 默认舍入模式
     */
    public static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    /**
     * 默认精度
     */
    public static final int DEFAULT_PRECISION = 2;

    /**
     * 最大精度
     */
    public static final int MAX_PRECISION = 16;

    /**
     * 收回优惠券时增加的天数
     **/
    public static final int TAKE_BACK_COUPON_ADD_DAYS = 7;

    /**
     * 允许每天绑定的失败次数
     **/
    public static final int ALLOW_BIND_FAIL_TIMES = 10;

    /***
     * session同步状态(mobile)
     */
    public static final String REDIS_SESSION_SYN_STATUS_MOBILE_ = "SESSION_SYN_STATUS_MOBILE_";

    /***
     * session同步状态(home)
     */
    public static final String REDIS_SESSION_SYN_STATUS_HOME_ = "SESSION_SYN_STATUS_HOME_";

    /**
     * 还款提示邮件列表
     **/
    public static final String REPAYNOTICESERVICE_SENDEMAIL_LIST = "REPAYNOTICESERVICE_SENDEMAIL_LIST";

    /**
     * 还款提示手机号列表
     **/
    public static final String REPAYNOTICESERVICE_SENDMOBILE_LIST = "REPAYNOTICESERVICE_SENDMOBILE_LIST";
    /**
     * 凌晨清算检查出错手机号列表
     */
    public static final String AUTOREPAYJOB_CHECK_SENDMOBILE_LIST = "AUTOREPAYJOB_CHECK_SENDMOBILE_LIST";
    /**
     * 凌晨还款检查出错手机号列表
     */
    public static final String AUTOADDREPAYJOB_CHECK_SENDMOBILE_LIST = "AUTOADDREPAYJOB_CHECK_SENDMOBILE_LIST";
    /**
     * 计划自动投标检查出错手机号列表
     */
    public static final String AUTOFINANCEPLAN_CHECK_SENDMOBILE_LIST = "AUTOFINANCEPLAN_CHECK_SENDMOBILE_LIST";
    /**
     * keepalive检查出错手机号列表
     */
    public static final String KEEP_ALIVE_CHECK_SENDMOBILE_LIST = "KEEP_ALIVE_CHECK_SENDMOBILE_LIST";
    /**
     * 批量提现例外用户ID的名单
     **/
    public static final String REDIS_WITHDRAWAL_EXCEPTION_LIST = "WITHDRAWAL_EXCEPTION_LIST";

    // 用户提现黑名单 WEB-253
    public static final String CASHDRAW_USERID_BLACKLIST = "CASHDRAW_USERID_BLACKLIST";

    // 银行卡号黑名单 WEB-253
    public static final String CASHDRAW_BANKCARDNO_BLACKLIST = "CASHDRAW_BANKCARDNO_BLACKLIST";

    // 手机号黑名单 WEB-253
    public static final String CASHDRAW_MOBILE_BLACKLIST = "CASHDRAW_MOBILE_BLACKLIST";

    // 用户名黑名单 WEB-253
    public static final String CASHDRAW_USERNAME_BLACKLIST = "CASHDRAW_USERNAME_BLACKLIST";

    // 身份证号黑名单 WEB-253
    public static final String CASHDRAW_IDNO_BLACKLIST = "CASHDRAW_IDNO_BLACKLIST";
    /**
     * 债权转让列表定时缓存的过期时间
     **/
    public static final int LOAN_TRANSFER_TIMED_REDIS_CACHE_EXPIRE_SECOND = 60;

    /**
     * 存储外挂监测数据日报收件人列表
     **/
    public static final String REDIS_KEY_DATA_MONITORING_REPORT_ADDRESSEE = "DATA_MONITORING_REPORT_ADDRESSEE";

    /**
     * 单笔最大提现金额
     **/
    public static final double CASH_DRAW_MAX_AMOUNT = 5000000;

    /**
     * 注册时手机信息验证今日错误次数
     **/
    public static final String NUMBER_ERRORS_ACCUMULATED_TODAY_ = "NUMBER_ERRORS_ACCUMULATED_TODAY_";

    // ----------- 凌晨清算优化 -----------
    /**
     * 凌晨清算的逾期和坏账状态修改多线程的最大并发数
     */
    public static final String UPDATE_OVERDUE_BADLOAN_THREAD_POOL_SIZE = "UPDATE_OVERDUE_BADLOAN_THREAD_POOL_SIZE";

    /**
     * 凌晨清算的子账户提现操作多线程的最大并发数
     */
    public static final String AUTO_CASHDRAW_FOR_HXB_CASHTYPE_THREAD_POOL_SIZE = "AUTO_CASHDRAW_FOR_HXB_CASHTYPE_THREAD_POOL_SIZE";

    /**
     * 凌晨清算的提现至红小宝账户或银行卡的最大并发数 autoWithdrawForExitingSubPoints
     */
    public static final String AUTO_WITH_DRAW_FOR_EXITING_SUBPOINTS_POOL_SIZE = "AUTO_WITH_DRAW_FOR_EXITING_SUBPOINTS_POOL_SIZE";

    /**
     * 凌晨清算薪计划自动充值多线程的最大并发数
     */
    public static final String AUTO_RECHARGE_FOR_AUTO_INVEST_PLAN_THREAD_POOL_SIZE = "AUTO_RECHARGE_FOR_AUTO_INVEST_PLAN_THREAD_POOL_SIZE";

    /**
     * 凌晨清算-理财计划，补足已收利息最大处理线程数
     */
    public static final String UPLAN_PERIOD_SCAN_THREAD_POOL_SIZE = "UPLAN_PERIOD_SCAN_THREAD_POOL_SIZE";

    /**
     * 凌晨清算-自动检查任务最大并发数
     */
    public static final String AUTO_CHECK_SERIVCE_THREAD_POOL_SIZE = "AUTO_CHECK_SERIVCE_THREAD_POOL_SIZE";

    /**
     * 生成对账的线程数
     */
    public static final String ACCOUNT_RECONCILE_THREAD_POOL_SIZE = "ACCOUNT_RECONCILE_THREAD_POOL_SIZE";

    /**
     * 监控schedule数据库是否能正常查询
     */
    public static final String UPDATE_ACCOUNT_BALANCE_KEEP_ALIVE = "UPDATE_ACCOUNT_BALANCE_KEEP_ALIVE";

    /**
     * 监控core-check数据库是否能正常查询
     */
    public static final String AUTO_FINANCEPLAN_CHECK_KEEP_ALIVE = "AUTO_FINANCEPLAN_CHECK_KEEP_ALIVE";

    /**
     * 生成内部对账的日期
     */
    public static final String CHECK_DATE_INNER = "CHECK_DATE_INNER";

    /**
     * 生成外部对账的日期
     */
    public static final String CHECK_DATE_OUTER = "CHECK_DATE_OUTER";

    /**
     * 该日期的内部对账是否已经执行
     */
    public static final String CHECK_DATE_INNER_DONE = "CHECK_DATE_INNER_DONE";

    /**
     * 该日期的外部对账是否已经执行
     */
    public static final String CHECK_DATE_OUTER_DONE = "CHECK_DATE_OUTER_DONE";

    // 只有借入者的逾期总数和严重逾期总数需要累加到数据库的要持久化到redis中, 其他的每次重启时重新计算,所以不用持久化到redis了
    public static final String redis_userLoanRecordUpdateLogList = "userLoanRecordUpdateLogList";

    // ----------- 多线程还款 -----------
    /**
     * 多线程还款的最大并发数
     */
    public static final String BORROW_REPAY_THREAD_POOL_SIZE = "BORROW_REPAY_THREAD_POOL_SIZE";

    /**
     * 多线程机构账户充值的最大并发数
     */
    public static final String BORROW_RECAHRGE_THREAD_POOL_SIZE = "BORROW_RECAHRGE_THREAD_POOL_SIZE";

    /**
     * 发送短信备用通道
     **/
    public static final String SMS_HY6_USERID_YANZHENG_ZHUWANG = "SMS_HY6_USERID_YANZHENG_ZHUWANG";

    public static final String SMS_HY6_ACCOUNT_YANZHENG_ZHUWANG = "SMS_HY6_ACCOUNT_YANZHENG_ZHUWANG";

    public static final String SMS_HY6_PASSWORD_YANZHENG_ZHUWANG = "SMS_HY6_PASSWORD_YANZHENG_ZHUWANG";

    public static final String SMS_HY6_SERVER_ZHUWANG = "SMS_HY6_SERVER_ZHUWANG";

    public static final String SMS_KEY_EMAY = "SMS_KEY_EMAY";

    public static final String SMS_SERIALNUMBER_EMAY = "SMS_SERIALNUMBER_EMAY";

    /**
     * 发送短信备用通道是否刷新备用配置
     **/
    public static final String SMS_ZHUWANG_REFRESH = "SMS_ZHUWANG_REFRESH";

    public static final String SMS_EMAY_REFRESH = "SMS_EMAY_REFRESH";

    /**
     * 批量还款中标记
     */
    public static final String MANUAL_AUTO_REPAY_LOCK_KEY = "MANUAL_AUTO_REPAY_LOCK_KEY";

    /**
     * 移动端散标列表在Redis中的缓存前缀
     */
    public static final String LOAN_MOBILE_LIST_REDIS_CACHE = "loanMobileList_";

    /**
     * 移动端判断用户是否被禁用状态
     */
    public static final String MOBILE_INVALID_USER_STATUS = "MOBILE_INVALID_USER_STATUS";

    /**
     * mgmt为用户充值时锁定point的key的前缀 暂时不用了
     **/
    public static final String POINT_RECHARGE_KEY_PREFIX = "POINT_RECHARGE_KEY_PREFIX_";

    /**
     * mgmt为用户充值时锁定point的过期时间 暂时不用了
     **/
    public static final int POINT_LOCK_TIMED_REDIS_CACHE_EXPIRE_SECOND = 5;

    /**
     * 主站判断用户是否被禁用状态
     */
    public static final String WEB_INVALID_USER_STATUS = "WEB_INVALID_USER_STATUS";

    /**
     * 贷后FTP服务器配置信息
     */
    public static final String AMQUE_FTP_PATH = "AMQUE_FTP_PATH";

    /**
     * 手动充值的充值凭证保存路径
     */
    public static final String MANUAL_RECHARGE_CERT_PATH = "MANUAL_RECHARGE_CERT_PATH";

    /**
     * 商路通每日全通用户同步保存路径
     */
    public static final String CRM_USER_SYNC = "CRM_USER_SYNC";

    /**
     * 批量还款凭证保存路径
     */
    public static final String BATCH_AUDIT_CERT_PATH = "BATCH_AUDIT_CERT_PATH";

    // public static final String AMQUE_FTP_IP = "AMQUE_FTP_IP";
    // public static final String AMQUE_FTP_PORT = "AMQUE_FTP_PORT";
    // public static final String AMQUE_FTP_USER = "AMQUE_FTP_USER";
    // public static final String AMQUE_FTP_PASSWORD = "AMQUE_FTP_PASSWORD";

    /**
     * 处理贷后任务的标记,防止并发执行
     */
    public static final String LOCK_AMQUE_STATUS = "lock:amque:status";

    /**
     * 正在处理中的贷后任务,key格式为 <br/>
     * <code>lock:amque:processing:{amqueRequestId}</code>
     */
    public static final String LOCK_AMQUE_PROCESSING = "lock:amque:processing:";

    /**
     * 贷后接口的充值费用要扣的手续费(默认0.5%)
     * 放在systemsetting里
     */
    public static final double AMQUE_RECHARGE_FEE_RATE = 0.005;

    /**
     * Redis中年龄小于18岁且充过值的用户ID集合
     */
    public static final String RECHARGE_AND_AGE_LESSTHAN_18 = "rechargeAndAgeLessThan18";

    /**
     * 默认的最小投资份数单位为1份,包括投标和债权转让,必须以min_invest_shares的整数倍作交易
     */
    public static final int MIN_INVEST_SHARES_DEFAULT = 1;

    /**
     * 标的金额区间在3000到500000之间
     */
    public static final int MIN_LOAN_AMOUNT = 3000;

    /**
     * 标的金额区间在3000到500000之间(创富和xproject的标不受最大金额限制)
     */
    public static final int MAX_LOAN_AMOUNT = 500000;

    /**
     * 记录标当时的还款状态或买债权状态
     */
    public static final String LOAN_REPAY_OR_TRANSFER = "loan";

    /**
     * 多线程还款借款人同步锁
     */
    public static final String REPAY_BORROWER_SYNC_LOCK = "REPAY_BORROWER_SYNC_LOCK";

    /**
     * 多线程还款借款人同步锁超时时间 30分钟
     */
    public static final Integer REPAY_BORROWER_SYNC_LOCK_EXPIRE = 30 * 60;

    /**
     * 多线程还款子账户同步锁
     */
    public static final String REPAY_SUBPOINT_SYNC_LOCK = "REPAY_SUBPOINT_SYNC_LOCK";

    /**
     * 前端用户统计信息是否从redis读取 1:从redis读取(先redis失败再db) 其它:读db
     */
    public static final String USER_STAT_READ_CACHE_STATUS = "USER_STAT_READ_CACHE_STATUS";

    /**
     * 将updateDelay信息插入到redis中
     */
    public static final String USER_STAT_WRITE_CACHE_STATUS = "USER_STAT_WRITE_CACHE_STATUS";

    /**
     * Redis中委外的标的的Key, 数据结构为set
     */
    public static final String OUTSOURCE_LOAN = "outsource:loan";

    /**
     * Redis中委外的用户的Key, 数据结构为set
     */
    public static final String OUTSOURCE_USER = "outsource:user";

    /**
     * API判断身份证号白名单
     */
    public static final String API_CARD_WHITE_LIST = "API_CARD_WHITE_LIST";

    /**
     * Schedule应用布署的域名
     */
    public static final String SCHEDULE_URL = "SCHEDULE_URL";

    /**
     * Schedule登录用户名
     */
    public static final String SCHEDULE_USERNAME = "SCHEDULE_USERNAME";

    /**
     * Schedule登录加密过的密码
     */
    public static final String SCHEDULE_PASSWORD = "SCHEDULE_PASSWORD";

    /**
     * Schedule任务
     */
    public static final String SCHEDULE_TASKNAME = "SCHEDULE_TASKNAME";

    /**
     * Redis中清算时等待数据备份的最大时间的key，单位为秒
     */
    public static final String REDIS_KEY_CHECKING_WAIT_TIME_FOR_DB_BACKUP = "checkingWaitTimeforDBBackUp";

    /**
     * 跳过清算时的自动检查, 为"1"时跳过
     */
    public static final String SKIP_AUTOCHECK = "SKIP_AUTOCHECK";

    /**
     * Redis中清算时等待数据备份的默认时间，单位为秒
     */
    public static final long DEFAULT_CHECKING_WAIT_TIME_FOR_DB_BACKUP = 60 * 30;

    /**
     * 发送到2345理财的UCid
     */
    public static final String REDIS_KEY_SEND_TO_2345LICAI_UC_IDS = "SEND_TO_2345LICAI_UC_IDS";

    // ----------- 存管托管 -----------
    /**
     * 强制去存管查询超时的订单最大并发数
     */
    public static final String QUERY_TIMEOUT_ORDER_POOL_SIZE = "QUERY_TIMEOUT_ORDER_POOL_SIZE";

    /**
     * 新手标当天累计放标量
     */
    public static final String NEW_COMER_LOAN_DAY_MAX_SIZE = "NEW_COMER_LOAN_DAY_MAX_SIZE";

    /**
     * 发送警告的邮箱列表，以逗号分隔
     */
    public static final String WARNING_MAIL_LIST = "autoRepayWarningMail";

    /**
     * 防止数据库压力过大的还款等待时间
     */
    public static final String BORROW_REPAY_DB_OVERLOAD_WAIT_TIME = "borrowRepayDBOverloadWaitTime";

    /**
     * 初始化奖品分布使用的Redis锁
     */
    public static final String LOTTERY_REDIS_INIT_LOCK_KEY = "lottery:lock:init";

    /**
     * 初始化奖品分布使用的Redis锁超时时间 3秒
     */
    public static final Integer LOTTERY_REDIS_INIT_LOCK_KEY_EXPIRE = 3;

    /**
     * 抽奖时使用的在Redis锁
     */
    public static final String LOTTERY_REDIS_LOCK_KEY = "lottery:lock:draw";

    /**
     * 抽奖时使用的在Redis锁超时时间 3秒
     */
    public static final Integer LOTTERY_REDIS_LOCK_KEY_EXPIRE = 3;

    /**
     * 用户提交Redis锁
     */
    public static final String REDIS_SUBMIT_LOAN = "loan:submit:";

    /**
     * 用户提交Redis锁超时时间 5秒
     */
    public static final Integer REDIS_SUBMIT_LOAN_EXPIRE = 5;

    /**
     * 记录标当时的还款状态或买债权状态Redis锁超时时间 10分钟
     */
    public static final Integer LOAN_REPAY_OR_TRANSFER_EXPIRE = 600;

    /**
     * 新手标缓存前缀
     */
    public static final String NEW_COMER_LOAN_CACHE = "new_comer_loan_cache";

    /**
     * 新手标缓存前缀超时时间5分钟
     */
    public static final Integer NEW_COMER_LOAN_CACHE_EXPIRE = 60 * 5;

    /**
     * 系统参数缓存
     */
    public static final String SYSTEM_SETTING_CACHE = "system_setting_cache";

    /**
     * 系统参数缓存超时时间10分钟
     */
    public static final Integer SYSTEM_SETTING_CACHE_EXPIRE = 60 * 10;

    /**
     * 理财计划详情页提示小黄条
     */
    public static final String TIPS_U_DETAIL_WEB = "TIPS_U_DETAIL_WEB";

    /**
     * 理财计划购买成功页提示小黄条
     */
    public static final String TIPS_U_PURCHASED_WEB = "TIPS_U_PURCHASED_WEB";

    /**
     * 账户首页提示小黄条
     */
    public static final String TIPS_INDEX_WEB = "TIPS_INDEX_WEB";

    /**
     * 记录标当时的买债权状态Redis锁超时时间 15分钟
     */
    public static final Integer LOAN_TRANSFER_EXPIRE = 900;

    /**
     * 记录标当时的还款状态状态Redis锁超时时间 15分钟
     */
    public static final Integer LOAN_REPAY_EXPIRE = 900;

    /**
     * 清算时还款等待的最后时间 格式 3:15 表示3点15分
     **/
    public static final String IS_CHECKING_REPAY_STOP_TIME = "IS_CHECKING_REPAY_STOP_TIME";

    /**
     * ACCOUNT_BALANCE延迟更新多线程的最大并发数
     */
    public static final String UPDATE_ACCOUNT_BALANCE_THREAD_POOL_SIZE = "UPDATE_ACCOUNT_BALANCE_THREAD_POOL_SIZE";

    /**
     * 理财计划到期发放红包多线程的最大并发数
     */
    public static final String UPLAN_EXPIRE_SEND_INCR_COUPON = "UPLAN_EXPIRE_SEND_INCR_COUPON";
    /**
     * hh:mm后暂停理财计划自动买债权, 默认23:30
     */
    public static final String STOP_BUY_LOANTRANSFER_BEFORE_AUTOREPAYJOB = "autoBid:STOP_BUY_LOANTRANSFER_BEFORE_AUTOREPAYJOB";

    /**
     * 薪计划充值锁
     * AUTO_INVEST_PLAN_RECHARGE_userId_autoInvestPlanRecordId
     */
    public static final String AUTO_INVEST_PLAN_RECHARGE = "AUTO_INVEST_PLAN_RECHARGE_";

    /**
     * 临时hack一个白名单,这个名单里的跳过逾期坏账检查, 标id按,分隔
     */
    public static final String OVERDUE_AND_BADLOAN_WHITELIST = "OVERDUE_AND_BADLOAN_WHITELIST";

    /**
     * 凌晨清算更新债权价值和状态操作多线程的最大并发数
     */
    public static final String UPDATE_LOANTRANSFER_PRICE_THREAD_POOL_SIZE = "UPDATE_LOANTRANSFER_PRICE_THREAD_POOL_SIZE";

    /**
     * 跳过自动任务买债权,默认为null或false,测试环境下改为true
     */
    public static final String SKIP_AUTO_BUY_PRESALE_LOANTRANSFER = "autoBid:SKIP_AUTO_BUY_PRESALE_LOANTRANSFER";

    /**
     * 用户债权转让申请时需要串行完成
     */
    public static final String LOANTRANSFER_FOR_APPLY_USERID_ = "LOANTRANSFER_FOR_APPLY_USERID_";

    /**
     * 平台债权转让申请时需要串行完成
     */
    public static final String LOANTRANSFER_FOR_APPLY_PLATFORM_ = "LOANTRANSFER_FOR_APPLY_PLATFORM_";

    /**
     * 放款推送信息报错，处理开标时间rediskey
     */
    public static final String HANDLE_OPEN_TIME_DATA = "HANDLE_OPEN_TIME_DATA";

    /**
     * 自定义标的还款日期,修复数据用
     * LOAN_BRR_CUSTOM_REPAY_DATE_brrId
     */
    public static final String LOAN_BRR_CUSTOM_REPAY_DATE = "LOAN_BRR_CUSTOM_REPAY_DATE_";

    /**
     * 临时代码，计划投资策略完善后应去掉URGENT
     */
    public static final String PLAN_INVEST_MODE = "autoBid:PLAN_INVEST_MODE";

    /**
     * 临时代码，计划投资策略完善后应去掉
     */
    public static final String PLAN_INVEST_CONCURRENCY = "autoBid:PLAN_INVEST_CONCURRENCY";

    /**
     * 临时代码，计划投资策略完善后应去掉
     */
    public static final String PLAN_INVEST_LT_CELL_PREFIX = "autoBid:PLAN_INVEST_LT_CELL_";

    /**
     * 临时代码，计划投资策略完善后应去掉
     */
    public static final String PLAN_INVEST_BUYER_RQ = "autoBid:PLAN_INVEST_BUYER_RQ";

    /**
     * 临时代码，计划投资策略完善后应去掉
     * 前台挂出债权优先 true:优先;false or null:不优先
     */
    public static final String PLAN_INVEST_TRANSFERING_FIRST = "autoBid:PLAN_INVEST_TRANSFERING_FIRST";

    /**
     * 自动预售线程数
     */
    public static final String AUTO_PRESALE_MAX_THREAD_COUNT = "autoBid:AUTO_PRESALE_MAX_THREAD_COUNT";

    /**
     * 短信验证码通道
     * 建周
     */
    public static final String SMS_CAPTCHA_SERVICE = "SMS_CAPTCHA_SERVICE";

    /**
     * 用户行为锁
     */
    public static final String REDIS_USER_OPERATION_LOCK = "USER_OPERATION_LOCK_";

    /**
     * 用户行为锁超时时间1分钟
     */
    public static final int REDIS_USER_OPERATION_LOCK_EXPIRE = 60;
    /**
     * 债权黑名单,这个里面的不在前台显示
     */
    public static final String LOAN_TRANSFER_DISPLAY_BLACKLIST = "LOAN_TRANSFER_DISPLAY_BLACKLIST";
    /**
     * 子账户投标买债权黑名单,这个里面的暂时不投标买债权
     */
    public static final String SUBPOINT_AUTO_BID_BLACKLIST = "SUBPOINT_AUTO_BID_BLACKLIST";
    /**
     * 放款提现的线程池大小
     */
    public static final String START_FAIL_CASH_LOAN_THREAD_POOL_SIZE = "START_FAIL_CASH_LOAN_THREAD_POOL_SIZE";
    /**
     * 批量充值还款中充值的线程池大小
     */
    public static final String BORROW_REPAY_RECHARGE_POOL_SIZE = "BORROW_REPAY_RECHARGE_POOL_SIZE";
    /**
     * 批量充值还款中加入还款任务的线程池大小
     */
    public static final String BORROW_REPAY_ADD_REPAY_TASK_POOL_SIZE = "BORROW_REPAY_ADD_REPAY_TASK_POOL_SIZE";

    /**
     * 理财计划退出自动卖债权的最大并发数
     */
    public static final String AUTO_SELL_LOAN_THREAD_POOL_SIZE = "AUTO_SELL_LOAN_THREAD_POOL_SIZE";
    /**
     * 订单重试的线程池大小
     */
    public static final String ESCROW_RETRY_THREAD_POOL_SIZE = "ESCROW_RETRY_THREAD_POOL_SIZE";

    /**
     * 至信标的当前合同编号
     */
    public static final String CONTRACT_ID_ZHIXIN = "CONTRACT_ID_ZHIXIN";

    /**
     * 理财计划的合同编号
     */
    public static final String CONTRACT_ID_FINANCE_PLAN = "CONTRACT_ID_FINANCE_PLAN";

    /**
     * 获取银行卡编码
     */
    public static final String BANK_KEYPREFIX = "bank:";

    /**
     * 是否采用理财计划投标规则调整
     */
    public static final String UPLAN_ORDER_NEW_RULE_TAG = "UPLAN_ORDER_NEW_RULE_TAG";

    /**
     * 理财计划到期自动退出扫描任务状态
     */
    public static final String AUTO_UPLAN_QUIT_JOB_STATE = "AUTO_UPLAN_QUIT_JOB_STATE";

    /**
     * UC投标队列中老优选是否优先投标
     */
    public static final String UC_QUEUE_OLD_FIRST = "UC_QUEUE_OLD_FIRST";
    /**
     * 合作伙伴
     */
    public static final String PARTNER = "ZHIXIN";
    /**
     * 输入字符集
     */
    public static final String INPUT_CHARSET = "UTF-8";

    /**
     * redis中保存的测试参数
     * 托管接口抛出测试异常的可能性, 例如20代表20%可能性抛出, 正式环境默认无此条记录
     */
    public static final String ESCROW_TEST_ERROR_PERCENT = "ESCROW_TEST_ERROR_PERCENT";

    /**
     * 前台传过来的登录平台代码
     */
    /**
     * PC
     */
    public static final String DEVICE_PC = "PC";

    /**
     * Android
     */
    public static final String DEVICE_ANDROID = "Android";

    /**
     * iOS
     */
    public static final String DEVICE_IOS = "iOS";

    public static final String REDIS_SESSION_DEVICE = "device";

    public static final String REDIS_SESSION_SESSIONID = "sessionId";

    /**
     * 兑换优惠券前缀
     */

    public static final String  EXCHANGE_COUPON_TRY_TIMES="bindCouponTryTimes:";
    /**
     * 每天兑换优惠券次数
     */
    public static final int ALLOW_EXCHANGE_TIMES = 10;
    /**
     * 我的福利红点redis标记
     */
    public static final String MY_WELFARE_RED_POINT_PREFIX="myWelfareredPoint"  ;

    public static final String BATCH_AUTO_RELEASE_COUNT="BATCH_AUTO_RELEASE_COUNT_";
    /**
     * 检验异步发放优惠券是否成功
     */
    public static final String ASYNC_COUPON_RELEASE_IS_COMPLETE="couponSendProgress:";
    public static final String ASYNC_COUPON_RELEASE_RUNNING="RUNNING";
    public static final String ASYNC_COUPON_RELEASE_SUCCESS="SUCCESS";
    public static final String ASYNC_COUPON_RELEASE_ERROR="ERROR";
    public static final String ASYNC_COUPON_RELEASE_UNSEND="UNSEND";

    public static final String CASHDRAW_PASSWORD_COUNT="CASHDRAW_PASSWORD_COUNT";

    public static final String REGISTER_INVITER="REGISTER_INVITER";

    public static  final String REGISTER_SELF_INVITER="REGISTER_SELF_INVITER";

    /**
     * 获取角色用户的有效时间（存放在redis中）
     */
    public static final String ROLE_VALID_TIME = "ROLE_VALID_TIME";


    //    Conservative, steady and positive
    //保守型上限分数
    public static final int CONSERVATIVE = 14;
    public static final Set<String> CONSERVATIVE_SET = new HashSet() {{
        add("AA");
        add("A");
    }};
    //稳健型上限分数
    public static final int STEADY = 36;
    public static final Set<String> STEADY_SET = new HashSet() {{
        add("AA");
        add("A");
        add("B");
    }};
    //积极型下限分数
    public static final int POSITIVE = 37;
    public static final Set<String> POSITIVE_SET = new HashSet() {{
        add("AA");
        add("A");
        add("B");
        add("C");
        add("D");
    }};

    /**
     * 用户是否已经绑卡标志
     */
    public static final String USER_HAS_BIND_CARD = "hasBindCard";
    /**
     * 用户解绑次数前缀
     */
    public static final String DAY_UNBIND_TIME = "DAY_UNBIND_TIME:";
    /**
     * 用户交易密码输入错误次数前缀
     */
    public static final String DAY_FAIL_CASHPASSWORD_TIME = "DAY_FAIL_CASHPASSWORD_TIME:";
    /**
     * 用户交易密码输入错误次数限制
     */
    public static final int DAY_CASH_PASSWORD_ERROR_TIME_LIMIT = 5;
    /**
     * 用户成功绑卡次数限制
     */
    public static final int DAY_UNBIND_TIME_LIMIT = 3;
}
