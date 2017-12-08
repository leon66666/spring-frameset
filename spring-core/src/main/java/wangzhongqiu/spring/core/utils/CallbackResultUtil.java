package wangzhongqiu.spring.core.utils;

import wangzhongqiu.spring.core.exception.CommonRuntimeException;
import wangzhongqiu.spring.core.exception.CommonRuntimeExceptionFactory;

public class CallbackResultUtil {
    public static CallbackResult wrapFailure(Enum<? extends EnumResult> statusEnum, Object args) {
        CommonRuntimeException commonRuntimeException = CommonRuntimeExceptionFactory.getException(statusEnum, new Object[]{args});
        return CallbackResult.failure(commonRuntimeException.getErrorCode(), commonRuntimeException);
    }

    public static CallbackResult wrapFailure(Enum<? extends EnumResult> statusEnum) {
        CommonRuntimeException commonRuntimeException = CommonRuntimeExceptionFactory.getException(statusEnum, new Object[0]);
        return CallbackResult.failure(commonRuntimeException.getErrorCode(), commonRuntimeException);
    }

    public static CallbackResult wrapFailure(Enum<? extends EnumResult> statusEnum, Object[] args) {
        CommonRuntimeException commonRuntimeException = CommonRuntimeExceptionFactory.getException(statusEnum, args);
        return CallbackResult.failure(commonRuntimeException.getErrorCode(), commonRuntimeException);
    }

    public static CallbackResult wrapFailure(Object[] params, Enum<? extends EnumResult> statusEnum, Object[] args) {
        CommonRuntimeException commonRuntimeException = CommonRuntimeExceptionFactory.getException(params, statusEnum, args);
        return CallbackResult.failure(commonRuntimeException.getErrorCode(), commonRuntimeException);
    }

    public static CallbackResult wrapFailure(Object businessObject, Enum<? extends EnumResult> statusEnum) {
        CommonRuntimeException commonRuntimeException = CommonRuntimeExceptionFactory.getException(statusEnum, new Object[0]);
        return CallbackResult.failure(commonRuntimeException.getErrorCode(), commonRuntimeException, businessObject);
    }

    public static CallbackResult wrapFailure(Object businessObject, Enum<? extends EnumResult> statusEnum, Object[] args) {
        CommonRuntimeException commonRuntimeException = CommonRuntimeExceptionFactory.getException(statusEnum, args);
        return CallbackResult.failure(commonRuntimeException.getErrorCode(), commonRuntimeException, businessObject);
    }

    public static CallbackResult wrapFailure(Object businessObject, Object[] params, Enum<? extends EnumResult> statusEnum, Object[] args) {
        CommonRuntimeException commonRuntimeException = CommonRuntimeExceptionFactory.getException(params, statusEnum, args);
        return CallbackResult.failure(commonRuntimeException.getErrorCode(), commonRuntimeException, businessObject);
    }

    public static CallbackResult wrapFailure(CommonRuntimeException commonRuntimeException) {
        return CallbackResult.failure(commonRuntimeException.getErrorCode(), commonRuntimeException);
    }
}