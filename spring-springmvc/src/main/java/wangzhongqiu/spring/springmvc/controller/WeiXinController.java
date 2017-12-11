package wangzhongqiu.spring.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wangzhongqiu.spring.mybatis.annotation.ReadOnlyDataSource;
import wangzhongqiu.spring.springmvc.annotation.Maintain;
import wangzhongqiu.spring.springmvc.utils.QRCodeUtil;
import wangzhongqiu.spring.springmvc.utils.WeixinUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/weixin")
public class WeiXinController extends BaseController {

    /**
     * 微信分享自定义
     */

    @RequestMapping(value = "/getSignature")
    @ResponseBody
    @Maintain
    @ReadOnlyDataSource
    public ModelMap getSignature(HttpServletRequest request, @RequestParam(value = "url", required = true) String url) {
        ModelMap mm = new ModelMap();
        try {
            Map<String, String> map = WeixinUtil.setShareConfig(request, url);
            mm.put("data", map);
            mm.addAttribute("message", "success");
            mm.addAttribute("status", "0");
        } catch (Exception e) {
            e.printStackTrace();
            mm.addAttribute("message", "failure");
            mm.addAttribute("status", "1");
        }

        return mm;
    }

    /**
     * 二维码生成
     */

    @RequestMapping(value = "/createQr")
    @ResponseBody
    @Maintain
    public ModelMap createQr(HttpServletRequest request,
                             @RequestParam(value = "code", required = true) String code) {

        ModelMap mm = new ModelMap();
        Map<String, String> map = new HashMap<String, String>();
        try {
            String imagePath = "/opt/image" + "/" + code + ".png";
            String url = "http://192.168.1.26:3300/landing/invite/" + code;
            File outputFile = new File(imagePath);
            QRCodeUtil.create(new String(url.getBytes("UTF-8"), "iso-8859-1"), outputFile);
            map.put("url", imagePath);
            map.put("code", code);
            mm.put("data", map);
            mm.addAttribute("message", "二维码创建成功");
            mm.addAttribute("status", "0");
        } catch (Exception e) {
            e.printStackTrace();
            mm.addAttribute("message", "二维码创建失败");
            mm.addAttribute("status", "1");
        }

        return mm;
    }
}
