package wangzhongqiu.spring.core.log;

public interface ILogger {
    public abstract String getName();

    public abstract boolean isTraceEnabled();

    public abstract void trace(String paramString);

    public abstract void trace(String paramString, Object paramObject);

    public abstract void trace(String paramString, Object[] paramArrayOfObject);

    public abstract void trace(String paramString, Throwable paramThrowable);

    public abstract boolean isDebugEnabled();

    public abstract void debug(String paramString);

    public abstract void debug(String paramString, Object paramObject);

    public abstract void debug(String paramString, Object[] paramArrayOfObject);

    public abstract void debug(String paramString, Throwable paramThrowable);

    public abstract boolean isInfoEnabled();

    public abstract void info(String paramString);

    public abstract void info(String paramString, Object paramObject);

    public abstract void info(String paramString, Object[] paramArrayOfObject);

    public abstract void info(String paramString, Throwable paramThrowable);

    public abstract boolean isWarnEnabled();

    public abstract void warn(String paramString);

    public abstract void warn(String paramString, Object paramObject);

    public abstract void warn(String paramString, Object[] paramArrayOfObject);

    public abstract void warn(String paramString, Throwable paramThrowable);

    public abstract boolean isErrorEnabled();

    public abstract void error(String paramString);

    public abstract void error(String paramString, Throwable paramThrowable);
}