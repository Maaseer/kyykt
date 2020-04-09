package com.zstu.ky.kyykt.feignclienttest;

import org.springframework.stereotype.Component;

//@Component
public class DiscoverRemoteHystrix implements DiscoverRemote {
    @Override
    public String test() {
        return "test sever is missing.";
    }
}
