package com.zstu.ky.kyykt.feignclienttest;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class DiscoverFallbackFactory implements FallbackFactory<DiscoverRemote> {
    @Override
    public DiscoverRemote create(Throwable throwable) {
        return new DiscoverRemote() {
            @Override
            public String test() {
                return "服务产生了一些错误";
            }
        };
    }
}
