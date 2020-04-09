package com.zstu.ky.kyykt.zuul.Entity.DO;

import lombok.Data;

@Data
public class User   {
    private int id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean locked;
    private String token;

}
