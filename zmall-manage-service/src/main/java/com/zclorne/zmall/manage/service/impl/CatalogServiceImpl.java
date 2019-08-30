package com.zclorne.zmall.manage.service.impl;

import com.zclorne.zmall.bean.PmsBaseCatalog1;
import com.zclorne.zmall.bean.PmsBaseCatalog2;
import com.zclorne.zmall.bean.PmsBaseCatalog3;
import com.zclorne.zmall.manage.mapper.PmsBaseCatalog1Mapper;
import com.zclorne.zmall.manage.mapper.PmsBaseCatalog2Mapper;
import com.zclorne.zmall.manage.mapper.PmsBaseCatalog3Mapper;

import com.zclorne.zmall.service.CatalogService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zclorne
 * 2019/8/29 22:17
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;
    @Autowired
    PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;
    @Autowired
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;

    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        return pmsBaseCatalog1Mapper.selectAll();
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        PmsBaseCatalog2 pmsBaseCatalog2 = new PmsBaseCatalog2();
        pmsBaseCatalog2.setCatalog1Id(catalog1Id);
        List<PmsBaseCatalog2> list = pmsBaseCatalog2Mapper.select(pmsBaseCatalog2);
        return list;
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        PmsBaseCatalog3 pmsBaseCatalog3 = new PmsBaseCatalog3();
        pmsBaseCatalog3.setCatalog2Id(catalog2Id);
        List<PmsBaseCatalog3> list = pmsBaseCatalog3Mapper.select(pmsBaseCatalog3);
        return list;
    }
}
