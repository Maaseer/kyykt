package com.zstu.ky.kyykt.userlogin.Controller;

import com.zstu.ky.kyykt.userlogin.Entity.AdminUser;
import com.zstu.ky.kyykt.userlogin.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
    final
    private UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }
    @GetMapping("/get-admin")
    AdminUser GetAdminUser(@RequestParam String adminId)
    {
        //return userInfoService.getAdminUser(adminId,adminPassword);
        return userInfoService.getAdminUser(adminId);
    }
}
