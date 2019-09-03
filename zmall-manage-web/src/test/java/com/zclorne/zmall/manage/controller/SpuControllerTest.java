package com.zclorne.zmall.manage.controller;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by zclorne
 * 2019/9/3 8:22
 */
public class SpuControllerTest {

    @Test
    public void fileUpload() {
        try {
            ClientGlobal.init("fdfs_client.conf");
            System.out.println(ClientGlobal.getG_tracker_group().tracker_servers[0].getHostString());
//            TrackerClient client = new TrackerClient();
//
//            TrackerServer trackerServer = client.getConnection();
//
//            StorageClient storageClient = new StorageClient(trackerServer,null);

//            String[] strings = storageClient.upload_file("d:/a.jpg", "jpg", null);
//            String url = "http://10.0.6.99";
//            for (String string : strings) {
//                url+="/"+string;
//            }
//            System.out.println(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}