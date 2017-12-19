package wangzhongqiu.spring.springmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wangzhongqiu.spring.mybatis.annotation.ReadOnlyDataSource;
import wangzhongqiu.spring.springmvc.annotation.EntityCheck;
import wangzhongqiu.spring.springmvc.annotation.Maintain;

/**
 * @author wangzhongqiu
 * @date 2017/12/11.
 */
@Controller
@RequestMapping(value = "/lend")
public class TestController extends BaseController {
    private static Log logger = LogFactory.getLog(TestController.class);

    @RequestMapping(value = "/readOnly")
    @ResponseBody
    @Maintain
    @ReadOnlyDataSource
    public ModelMap readOnly(@RequestParam(value = "pageNumber", required = false) String pageNumber,
                             @RequestParam(value = "pageSize", required = false) String pageSize,
                             @RequestParam(value = "version", required = false, defaultValue = "1.0") String version
    ) {
        ModelMap mm = new ModelMap();
        try {
            mm.addAttribute("message", "success");
            mm.addAttribute("status", "0");
            mm.addAttribute("pageNumber", pageNumber);
            mm.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            mm.addAttribute("message", "failure");
            mm.addAttribute("status", "1");
        }
        return mm;
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public ModelMap test(@EntityCheck String str) {
        ModelMap mm = new ModelMap();
        try {
            mm.addAttribute("message", "success");
            mm.addAttribute("status", "0");
        } catch (Exception e) {
            e.printStackTrace();
            mm.addAttribute("message", "failure");
            mm.addAttribute("status", "1");
        }
        return mm;
    }
}
