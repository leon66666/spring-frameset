package wangzhongqiu.spring.springmvc.resolver;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import wangzhongqiu.spring.core.exception.InvalidServletRequestParameterException;
import wangzhongqiu.spring.core.utils.CallbackResult;
import wangzhongqiu.spring.springmvc.annotation.EntityCheck;
import wangzhongqiu.spring.springmvc.annotation.ParamCheck;
import wangzhongqiu.spring.springmvc.enums.ParamStatusEnum;
import wangzhongqiu.spring.springmvc.validator.BaseParamValidator;
import wangzhongqiu.spring.springmvc.validator.ValidateContext;
import wangzhongqiu.spring.springmvc.validator.impl.DefaultValidator;

import java.lang.reflect.Field;
import java.util.Iterator;

/**
 * Custom parameter resolver , add the support for custom parameter annotation
 * eg: @NotBlankField
 * 
 * 
 */
public class RRDCustomArgumentResolver implements HandlerMethodArgumentResolver {
    
    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        CommonAnnotationBeanPostProcessor commonAnnotationBeanPostProcessor;
        Object arg = null;
        String[] paramValues = webRequest.getParameterValues(parameter.getParameterName());
        if (paramValues != null) {
            arg = paramValues.length >= 1 ? paramValues[0] : null;
        }

        ParamCheck paramCheck = parameter.getParameterAnnotation(ParamCheck.class);
        if(null != paramCheck){
            try {
                this.doCheck(arg, paramCheck, parameter.getParameterName(),parameter.getParameterType().getSimpleName());
            } catch (RuntimeException e) {
                LOGGER.error(e);
            }
            return arg;
        }
        
        EntityCheck entityCheck = parameter.getParameterAnnotation(EntityCheck.class);
        if(null != entityCheck){
            arg = this.buildEntityFromParam(parameter, webRequest);
            try {
                this.doCheck(arg, entityCheck, parameter,webRequest);
            } catch (RuntimeException e) {
                LOGGER.error(e);
            }
            return arg;
        }
        return arg;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (parameter.hasParameterAnnotation(ParamCheck.class) || parameter
                .hasParameterAnnotation(EntityCheck.class));
    }
    
    private Object buildEntityFromParam(MethodParameter parameter,NativeWebRequest webRequest){
        
        Object o = null;
        try {
            o = BeanUtils.instantiate(parameter.getParameterType());
            StringBuffer tmp;
            String[] val;
            Field[] frr = parameter.getParameterType().getDeclaredFields();
            for (Iterator<String> itr = webRequest.getParameterNames(); itr.hasNext();) {
                tmp = new StringBuffer(itr.next());
                for (int i = 0; i < frr.length; i++) {
                    frr[i].setAccessible(true);
                    if (tmp.toString().equals(frr[i].getName())) {
                        val = webRequest.getParameterValues(tmp.toString());
                        frr[i].set(o, val[0]);
                    }
                }
            }
        }catch (Exception e) {
            LOGGER.error(e);
        }
        return o;
    }

    private String buildError(ParamCheck check,String defaultError){
       
        String error = null;
        if(!check.msg().equals(ParamStatusEnum.NULL)){
            error = check.msg().getDescription();
            if(StringUtils.isBlank(error)){
                error = defaultError;
            }
        }else{
            error = defaultError;
        }
        return error;
    }
    
    private void doCheck(Object arg, ParamCheck check, String paramName, String paramType)
            throws Exception {

        BaseParamValidator validator =  check.validator().newInstance();
        
        /**
         * If there custom validator exists in the @ParamCheck annotation, we need
         *  to use in first, which has more higher priority.
         */
        if(validator instanceof DefaultValidator){
            
            if (check.notBlank()) {
                if (null == arg || StringUtils.isBlank(arg.toString())) {
                    String defaultError =  ParamStatusEnum.NOT_BLANK_DEFAULT.getDescription(paramName);
                    throw new InvalidServletRequestParameterException(paramName, paramType,this.buildError(check, defaultError));
                }
            }

            if (check.maxLen() > 0) { // 判断字符串最大长度
                if (null != arg && ((String) arg).length() > check.maxLen()){
                    String defaultError =  ParamStatusEnum.MAX_LEN_DEFAULT.getDescription(paramName,check.maxLen());
                    throw new InvalidServletRequestParameterException(paramName, paramType,this.buildError(check, defaultError));
                }
            }

            if (check.minLen() > 0) { // 判断字符串最小长度
                if (null != arg && ((String) arg).length() < check.minLen()){
                    String defaultError =  ParamStatusEnum.MIN_LEN_DEFAULT.getDescription(paramName,check.minLen());
                    throw new InvalidServletRequestParameterException(paramName, paramType,this.buildError(check, defaultError));
                }

            }

            if (!"".equals(check.regStr())) { // 判断正则
                if (null != arg && arg instanceof String) {
                    if (!((String) arg).matches(check.regStr())){
                        String defaultError =  ParamStatusEnum.REG_DEFAULT.getDescription(paramName);
                        throw new InvalidServletRequestParameterException(paramName, paramType,this.buildError(check, defaultError));
                    }
                }
            }
        } else {
            
            ValidateContext context = new ValidateContext();
            
            validator.setArg(arg);
            validator.setArgName(paramName);
//            validator.setMsgEnum(check.msg());
            validator.setMinLen(check.minLen());
            validator.setMaxLen(check.maxLen());
            validator.setRegStr(check.regStr());
            
            CallbackResult validateResult = validator.validate(context);
            if (validateResult.isFailure()) {
                throw new InvalidServletRequestParameterException(paramName, paramType, validateResult.getThrowable().getMessage());
            }
        }
        
     
    }
    
    private void doCheck(Object arg, EntityCheck entityCheck, MethodParameter parameter, NativeWebRequest webRequest)
            throws Exception {

        Class<?> paramTypeClass = parameter.getParameterType();
        Field[] fields = paramTypeClass.getDeclaredFields();
        for(Field field:fields){
            ParamCheck paramCheck = field.getAnnotation(ParamCheck.class);
            if(null !=paramCheck){
                String[] values = webRequest.getParameterValues(field.getName());
                String checkArg = (null != values && values.length == 1) ? values[0] : null;
                this.doCheck(checkArg, paramCheck,field.getName(),field.getType().getSimpleName());
            }
        }
    }
}
