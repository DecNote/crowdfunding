package com.atguigu.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Dec
 */



@SpringBootApplication
@MapperScan("com.atguigu.crowd.mapper")
public class CrowdMainClass10 {
    public static void main(String[] args) {
        SpringApplication.run(CrowdMainClass10.class, args);
    }
}
