package wangzhongqiu.spring.springmvc.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import wangzhongqiu.spring.core.constants.Constants;
import zhongqiu.javautils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能封禁
 * @author luzongwei
 */
public class AntiPluginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AntiPluginBlackListService antiPluginBlackListService;
    //3050=因使用第三方程序，您的账户已被封禁，如有任何疑问请与客服联系。
    private static final int pluginErrorCode = 3050;
    //3051=因资金操作异常，您的账户已被封禁，如有任何疑问请与客服联系。
    private static final int capitalErrorCode = 3051;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String _userId = request.getParameter(Constants.USER_ID);
        if(StringUtil.isEmpty(_userId)){
            return true;
        }
        Integer userId = Integer.parseInt(_userId);

        String path = request.getServletPath();
        if ("/financeplan/registerfinanceplan.action".equals(path)) {
            BlackListAntiplugin bla = antiPluginBlackListService.getUserAnti(userId, AntiPluginType.ANTI_PLUGIN_BUY_FINANCE_PLAN);
            return responseError(response, bla, pluginErrorCode);
        }
        if ("/loantransfer/buy.action".equals(path)) {
            BlackListAntiplugin bla = antiPluginBlackListService.getUserAnti(userId, AntiPluginType.ANTI_PLUGIN_BUY_LOAN_TRANSFER);
            return responseError(response, bla, pluginErrorCode);
        }
        if ("/lend/loanlender.action".equals(path)) {
            BlackListAntiplugin bla = antiPluginBlackListService.getUserAnti(userId, AntiPluginType.ANTI_PLUGIN_BUY_LOAN);
            return responseError(response, bla, pluginErrorCode);
        }
        if ("/paycenter/getFuyuouRequest".equals(path)) {
            BlackListAntiplugin bla = antiPluginBlackListService.getUserAnti(userId, AntiPluginType.ANTI_PLUGIN_CHECK_IN);
            return responseError(response, bla, capitalErrorCode);
        }
        if ("/cashdraw/manualCashDrawSubmit".equals(path) ||
                "/cashdraw/doBatchCashdrawForWait".equals(path) ||
                "/cashdraw/auditCashLogForApply".equals(path) ||
                "/paycenter/applyForCash".equals(path)) {
            BlackListAntiplugin bla = antiPluginBlackListService.getUserAnti(userId, AntiPluginType.ANTI_PLUGIN_CASH_DRAW);
            return responseError(response, bla, capitalErrorCode);
        }

        return true;
    }

    private boolean responseError(HttpServletResponse response, BlackListAntiplugin bla,int errorCode) throws IOException {
        if (bla != null) {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(MobileJsonResultVo.buildErrorVo(errorCode).toJson());
            response.getWriter().close();
            return false;
        }
        return true;
    }



}
