package com.zstu.ky.kyykt.eurekacustomer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    private final
    RestTemplate restTemplate;

    @Autowired
    public TestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/consumer")
    public String getMsg() {
        return restTemplate.getForObject("http://eureka-client/client", String.class) + " --------- " + restTemplate.getForObject("http://eureka-client/test", String.class);
    }
}
