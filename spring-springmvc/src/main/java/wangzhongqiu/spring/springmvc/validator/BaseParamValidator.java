package wangzhongqiu.spring.springmvc.validator;

import wangzhongqiu.spring.core.utils.EnumResult;

/**
 * Abstarct class for parameter validator
 * 
 * 
 */
public abstract class BaseParamValidator implements CommonValidator {

    private Object arg;
    private String argName;
    private Enum<? extends EnumResult> msgEnum;
    private int maxLen;//最大长度
    private int minLen;//最小长度
    private String regStr;//正则表达式

    public Object getArg() {
        return arg;
    }

    public void setArg(Object arg) {
        this.arg = arg;
    }

    public String getArgName() {
        return argName;
    }

    public void setArgName(String argName) {
        this.argName = argName;
    }

    public Enum<? extends EnumResult> getMsgEnum() {
        return msgEnum;
    }

    public void setMsgEnum(Enum<? extends EnumResult> msgEnum) {
        this.msgEnum = msgEnum;
    }

    public int getMaxLen() {
        return maxLen;
    }

    public void setMaxLen(int maxLen) {
        this.maxLen = maxLen;
    }

    public int getMinLen() {
        return minLen;
    }

    public void setMinLen(int minLen) {
        this.minLen = minLen;
    }

    public String getRegStr() {
        return regStr;
    }

    public void setRegStr(String regStr) {
        this.regStr = regStr;
    }
}
