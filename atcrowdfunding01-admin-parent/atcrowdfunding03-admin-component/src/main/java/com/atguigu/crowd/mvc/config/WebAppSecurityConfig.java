package com.atguigu.crowd.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Dec
 */

// 将当前类标记为配置类
@Configuration
// 启用 Web 环境下权限控制功能
@EnableWebSecurity

public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        // 临时使用的内存版登录的模式（测试代码）
        builder.inMemoryAuthentication().withUser("tom").password("123456").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {


        security
                .authorizeRequests()
                .antMatchers("/admin/to/login/page.html")
                .permitAll()
                .antMatchers("/bootstrap/**")
                .permitAll()
                .antMatchers("/crowd/**")
                .permitAll()
                .antMatchers("/css/**")
                .permitAll()
                .antMatchers("/fonts/**")
                .permitAll()
                .antMatchers("/img/**")
                .permitAll()
                .antMatchers("/jquery/**")
                .permitAll()
                .antMatchers("/layer/**")
                .permitAll()
                .antMatchers("/script/**")
                .permitAll()
                .antMatchers("/ztree/**")
                .permitAll()

                // 其他任意请求，登录后访问
                .anyRequest()
                .authenticated()
                .and()

                // 禁用防跨站请求伪造（CSRF）功能
                .csrf()
                .disable()

                // 开启表单登录
                .formLogin()
                // 指定登录页面
                .loginPage("/admin/to/login/page.html")
                // 指定处理登录请求的地址（表单提交地址）
                .loginProcessingUrl("/security/do/login.html")
                // 指定登录成功后默认前往的地址
                .defaultSuccessUrl("/admin/to/main/page.html")
                .usernameParameter("loginAcct")
                .passwordParameter("userPswd");
    }

}