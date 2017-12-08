package wangzhongqiu.spring.core.utils;

public interface ServiceCallback {
    public abstract CallbackResult executeCheck();

    public abstract CallbackResult executeAction();
}