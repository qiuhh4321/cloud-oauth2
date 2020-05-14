package com.funtl.myshop.plus.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by IntelliJ IDEA.
 * User: qhh
 * Date: 2020/5/11
 */
@SpringBootApplication
@MapperScan(basePackages = "com.funtl.myshop.plus.provider.mapper")
public class UmsAdminProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UmsAdminProviderApplication.class, args);
    }

}
