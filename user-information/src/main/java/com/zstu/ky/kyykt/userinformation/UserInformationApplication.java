package com.zstu.ky.kyykt.userinformation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableCircuitBreaker

public class UserInformationApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserInformationApplication.class, args);
    }

}
