package com.atguigu.crowd.mvc.handler;


import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/test")
public class HandlerTest {
    @Autowired
    private AdminService adminService;


    private Logger logger = LoggerFactory.getLogger(HandlerTest.class);

    @RequestMapping("ssm.html")
    public String testSsm(ModelMap modelMap, HttpServletRequest request) {
        // part1：SSM整合成功与否测试
        List<Admin> adminList = adminService.getAll();
        modelMap.addAttribute("adminList", adminList);


        // part2：测试基于XML的异常映射机制，在spring-web-mvc.xml中配置。映射指的是有异常跳转error页面
        System.out.println(10 / 0);
        int a = 1 / 0;


        // part3：请求类型判断
        boolean type = CrowdUtil.judgeRequestType(request);
        if (type) {
            logger.info("请求类型为：json数据（ajax请求）");
        } else {
            logger.info("请求类型为：html或其他（普通请求）");
        }


        // part4：测试基于注解的异常映射机制，在component模块的CrowExceptionResolver.java中定义
//        throw new NullPointerException();

        return "success";
    }


    @RequestMapping("error.html")
    public void testError() throws Exception {
        System.out.println("----------\n----------\n----------\n----------\n----------\n");
        throw new Exception();
    }


}
