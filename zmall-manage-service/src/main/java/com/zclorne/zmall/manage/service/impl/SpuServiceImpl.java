package com.zclorne.zmall.manage.service.impl;

import com.zclorne.zmall.bean.PmsProductImage;
import com.zclorne.zmall.bean.PmsProductInfo;
import com.zclorne.zmall.bean.PmsProductSaleAttr;
import com.zclorne.zmall.bean.PmsProductSaleAttrValue;
import com.zclorne.zmall.manage.mapper.PmsProductImageMapper;
import com.zclorne.zmall.manage.mapper.PmsProductInfoMapper;
import com.zclorne.zmall.manage.mapper.PmsProductSaleAttrMapper;
import com.zclorne.zmall.manage.mapper.PmsProductSaleAttrValueMapper;
import com.zclorne.zmall.service.SpuService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zclorne
 * 2019/8/30 15:55
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;

    @Autowired
    PmsProductImageMapper pmsProductImageMapper;

    @Autowired
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;

    @Autowired
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo = new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        List<PmsProductInfo> select = pmsProductInfoMapper.select(pmsProductInfo);
        return select;
    }

    @Override
    public String saveSpuInfo(PmsProductInfo pmsProductInfo) {
        pmsProductInfoMapper.insertSelective(pmsProductInfo);

        String prodectId = pmsProductInfo.getId();

        //保存图片信息
        List<PmsProductImage> pmsProductImageList = pmsProductInfo.getPmsProductImageList();

        pmsProductImageList.forEach(t -> {
            t.setProductId(prodectId);
            pmsProductImageMapper.insertSelective(t);

        });

        //保存销售属性
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductInfo.getPmsProductSaleAttrList();
        pmsProductSaleAttrList.forEach(t -> {
            t.setProductId(prodectId);
            pmsProductSaleAttrMapper.insertSelective(t);

            //保存销售属性值
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList = t.getPmsProductSaleAttrValueList();
            pmsProductSaleAttrValueList.forEach(e->{
                e.setProductId(prodectId);
                pmsProductSaleAttrValueMapper.insertSelective(e);
            });
        });

//        //保存图片信息
//        List<PmsProductImage> pmsProductImageList = pmsProductInfo.getPmsProductImageList();
//
//        for (PmsProductImage pmsProductImage : pmsProductImageList) {
//            pmsProductImage.setProductId(prodectId);
//            pmsProductImageMapper.insertSelective(pmsProductImage);
//        }

//        //保存销售属性
//        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductInfo.getPmsProductSaleAttrList();
//        for (PmsProductSaleAttr t : pmsProductSaleAttrList) {
//            t.setProductId(prodectId);
//            pmsProductSaleAttrMapper.insertSelective(t);
//
//            //保存销售属性值
//            List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList = t.getPmsProductSaleAttrValueList();
//            for (PmsProductSaleAttrValue e : pmsProductSaleAttrValueList) {
//                e.setProductId(prodectId);
//                pmsProductSaleAttrValueMapper.insertSelective(e);
//            }
//        }
        return "success";
    }

    @Override
    public List<PmsProductImage> pmsProductImageList(String productId) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(productId);
        return pmsProductImageMapper.select(pmsProductImage);
    }

    @Override
    public List<PmsProductSaleAttr> pmsProductSaleAttrList(String productId) {
        PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(productId);
        List<PmsProductSaleAttr> list = pmsProductSaleAttrMapper.select(pmsProductSaleAttr);

        list.forEach(t->{
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(productId);
            pmsProductSaleAttrValue.setSaleAttrId(t.getSaleAttrId());
            t.setPmsProductSaleAttrValueList(pmsProductSaleAttrValueMapper.select(pmsProductSaleAttrValue));
        });
        return list;
    }
}
