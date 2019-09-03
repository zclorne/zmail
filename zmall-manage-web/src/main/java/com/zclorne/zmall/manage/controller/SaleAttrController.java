package com.zclorne.zmall.manage.controller;

import com.zclorne.zmall.bean.PmsBaseSaleAttr;
import com.zclorne.zmall.service.SaleAttrService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zclorne
 * 2019/9/2 20:43
 */
@RestController
@CrossOrigin
public class SaleAttrController {

    @Reference
    private SaleAttrService saleAttrService;

    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        return saleAttrService.baseSaleAttrList();
    }
}
