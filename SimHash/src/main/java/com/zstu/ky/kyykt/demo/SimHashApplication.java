package com.zstu.ky.kyykt.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SimHashApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimHashApplication.class, args);
    }

}
