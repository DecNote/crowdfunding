package com.atguigu.crowd.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Temp {
    public static void main(String[] args) throws FileNotFoundException {

        String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
        String accessKeyId = "LTAI4GAVYNaiTHt3faFeyEML";
        String accessKeySecret = "xYriLsuAiOlNWW7rZUTVIK6UWiTYKi";
        InputStream inputStream = new FileInputStream("C:\\Users\\Dec\\Desktop\\apple.jpg");
        String bucketName = "atguigu000";
        String bucketDomain = "http://atguigu000.oss-cn-shanghai.aliyuncs.com";
        String originalName = "apple.jpg";
        ResultEntity<String> resultEntity = CrowdUtil.uploadFileToOss(endpoint, accessKeyId, accessKeySecret, inputStream, bucketName, bucketDomain, originalName);
        System.out.println(resultEntity);
    }
}
