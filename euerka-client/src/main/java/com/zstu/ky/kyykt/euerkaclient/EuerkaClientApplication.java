package com.zstu.ky.kyykt.euerkaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication

public class EuerkaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EuerkaClientApplication.class, args);
    }

}
