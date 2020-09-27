package com.atguigu.crowd.test;

import com.atguigu.crowd.util.CrowdUtil;
import org.junit.Test;


public class StringTest {
    @Test
    public void testMD5() {
        String source = "1234";
        String s = CrowdUtil.md5(source);
        System.out.println(s);

    }
}
