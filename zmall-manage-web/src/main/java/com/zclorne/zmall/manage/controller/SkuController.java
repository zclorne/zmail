package com.zclorne.zmall.manage.controller;

import com.zclorne.zmall.bean.PmsSkuInfo;
import com.zclorne.zmall.service.SkuService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zclorne
 * 2019/9/3 11:14
 */
@RestController
@CrossOrigin
public class SkuController {

    @Reference
    private SkuService skuService;

    @RequestMapping("saveSkuInfo")
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo){

        skuService.saveSkuInfo(pmsSkuInfo);
        return "success";
    }
}
