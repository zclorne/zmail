package com.zclorne.zmall.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.zclorne.zmall.*.mapper")
@ComponentScan(basePackages = "com.zclorne.zmall")
public class ZmallManageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZmallManageServiceApplication.class, args);
    }

}
