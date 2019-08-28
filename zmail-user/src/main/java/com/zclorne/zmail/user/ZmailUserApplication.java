package com.zclorne.zmail.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.zclorne.zmail.user.mapper")
public class ZmailUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZmailUserApplication.class, args);
    }

}
