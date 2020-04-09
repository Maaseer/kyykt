package com.zstu.ky.kyykt.eurekaclient2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DicsoverController {
    private final DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String ip;

    @Autowired
    public DicsoverController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/client")
    public String client() {
        String services = "Services: " + discoveryClient.getServices()+" ip :"+ip;

        System.out.println(services);
        return services;
    }
    @GetMapping("/test")
    public String test(){
        return "This is eureka client 2.";
    }
}


