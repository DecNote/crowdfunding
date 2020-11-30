package com.atguigu.crowd.test;

import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;

/**
 * @author Dec
 */
public class CrowdTest {
    public static void main(String[] args) {


        String host = "https://smssend.shumaidata.com";
        String path = "/sms/send";
        String method = "POST";
        String appcode = "4785a45c90d844efbcd93bd86ada3926";


        String templateId = "M09DD535F4";
        String phone = "15301160965";



        try {
            ResultEntity<String> resultEntity = CrowdUtil.sendCodeByShortMessage(host, path, method, appcode, phone, templateId);
            System.out.println(resultEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
