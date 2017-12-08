package wangzhongqiu.spring.core.utils;

public class AbstractServiceListener
        implements ServiceListener {
    public void beforeCheck() {
    }

    public void afterCheck() {
    }

    public void beforeExecute() {
    }

    public void afterExecute() {
    }

    public void onException(Throwable throwable) {
    }

    public void templateExtensionInTransaction(CallbackResult result) {
    }

    public void templateExtensionAfterTransactionSuccess(CallbackResult result) {
    }

    public void templateExtensionAfterTransactionFailure(CallbackResult result) {
    }

    public void templateExtensionAfterExecuteSuccess(CallbackResult result) {
    }

    public void templateExtensionAfterExecuteFailure(CallbackResult result) {
    }
}