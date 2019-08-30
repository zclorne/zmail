package com.zclorne.zmall.manage.controller;

import com.zclorne.zmall.bean.PmsBaseAttrInfo;
import com.zclorne.zmall.bean.PmsBaseAttrValue;
import com.zclorne.zmall.service.AttrService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zclorne
 * 2019/8/30 9:38
 */
@RestController
@CrossOrigin
public class AttrController {

    @Reference
    AttrService attrService;

    @RequestMapping("attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(@RequestParam String catalog3Id){

        return attrService.attrInfoList(catalog3Id);
    }

    @RequestMapping("saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
        return attrService.saveAttrInfo(pmsBaseAttrInfo);
    }

    @RequestMapping("getAttrValueList")
    public List<PmsBaseAttrValue> getAttrValueList(@RequestParam String attrId){
        return attrService.getAttrValueList(attrId);
    }
}
