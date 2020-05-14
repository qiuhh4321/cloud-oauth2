package com.funtl.myshop.plus.business;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * Created by IntelliJ IDEA.
 * User: qhh
 * Date: 2020/5/14
 * @author 邱豪辉
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BusinessProfileApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessProfileApplication.class, args);
    }
}
