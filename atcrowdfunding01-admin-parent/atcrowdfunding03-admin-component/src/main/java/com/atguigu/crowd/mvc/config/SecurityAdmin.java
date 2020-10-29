package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * 考虑到User对象中仅仅包含账号和密码，为了能够获取到原始Admin对象，专门创建这个类继承User类来进行扩展
 * @author Dec
 */
public class SecurityAdmin extends User {

    /**
     * 原始Admin对象，包含Admin对象的全部属性
     */
    private Admin originalAdmin;

    public SecurityAdmin(Admin originalAdmin, List<GrantedAuthority> authorities){
        super(originalAdmin.getLoginAcct(), originalAdmin.getUserPswd(), authorities);
        this.originalAdmin = originalAdmin;
    }
}
