package com.atguigu.crowd.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Dec
 */
@Controller
public class PortalHandler {

    @RequestMapping("/")
    public String showPortalPage() {
        // 这里实际开发中需要加载数据……
        return "portal";
    }
}