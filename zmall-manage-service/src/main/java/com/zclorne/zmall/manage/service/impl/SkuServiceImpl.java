package com.zclorne.zmall.manage.service.impl;

import com.zclorne.zmall.bean.*;
import com.zclorne.zmall.manage.mapper.PmsSkuAttrValueMapper;
import com.zclorne.zmall.manage.mapper.PmsSkuImageMapper;
import com.zclorne.zmall.manage.mapper.PmsSkuInfoMapper;
import com.zclorne.zmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.zclorne.zmall.service.SkuService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zclorne
 * 2019/9/3 11:17
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;

    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;

    @Override
    public String saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
        String skuInfoId = pmsSkuInfo.getId();

        //保存图片
        List<PmsSkuImage> pmsSkuImageList = pmsSkuInfo.getSkuImageList();
        pmsSkuImageList.forEach(t -> {
            t.setSkuId(skuInfoId);
            pmsSkuImageMapper.insertSelective(t);
        });

        //保存平台属性
        List<PmsSkuAttrValue> pmsSkuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        pmsSkuAttrValueList.forEach(t -> {
            t.setSkuId(skuInfoId);
            pmsSkuAttrValueMapper.insertSelective(t);
        });

        //保存销售属性
        List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        pmsSkuSaleAttrValueList.forEach(t -> {
            t.setSkuId(skuInfoId);
            pmsSkuSaleAttrValueMapper.insertSelective(t);
        });

        return "success";
    }

    @Override
    public PmsSkuInfo getSkuInfoById(String skuId) {

        //sku商品对象
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo info = pmsSkuInfoMapper.selectOne(pmsSkuInfo);

        //sku图片集合
        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> images = pmsSkuImageMapper.select(pmsSkuImage);
        info.setSkuImageList(images);
        return info;
    }

    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId) {
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setProductId(productId);
        List<PmsSkuInfo> infos = pmsSkuInfoMapper.select(pmsSkuInfo);
        infos.forEach(t->{
            PmsSkuSaleAttrValue pmsSkuSaleAttrValue = new PmsSkuSaleAttrValue();
            pmsSkuSaleAttrValue.setSkuId(t.getId());
            List<PmsSkuSaleAttrValue> values = pmsSkuSaleAttrValueMapper.select(pmsSkuSaleAttrValue);
            t.setSkuSaleAttrValueList(values);
        });
        return infos;
    }
}
