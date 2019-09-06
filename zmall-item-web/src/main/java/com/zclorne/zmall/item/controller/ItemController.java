package com.zclorne.zmall.item.controller;

import com.alibaba.fastjson.JSON;
import com.zclorne.zmall.bean.PmsProductSaleAttr;
import com.zclorne.zmall.bean.PmsSkuInfo;
import com.zclorne.zmall.bean.PmsSkuSaleAttrValue;
import com.zclorne.zmall.service.SkuService;
import com.zclorne.zmall.service.SpuService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zclorne
 * 2019/9/3 16:41
 */
@Controller
public class ItemController {

    @Reference
    SkuService skuService;

    @Reference
    SpuService spuService;

    @RequestMapping("{skuId}.html")
    public String index(@PathVariable String skuId, ModelMap map) {
        PmsSkuInfo pmsSkuInfo = skuService.getSkuInfoById(skuId);
        map.put("skuInfo", pmsSkuInfo);

        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrListCheckBySku(skuId, pmsSkuInfo.getProductId());
        map.put("spuSaleAttrListCheckBySku", pmsProductSaleAttrs);


        List<PmsSkuInfo> pmsSkuInfos = skuService.getSkuSaleAttrValueListBySpu(pmsSkuInfo.getProductId());
        Map<String,String> skuSaleAttrHash = new HashMap<>();
        pmsSkuInfos.forEach(t -> {
            String k = "";
            String v = t.getId();
            List<PmsSkuSaleAttrValue> skuSaleAttrValueList = t.getSkuSaleAttrValueList();
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                k+=pmsSkuSaleAttrValue.getSaleAttrValueId()+"|";
            }
            skuSaleAttrHash.put(k,v);
        });

        map.put("skuSaleAttrHashJsonStr", JSON.toJSONString(skuSaleAttrHash));
        return "item";
    }
}
