package wangzhongqiu.spring.core.exception.base;

import java.util.HashMap;

public class ExceptionRegister {

    static private HashMap<Class<? extends BaseException>, Integer> codeMap;

    static {
        codeMap = new HashMap<Class<? extends BaseException>, Integer>();

        // 1xxx: 业务逻辑异常
        codeMap.put(BusinessBaseException.class, 1000);
        // 实体不存在
        codeMap.put(EntityNotExistsException.class, 1001);
        // 处于锁定状态
        codeMap.put(LockedException.class, 1008);
        // 非法份额
        codeMap.put(InvalidShareException.class, 1009);
        // TODO 债权价值发生变化，需要调整命名
        codeMap.put(DataAlreadyExpireException.class, 1010);
        // TODO 现在这个Exception用于90天内不能转让债权的限制，后续需要调整命名
        codeMap.put(PermissionException.class, 1011);
        // 非法分页参数
        codeMap.put(InvalidPagingException.class, 1013);
        // 非法分页参数
        codeMap.put(InvalidTimeRangeException.class, 1014);

        // 11xx: 资金账户异常
        // 资金账户已存在
        codeMap.put(PointExistsException.class, 1101);

        // 12xx: 标的异常
        // 存在标的未批准
        codeMap.put(HasLoanNotApprovedException.class, 1201);
        // 存在标的逾期
        codeMap.put(HasLoanOverdueException.class, 1202);
        // 非法债务人
        codeMap.put(InvalidBorrowerException.class, 1203);
        // 非法债权人
        codeMap.put(InvalidLenderException.class, 1204);
        // 非法所有者
        codeMap.put(InvalidOwnerException.class, 1205);
        // 非法购买者
        codeMap.put(InvalidBuyerException.class, 1205);

        // 2xxx: 系统异常
        codeMap.put(SystemBaseException.class, 2000);
        // 公用内存、缓存问题
        codeMap.put(RedisException.class, 2002);
        // 服务不存在
        codeMap.put(ServiceNotExistsException.class, 2003);
        // hibernate事务之后执行注册的其他追加事务发生错误
        codeMap.put(AfterTransactionException.class, 2004);

        // 3xxx: 非运行时异常包装
        codeMap.put(CheckedException.class, 3000);
    }

    public static int getCode(Class<? extends BaseException> exceptionClass) {
        return codeMap.get(exceptionClass);
    }

}
