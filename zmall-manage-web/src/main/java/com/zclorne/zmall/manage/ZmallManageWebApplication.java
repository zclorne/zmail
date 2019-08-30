package com.zclorne.zmall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ZmallManageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZmallManageWebApplication.class, args);
    }

}
