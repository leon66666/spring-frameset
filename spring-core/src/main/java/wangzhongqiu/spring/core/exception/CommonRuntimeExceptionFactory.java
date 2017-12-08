package wangzhongqiu.spring.core.exception;

import wangzhongqiu.spring.core.utils.EnumResult;

public class CommonRuntimeExceptionFactory {
    public static CommonRuntimeException getException(int code, String message) {
        return new CommonRuntimeException(code, message);
    }

    public static CommonRuntimeException getException(Enum<? extends EnumResult> statusEnum) {
        int code = ((EnumResult) statusEnum).getCode();
        return new CommonRuntimeException(code, ((EnumResult) statusEnum).getDescription(null));
    }

    public static CommonRuntimeException getException(Enum<? extends EnumResult> statusEnum, Object[] objects) {
        int code = ((EnumResult) statusEnum).getCode();
        String description = ((EnumResult) statusEnum).getDescription(objects);

        return new CommonRuntimeException(code, description);
    }

    public static CommonRuntimeException getException(Enum<? extends EnumResult> statusEnum, Object object) {
        int code = ((EnumResult) statusEnum).getCode();
        String description = ((EnumResult) statusEnum).getDescription(new Object[]{object});

        return new CommonRuntimeException(code, description);
    }

    public static CommonRuntimeException getException(Object[] params, Enum<? extends EnumResult> statusEnum, Object[] objects) {
        int code = ((EnumResult) statusEnum).getCode();
        String description = ((EnumResult) statusEnum).getDescription(objects);

        return new CommonRuntimeException(code, description, params);
    }

    public static CommonRuntimeException getException(int code, String description, Object[] params) {
        return new CommonRuntimeException(code, description, params);
    }
}