package wangzhongqiu.spring.core.log;

import org.slf4j.Logger;

public class ILoggerImpl
        implements ILogger {
    protected Logger logger = null;

    public void setLogger(Logger loger) {
        this.logger = loger;
    }

    public String getName() {
        return this.logger.getName();
    }

    public boolean isTraceEnabled() {
        return this.logger.isTraceEnabled();
    }

    public void trace(String msg) {
        this.logger.trace(msg);
    }

    public void trace(String format, Object arg) {
        this.logger.trace(format, arg);
    }

    public void trace(String format, Object[] argArray) {
        this.logger.trace(format, argArray);
    }

    public void trace(String msg, Throwable t) {
        this.logger.trace(msg, t);
    }

    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    public void debug(String message) {
        this.logger.debug(message);
    }

    public void debug(String format, Object arg) {
        this.logger.debug(format, arg);
    }

    public void debug(String format, Object[] argArray) {
        this.logger.debug(format, argArray);
    }

    public void debug(String message, Throwable t) {
        this.logger.debug(message, t);
    }

    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    public void info(String message) {
        this.logger.info(message);
    }

    public void info(String format, Object arg) {
        this.logger.info(format, arg);
    }

    public void info(String format, Object[] argArray) {
        this.logger.info(format, argArray);
    }

    public void info(String msg, Throwable t) {
        this.logger.info(msg, t);
    }

    public boolean isWarnEnabled() {
        return this.logger.isWarnEnabled();
    }

    public void warn(String msg) {
        this.logger.warn(msg);
    }

    public void warn(String format, Object arg) {
        this.logger.warn(format, arg);
    }

    public void warn(String format, Object[] argArray) {
        this.logger.warn(format, argArray);
    }

    public void warn(String msg, Throwable t) {
        this.logger.warn(msg, t);
    }

    public boolean isErrorEnabled() {
        return this.logger.isErrorEnabled();
    }

    public void error(String message) {
        this.logger.error(message);
    }

    public void error(String message, Throwable t) {
        this.logger.error(message, t);
    }
}