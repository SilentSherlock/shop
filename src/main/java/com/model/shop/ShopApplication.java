package com.model.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.model.shop.dao")
public class ShopApplication extends SpringBootServletInitializer {

    //用于在线部署访问
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ShopApplication.class);
    }

    //本地启动
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

}
