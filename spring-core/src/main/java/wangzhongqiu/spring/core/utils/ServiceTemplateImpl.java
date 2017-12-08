package wangzhongqiu.spring.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import wangzhongqiu.spring.core.exception.CommonRuntimeException;
import wangzhongqiu.spring.core.log.ILogger;
import wangzhongqiu.spring.core.log.ILoggerFactory;

public class ServiceTemplateImpl implements ServiceTemplate {
    private static final ILogger logger = ILoggerFactory.getLogger(ServiceTemplateImpl.class);

    @Autowired
    protected TransactionTemplate transactionTemplate;

    public CallbackResult execute(ServiceCallback action, Object domain) {
        return execute(action, domain, null);
    }

    public CallbackResult executeWithoutTransaction(ServiceCallback action, Object domain) {
        return executeWithoutTransaction(action, domain, null);
    }

    public CallbackResult execute(final ServiceCallback action, Object domain, ServiceListener listener) {
        if (logger.isDebugEnabled()) {
            logger.debug("进入模板方法开始处理");
        }
        if (listener == null) {
            listener = new AbstractServiceListener();
        }
        final ServiceListener flistener = listener;
        CallbackResult result = null;
        try {
            flistener.beforeCheck();
            result = action.executeCheck();
            flistener.afterCheck();
            if (result.isSuccess()) {
                flistener.beforeExecute();
                result = (CallbackResult) this.transactionTemplate.execute(new TransactionCallback() {
                    public Object doInTransaction(TransactionStatus status) {
                        CallbackResult iNresult = action.executeAction();
                        if (null == iNresult) {
                            throw new ServiceException(CallbackResult.FAILURE);
                        }
                        flistener.templateExtensionInTransaction(iNresult);
                        if (iNresult.isFailure()) {
                            status.setRollbackOnly();
                            return iNresult;
                        }

                        return iNresult;
                    }
                });
                flistener.afterExecute();
                if (result.isSuccess()) {
                    flistener.templateExtensionAfterTransactionSuccess(result);
                }
                if (result.isFailure()) {
                    flistener.templateExtensionAfterTransactionFailure(result);
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
            result = CallbackResult.failure(e.getErrorCode(), e, e.getParameters());
            flistener.onException(e);
        } catch (CommonRuntimeException e) {
            e.printStackTrace();
            result = CallbackResult.failure(e.getErrorCode(), e, e.getParameters());
            flistener.onException(e);
        } catch (Throwable e) {
            e.printStackTrace();
            result = CallbackResult.failure(CallbackResult.FAILURE, e);
            flistener.onException(e);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("模板方法处理结束");
        }
        return result;
    }

    public CallbackResult executeWithoutTransaction(ServiceCallback action, Object domain, ServiceListener listener) {
        if (logger.isDebugEnabled()) {
            logger.debug("进入模板方法开始处理");
        }

        if (listener == null) {
            listener = new AbstractServiceListener();
        }
        ServiceListener flistener = listener;
        CallbackResult result = null;
        try {
            flistener.beforeCheck();
            result = action.executeCheck();
            flistener.afterCheck();
            if (result.isSuccess()) {
                flistener.beforeExecute();
                result = action.executeAction();
                flistener.afterExecute();
                if (null == result) {
                    throw new ServiceException(CallbackResult.FAILURE_NO_ROLLBACK);
                }
                flistener.templateExtensionAfterExecuteSuccess(result);
            }
            if (result.isFailure())
                flistener.templateExtensionAfterExecuteFailure(result);
        } catch (ServiceException e) {
            e.printStackTrace();
            result = CallbackResult.failure(e.getErrorCode(), e, e.getParameters());
            flistener.onException(e);
        } catch (CommonRuntimeException e) {
            e.printStackTrace();
            result = CallbackResult.failure(e.getErrorCode(), e, e.getParameters());
            flistener.onException(e);
        } catch (Throwable e) {
            e.printStackTrace();
            result = CallbackResult.failure(CallbackResult.FAILURE, e);
            flistener.onException(e);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("模板方法处理结束");
        }
        return result;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}