package com.zclorne.zmall.manage.controller;

import com.sun.org.apache.xml.internal.resolver.Catalog;
import com.zclorne.zmall.bean.PmsBaseCatalog1;
import com.zclorne.zmall.bean.PmsBaseCatalog2;
import com.zclorne.zmall.bean.PmsBaseCatalog3;
import com.zclorne.zmall.service.CatalogService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zclorne
 * 2019/8/29 22:04
 */
@RestController
@CrossOrigin
public class CatalogController {

    @Reference
    private CatalogService catalogService;

    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<PmsBaseCatalog1> getCatalog1(){
        return catalogService.getCatalog1();
    }

    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<PmsBaseCatalog2> getCatalog2(@RequestParam String catalog1Id){
        return catalogService.getCatalog2(catalog1Id);
    }

    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<PmsBaseCatalog3> getCatalog3(@RequestParam String catalog2Id){
        return catalogService.getCatalog3(catalog2Id);
    }
}
