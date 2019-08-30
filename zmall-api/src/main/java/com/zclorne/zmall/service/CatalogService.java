package com.zclorne.zmall.service;

import com.zclorne.zmall.bean.PmsBaseCatalog1;
import com.zclorne.zmall.bean.PmsBaseCatalog2;
import com.zclorne.zmall.bean.PmsBaseCatalog3;

import java.util.List;

/**
 * Created by zclorne
 * 2019/8/29 22:16
 */
public interface CatalogService {
    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
