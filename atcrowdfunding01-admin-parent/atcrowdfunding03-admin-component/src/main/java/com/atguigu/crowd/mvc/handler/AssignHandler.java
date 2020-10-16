package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Role;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Dec
 */
@Controller
public class AssignHandler {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;



    @RequestMapping("/assign/to/assign/page.html")
    public String toAssignRolePage(@RequestParam("adminId") Integer adminId, ModelMap modelMap){

        // 1. 查询已分配角色
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);

        // 2. 查询未分配角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);
        // 3. 存入模型(本质上：request.setAttribute("attrName", attrValue))
        modelMap.addAttribute("assignedRoleList", assignedRoleList);
        modelMap.addAttribute("unAssignedRoleList", unAssignedRoleList);

        System.out.println(unAssignedRoleList);
        System.out.println(assignedRoleList);
        return "assign-role";
    }
}
