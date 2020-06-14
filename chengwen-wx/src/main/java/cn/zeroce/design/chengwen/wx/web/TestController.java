package cn.zeroce.design.chengwen.wx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zeroce
 * @date 20.4.17 10:21
 */
@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "testController";
    }
}
