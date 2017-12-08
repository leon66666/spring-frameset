package wangzhongqiu.spring.core.utils;

public interface ServiceTemplate {
    CallbackResult execute(ServiceCallback paramServiceCallback, Object paramObject);

    CallbackResult executeWithoutTransaction(ServiceCallback paramServiceCallback, Object paramObject);

    CallbackResult execute(ServiceCallback paramServiceCallback, Object paramObject, ServiceListener paramServiceListener);

    CallbackResult executeWithoutTransaction(ServiceCallback paramServiceCallback, Object paramObject, ServiceListener paramServiceListener);
}