package com.atguigu.crowd.handler;

import com.atguigu.crowd.entity.po.MemberPO;
import com.atguigu.crowd.service.api.MemberService;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dec
 */

@RestController
public class MemberProviderHandler {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/get/memberpo/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct){
        try {
            // 1. 调用本地Service查询
            MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginacct);
            // 2. 如果成功返回成功结果
            return ResultEntity.successWithData(memberPO);
        } catch (Exception e) {
            // 3. 如果失败返回失败结果
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }
}
