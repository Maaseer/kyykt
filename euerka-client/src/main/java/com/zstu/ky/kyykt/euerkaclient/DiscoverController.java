package com.zstu.ky.kyykt.euerkaclient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoverController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String ip;

    @GetMapping("/client")
    public String client() {
        String services = "Services: " + discoveryClient.getServices()+" ip :"+ip;

        System.out.println(services);
        return services;
    }
    @GetMapping("/test")
    public String test(){
        return "This is eureka client 1.";
    }
}
