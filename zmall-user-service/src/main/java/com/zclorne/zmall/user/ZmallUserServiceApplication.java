package com.zclorne.zmall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.zclorne.zmall.user.mapper")
public class ZmallUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZmallUserServiceApplication.class, args);
    }

}
