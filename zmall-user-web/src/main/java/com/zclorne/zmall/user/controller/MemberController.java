package com.zclorne.zmall.user.controller;

import com.zclorne.zmall.bean.UmsMember;
import com.zclorne.zmall.service.MemberService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zclorne
 * 2019/8/28 13:38
 */
@RestController
@RequestMapping("member")
public class MemberController {

    @Reference
    private MemberService memberService;

    @RequestMapping("getMember")
    @ResponseBody
    public List<UmsMember> getMember(){
        return memberService.getMember();
    }


}
