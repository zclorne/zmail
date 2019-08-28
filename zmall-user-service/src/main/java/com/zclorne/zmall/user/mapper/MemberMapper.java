package com.zclorne.zmall.user.mapper;

import com.zclorne.zmall.bean.UmsMember;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by zclorne
 * 2019/8/28 13:43
 */
@Repository
public interface MemberMapper extends Mapper<UmsMember> {
}
