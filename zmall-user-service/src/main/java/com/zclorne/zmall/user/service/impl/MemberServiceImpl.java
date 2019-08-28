package com.zclorne.zmall.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zclorne.zmall.bean.UmsMember;
import com.zclorne.zmall.service.MemberService;

import com.zclorne.zmall.user.mapper.MemberMapper;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by zclorne
 * 2019/8/28 13:40
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<UmsMember> getMember() {
        return memberMapper.selectAll();
    }
}
