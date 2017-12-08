package wangzhongqiu.spring.core.utils;

public interface ServiceListener {
    void beforeCheck();

    void afterCheck();

    void beforeExecute();

    void afterExecute();

    void onException(Throwable paramThrowable);

    void templateExtensionInTransaction(CallbackResult paramCallbackResult);

    void templateExtensionAfterTransactionSuccess(CallbackResult paramCallbackResult);

    void templateExtensionAfterTransactionFailure(CallbackResult paramCallbackResult);

    void templateExtensionAfterExecuteSuccess(CallbackResult paramCallbackResult);

    void templateExtensionAfterExecuteFailure(CallbackResult paramCallbackResult);
}