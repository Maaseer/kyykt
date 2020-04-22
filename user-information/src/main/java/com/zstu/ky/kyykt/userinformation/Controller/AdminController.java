package com.zstu.ky.kyykt.userinformation.Controller;

import com.zstu.ky.kyykt.userinformation.Entity.DO.AdminUser;
import com.zstu.ky.kyykt.userinformation.Entity.VO.AdminUserVO;
import com.zstu.ky.kyykt.userinformation.Mapper.AdminUserMapper;
import com.zstu.ky.kyykt.userinformation.Service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController   {
    private final AdminUserService adminUserService;
    @Autowired
    public AdminController(AdminUserMapper adminUserMapper,AdminUserService adminUserService) {
        this.adminUserService=adminUserService;
    }

    @GetMapping("/getUser")
    public ResponseEntity<Object> getAdminUser(Integer id) throws Exception {
        if(id==null) {
            throw new Exception("Id can not be null.");
        }
        return ResponseEntity.ok(adminUserService.getAdminUser(id));
    }

    @GetMapping("/getUsers")
    public List<AdminUserVO> getAdminUsers() throws Exception {
        return adminUserService.getAdminUsers();
    }
    @PostMapping("/postUser")
    public AdminUserVO postUser(@RequestBody AdminUserVO adminUser) throws Exception {
        if(adminUser == null || adminUser.getUser() == null || adminUser.getAdmin() == null) {
            throw new Exception("Request Body is null.");
        }
        return adminUserService.insertAdminUser(adminUser);
    }
    @PutMapping("/putUser")
    public boolean putUser(AdminUser adminUser){
        //return adminUserService.updateById(adminUser);
        return false;
    }
}
