package com.zclorne.zmall.manage.service.impl;

import com.alibaba.fastjson.JSON;
import com.zclorne.zmall.bean.*;
import com.zclorne.zmall.manage.mapper.PmsSkuAttrValueMapper;
import com.zclorne.zmall.manage.mapper.PmsSkuImageMapper;
import com.zclorne.zmall.manage.mapper.PmsSkuInfoMapper;
import com.zclorne.zmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.zclorne.zmall.service.SkuService;
import com.zclorne.zmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    RedisUtil redisUtil;

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
//        PmsSkuInfo info = getPmsSkuInfoByIdFromDb(skuId);
        PmsSkuInfo skuInfo = new PmsSkuInfo();
        //连接缓存
        Jedis jedis = redisUtil.getJedis();

        //查询缓存
        String skuKey = "sku:" + skuId + ":info";
        String skuLock = "sku:" + skuId + ":lock";
        String skuJson = jedis.get(skuKey);

        if (StringUtils.isNotBlank(skuJson)) {
            skuInfo = JSON.parseObject(skuJson, PmsSkuInfo.class);
        } else {


            // 设置分布式锁  过期时间10s
            String OK = jedis.set(skuLock, "1", "nx", "px", 10*1000);

            if (StringUtils.isNotBlank(OK) && OK.equals("OK")) {
                // 获得锁，在10秒内有权访问数据库

                // 缓存中没有，查询数据库
                skuInfo = getPmsSkuInfoByIdFromDb(skuId);

                if (skuInfo != null) {
                    // mysql数据库查询结果存入redis
                    jedis.set(skuKey, JSON.toJSONString(skuInfo));
                } else {
                    // 数据库中不存在该sku
                    // 防止缓存穿透，将null或空字符串设置给redis
                    jedis.setex(skuKey, 60 * 3, JSON.toJSONString(null));
                }

                //释放分布式锁
                jedis.del(skuLock);
            }else {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 获得锁失败，自旋
                return getSkuInfoById(skuId);
            }

        }
        jedis.close();
        return skuInfo;
    }

    private PmsSkuInfo getPmsSkuInfoByIdFromDb(String skuId) {
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
        infos.forEach(t -> {
            PmsSkuSaleAttrValue pmsSkuSaleAttrValue = new PmsSkuSaleAttrValue();
            pmsSkuSaleAttrValue.setSkuId(t.getId());
            List<PmsSkuSaleAttrValue> values = pmsSkuSaleAttrValueMapper.select(pmsSkuSaleAttrValue);
            t.setSkuSaleAttrValueList(values);
        });
        return infos;
    }
}
