package com.zstu.ky.kyykt.feignclienttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscoverController {
    @Autowired
    DiscoverRemote discoverRemote;
    @GetMapping("/hello")
    public String hello(){

        return  discoverRemote.test();

    }

}
