package com.zstu.ky.kyykt.userinformation;

import com.zstu.ky.kyykt.userinformation.Entity.DO.AdminUser;
import com.zstu.ky.kyykt.userinformation.Entity.DO.Role;
import com.zstu.ky.kyykt.userinformation.Entity.DO.User;
import com.zstu.ky.kyykt.userinformation.Entity.VO.AdminUserVO;
import com.zstu.ky.kyykt.userinformation.Service.AdminUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserInformationApplicationTests {

    @Autowired
    private AdminUserService adminUserService;

    @Test
    void contextLoads() throws Exception {
        User user = new User();
        user.setUsername("Xb16620124");
        user.setPassword("q763759021");
        AdminUser adminUser = new AdminUser();
        adminUser.setAdminName("咩兔叽");
        Role role = new Role();

        AdminUserVO adminUserVO = new AdminUserVO();
        adminUserVO.setUser(user);
        adminUserVO.setAdmin(adminUser);
        adminUserVO.setRole(role);

        adminUserService.insertAdminUser(adminUserVO);
    }

}
