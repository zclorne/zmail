package com.zclorne.zmall.manage.mapper;

import com.zclorne.zmall.bean.PmsProductSaleAttr;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zclorne
 * 2019/9/3 9:52
 */
public interface PmsProductSaleAttrMapper extends Mapper<PmsProductSaleAttr> {
    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("skuId") String skuId,@Param("productId") String productId);
}
