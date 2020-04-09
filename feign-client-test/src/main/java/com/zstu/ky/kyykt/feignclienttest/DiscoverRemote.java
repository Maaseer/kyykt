package com.zstu.ky.kyykt.feignclienttest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="eureka-client",fallbackFactory = DiscoverFallbackFactory.class)
public interface DiscoverRemote {
    @RequestMapping(value = "/test")
    String test();
}
