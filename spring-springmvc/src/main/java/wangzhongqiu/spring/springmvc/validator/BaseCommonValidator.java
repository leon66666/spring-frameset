package wangzhongqiu.spring.springmvc.validator;

import wangzhongqiu.spring.core.model.User;
import wangzhongqiu.spring.springmvc.contants.WebConstants;

/**
 * Abstarct class for common validator
 * 
 * 
 */
public abstract class BaseCommonValidator implements CommonValidator {


    protected User getUser(ValidateContext context) {

        Object obj = null;
        try {
            obj = context.getObj(WebConstants.VALIDATE_CONTEXT_KEY_USER);
            if (null != obj && obj instanceof User) {
                return (User) obj;
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }
}
