package com.zstu.ky.kyykt.userinformation;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zstu.ky.kyykt.userinformation.Entity.DO.AdminUser;
import com.zstu.ky.kyykt.userinformation.Service.AdminUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    AdminUserService adminUserService;
    @Test
    void getAdminUser() throws Exception {
        Page page = new Page(1,5);
        LambdaQueryWrapper<AdminUser> wrapper =  Wrappers.<AdminUser>lambdaQuery();
        wrapper.like(AdminUser::getAdminName,"%兔%");

        adminUserService.getAdminUsers(wrapper, page);
    }
}
