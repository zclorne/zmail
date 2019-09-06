package com.zclorne.zmall.service;

import com.zclorne.zmall.bean.PmsProductSaleAttr;
import com.zclorne.zmall.bean.PmsSkuInfo;

import java.util.List;


/**
 * Created by zclorne
 * 2019/9/3 11:17
 */
public interface SkuService {
    String saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuInfoById(String skuId);

    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);
}
