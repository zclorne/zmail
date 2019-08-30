package com.zclorne.zmall.service;

import com.zclorne.zmall.bean.PmsBaseAttrInfo;
import com.zclorne.zmall.bean.PmsBaseAttrValue;

import java.util.List;

/**
 * Created by zclorne
 * 2019/8/30 9:40
 */
public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);
}
