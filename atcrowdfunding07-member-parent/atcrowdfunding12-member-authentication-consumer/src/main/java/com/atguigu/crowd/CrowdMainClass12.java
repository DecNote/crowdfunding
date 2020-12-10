package com.atguigu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Dec
 */

@EnableFeignClients
@SpringBootApplication
public class CrowdMainClass12 {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass12.class, args);
    }
}
