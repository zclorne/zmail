package com.zclorne.zmail.user.controller;

import com.zclorne.zmail.user.bean.UmsMember;
import com.zclorne.zmail.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MemberService memberService;

    @RequestMapping("getMember")
    @ResponseBody
    public List<UmsMember> getMember(){
        return memberService.getMember();
    }


}
