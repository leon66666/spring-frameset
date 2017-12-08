package wangzhongqiu.spring.core.exception;

import org.springframework.web.bind.ServletRequestBindingException;

/**
 * Custom parameter exception for arguments validation.
 * 
 * 
 */
@SuppressWarnings("serial")
public class InvalidServletRequestParameterException extends ServletRequestBindingException{

    private final String parameterName;

    private final String parameterType;
    
    private final String invalidText;//错误提示文本


    /**
     * Constructor for InvalidServletRequestParameterException.
     * @param parameterName the name of the missing parameter
     * @param parameterType the expected type of the missing parameter
     */
    public InvalidServletRequestParameterException(String parameterName, String parameterType, String invalidText) {
        super("");
        this.parameterName = parameterName;
        this.parameterType = parameterType;
        this.invalidText = invalidText;
    }


    @Override
    public String getMessage() {
        return invalidText;
    }

    /**
     * Return the name of the offending parameter.
     */
    public final String getParameterName() {
        return this.parameterName;
    }

    /**
     * Return the expected type of the offending parameter.
     */
    public final String getParameterType() {
        return this.parameterType;
    }


    public String getInvalidText() {
        return invalidText;
    }
    
}
