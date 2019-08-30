package com.zclorne.zmall.manage.service.impl;

import com.zclorne.zmall.bean.PmsBaseAttrInfo;
import com.zclorne.zmall.bean.PmsBaseAttrValue;
import com.zclorne.zmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.zclorne.zmall.manage.mapper.PmsBaseAttrValueMapper;
import com.zclorne.zmall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by zclorne
 * 2019/8/30 9:41
 */
@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> list = pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
        return list;
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {

        String id = pmsBaseAttrInfo.getId();
        if (StringUtils.isBlank(id)) {
            //id 为空 新增
            pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);

            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            attrValueList.forEach(t -> {
                t.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueMapper.insertSelective(t);
            });
        } else {
            //id 不空 修改
            //修改属性
            Example e = new Example(PmsBaseAttrInfo.class);
            e.createCriteria().andEqualTo("id", pmsBaseAttrInfo.getId());
            pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo, e);

            //修改属性值
            //1. 删除原有属性值
            List<PmsBaseAttrValue> delList = pmsBaseAttrInfoMapper.selectByPrimaryKey(pmsBaseAttrInfo.getId()).getAttrValueList();
            delList.forEach(t->pmsBaseAttrValueMapper.delete(t));

            //2. 插入新属性值
            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
            attrValueList.forEach(t -> {
                t.setAttrId(pmsBaseAttrInfo.getId());
                pmsBaseAttrValueMapper.insertSelective(t);
            });
        }
        return "success";
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        return pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
    }
}
