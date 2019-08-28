package com.zclorne.zmail.user.service.impl;

import com.zclorne.zmail.user.bean.UmsMember;
import com.zclorne.zmail.user.mapper.MemberMapper;
import com.zclorne.zmail.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
