package com.zclorne.zmall.manage.controller;

import com.zclorne.zmall.bean.PmsProductImage;
import com.zclorne.zmall.bean.PmsProductInfo;
import com.zclorne.zmall.bean.PmsProductSaleAttr;
import com.zclorne.zmall.service.SpuService;
import org.apache.dubbo.config.annotation.Reference;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by zclorne
 * 2019/8/30 15:47
 */
@RestController
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;

    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(@RequestParam String catalog3Id) {
        List<PmsProductInfo> pmsProductInfos = spuService.spuList(catalog3Id);
        return pmsProductInfos;
    }

    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo){
        spuService.saveSpuInfo(pmsProductInfo);
        return "success";
    }

    @RequestMapping("pmsProductImageList")
    @ResponseBody
    public List<PmsProductImage> pmsProductImageList(@RequestParam String productId){
        return spuService.pmsProductImageList(productId);
    }

    @RequestMapping("pmsProductSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> pmsProductSaleAttrList(@RequestParam String productId){
        return spuService.pmsProductSaleAttrList(productId);
    }

    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam MultipartFile file) {
        String url = "http://";
        try {
            ClientGlobal.init("fdfs_client.conf");
            url += ClientGlobal.getG_tracker_group().tracker_servers[0].getHostString();
            TrackerClient client = new TrackerClient();

            TrackerServer trackerServer = client.getConnection();

            StorageClient storageClient = new StorageClient(trackerServer, null);

            String originalFilename = file.getOriginalFilename();

            String[] strings = storageClient.upload_file(file.getBytes(), originalFilename.substring(originalFilename.lastIndexOf('.') + 1), null);
            for (String string : strings) {
                url += "/" + string;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }
}
