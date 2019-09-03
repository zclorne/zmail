package com.zclorne.zmall.manage.service.impl;

import com.zclorne.zmall.bean.PmsBaseSaleAttr;
import com.zclorne.zmall.manage.mapper.PmsBaseSaleAttrMapper;
import com.zclorne.zmall.service.SaleAttrService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zclorne
 * 2019/9/2 20:48
 */
@Service
public class SaleAttrServiceImpl implements SaleAttrService
{
    @Autowired
    private PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return pmsBaseSaleAttrMapper.selectAll();
    }
}
