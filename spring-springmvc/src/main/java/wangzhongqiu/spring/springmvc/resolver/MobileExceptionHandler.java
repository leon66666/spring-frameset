package wangzhongqiu.spring.springmvc.resolver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MobileExceptionHandler implements HandlerExceptionResolver {

    private static Log logger = LogFactory.getLog(MobileExceptionHandler.class);

    private static final Log LOGGER = LogFactory.getLog(MobileExceptionHandler.class);
    private static final int DEFAULT_STATUS_CODE = 1000;
    private static final int UPLOAD_SIZE_EXCEEDED = 300601;
    private static final String SPLIT = "";

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();
        ModelAndView mav = new ModelAndView();
        Map<String, Object> model = new HashMap();
        // 默认异常信息 “请求失败，请稍后再试”
        int status = DEFAULT_STATUS_CODE;
        MappingJacksonJsonView view = new MappingJacksonJsonView();
        try {
            // 必选参数丢失
            if (ex instanceof MissingServletRequestParameterException
                    || ex instanceof HttpRequestMethodNotSupportedException || ex instanceof TypeMismatchException) {
                status = 1001;
            } else if (ex instanceof MaxUploadSizeExceededException) {
                LOGGER.error("上传头像图片大小超过系统限制", ex);
                status = UPLOAD_SIZE_EXCEEDED;
            }
            model.put("message", "错误");
            model.put("status", status);
            view.setAttributesMap(model);
            mav.setView(view);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return mav;
    }
}
