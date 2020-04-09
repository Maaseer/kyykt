package com.zstu.ky.kyykt.euerkaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
public class EuerkaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EuerkaClientApplication.class, args);
    }

}
