package com.zclorne.zmall.service;

import com.zclorne.zmall.bean.PmsProductImage;
import com.zclorne.zmall.bean.PmsProductInfo;
import com.zclorne.zmall.bean.PmsProductSaleAttr;

import java.util.List;

/**
 * Created by zclorne
 * 2019/8/30 15:54
 */
public interface SpuService {

    List<PmsProductInfo> spuList(String catalog3Id);

    String saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductImage> pmsProductImageList(String productId);

    List<PmsProductSaleAttr> pmsProductSaleAttrList(String productId);
}
