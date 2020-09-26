package com.atguigu.crowd.test;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})


public class CrowdTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminService adminService;


    /**
     * 测试数据库连接是否成功（非Mybatis）
     */
    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }


    /**
     * Mybatis连接测试
     */
    @Test
    public void testMapper() {
        System.out.println(adminMapper);
        Admin admin = new Admin(null, "Tom", "123456", "汤姆", "tom@qq.com", null);
        int count = adminMapper.insert(admin);
        System.out.println("受影响的行数：" + count);
    }


    /**
     * 日志系统测试
     */
    @Test
    public void testLog() {
        // 日志级别：debug < info < warn < error
        // 在配置文件中选定4者之一，将只打印当前级别和更高级别
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        // 根据日志不同级别打印日志
        logger.debug("debug level!!!");
        logger.info("info level!!!");
        logger.warn("warn level!!!");
        logger.error("error level!!!");
    }

    /**
     * 声明式事务(TX)控制测试
     */
    @Test
    public void testTx() {

        Admin admin = new Admin(null, "Tom", "123456", "汤姆", "tom@qq.com", null);
        adminService.saveAdmin(admin);

    }
}
