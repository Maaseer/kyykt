package com.zstu.ky.kyykt.userlogin.Service;

import com.zstu.ky.kyykt.userlogin.Entity.AdminUser;
import com.zstu.ky.kyykt.userlogin.Mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    final
    private UserInfoMapper userInfo;

    @Autowired
    public UserInfoService(UserInfoMapper userInfo) {
        this.userInfo = userInfo;
    }

//    public AdminUser getAdminUser(String adminId,String adminPassword){
//        return userInfo.selectAdminUser(adminId,adminPassword);
//    }
    public AdminUser getAdminUser(String adminId){
        return userInfo.selectAdminUser(adminId);
    }
}
