package wangzhongqiu.spring.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wangzhongqiu.spring.mybatis.annotation.ReadOnlyDataSource;
import wangzhongqiu.spring.springmvc.annotation.Maintain;

/**
 * @author wangzhongqiu
 * @date 2017/12/11.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController extends BaseController {
    @RequestMapping(value = "/readOnly")
    @ResponseBody
    @Maintain
    @ReadOnlyDataSource
    public ModelMap readOnly(String str) {
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
