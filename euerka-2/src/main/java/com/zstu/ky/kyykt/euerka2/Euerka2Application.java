package com.zstu.ky.kyykt.euerka2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Euerka2Application {


    public static void main(String[] args) {
        SpringApplication.run(Euerka2Application.class, args);
    }

}
