package wangzhongqiu.spring.core.utils;

import java.io.Serializable;

public class CallbackResult<T> implements Serializable {
    private static final long serialVersionUID = -8761836285176496503L;
    public static final int SUCCESS = 1;
    public static final int FAILURE_NO_ROLLBACK = 0;
    public static final int FAILURE = -1;
    private int statusCode = 1;

    private int resultCode = 0;
    private Throwable throwable;
    private T businessObject;

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public T getBusinessObject() {
        return this.businessObject;
    }

    public void setBusinessObject(T businessObject) {
        this.businessObject = businessObject;
    }

    private CallbackResult(int statusCode, int resultCode, Throwable throwable, T businessObject) {
        this.statusCode = statusCode;
        this.resultCode = resultCode;
        this.throwable = throwable;
        this.businessObject = businessObject;
    }

    public static CallbackResult success() {
        return success(0, null);
    }

    public static CallbackResult success(int resultCode) {
        return success(resultCode, null);
    }

    public static CallbackResult success(int resultCode, Object businessObject) {
        return new CallbackResult(1, resultCode, null, businessObject);
    }

    public static CallbackResult failure(int resultCode) {
        return failure(resultCode, null, null);
    }

    public static CallbackResult failure(int resultCode, Throwable throwable) {
        return failure(resultCode, throwable, null);
    }

    public static CallbackResult failure(int resultCode, Throwable throwable, Object businessObject) {
        return new CallbackResult(-1, resultCode, throwable, businessObject);
    }

    public boolean isSuccess() {
        return this.statusCode == 1;
    }

    public boolean isFailure() {
        return this.statusCode == -1;
    }

    public boolean isNotRollback() {
        return this.statusCode == 0;
    }

    public boolean isNullBusinessObject() {
        return null == this.businessObject;
    }
}