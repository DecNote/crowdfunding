package com.atguigu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Dec
 */
@EnableZuulProxy
@SpringBootApplication
public class CrowdMainClass16 {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass16.class, args);
    }
}