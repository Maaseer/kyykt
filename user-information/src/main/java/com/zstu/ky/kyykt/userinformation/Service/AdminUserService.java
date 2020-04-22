package com.zstu.ky.kyykt.userinformation.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zstu.ky.kyykt.userinformation.Entity.DO.AdminUser;
import com.zstu.ky.kyykt.userinformation.Entity.DO.UserRole;
import com.zstu.ky.kyykt.userinformation.Entity.VO.AdminUserVO;
import com.zstu.ky.kyykt.userinformation.Mapper.AdminUserMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.RoleMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.UserMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.UserRoleMapper;
import lombok.experimental.var;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserService {
    final
    AdminUserMapper adminUserMapper;
    final
    UserMapper userMapper;
    final
    UserRoleMapper userRoleMapper;
    final
    RoleMapper roleMapper;

    final
    PasswordEncoder passwordEncoder;

    Log log = LogFactory.getLog(AdminUserService.class);
    public AdminUserService(AdminUserMapper adminUserMapper, UserMapper userMapper, UserRoleMapper userRoleMapper, RoleMapper roleMapper, PasswordEncoder passwordEncoder) {
        this.adminUserMapper = adminUserMapper;
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public AdminUserVO insertAdminUser(AdminUserVO adminUserVo) throws Exception {
        //插入user
        adminUserVo.getUser().setPassword(passwordEncoder.encode(adminUserVo.getUser().getPassword()));
        userMapper.insert(adminUserVo.getUser());
        //插入admin
        var admin =  adminUserVo.getAdmin();
        admin.setId(adminUserVo.getUser().getId());
        adminUserMapper.insert(adminUserVo.getAdmin());

        //插入角色
        UserRole userRole = new UserRole();
        userRole.setRid(1);
        userRole.setUid(adminUserVo.getUser().getId());
        userRoleMapper.insert(userRole);

        return getAdminUser(admin.getId());
    }

    public List<AdminUserVO> getAdminUsers() throws Exception {

        LambdaQueryWrapper<AdminUser> wrapper= Wrappers.<AdminUser>lambdaQuery();
        wrapper.like(AdminUser::getAdminName,"%a%");
        var adminResult =  adminUserMapper.selectList(wrapper);
        List<AdminUserVO> results = new ArrayList<>();
        for (AdminUser a : adminResult) {
            results.add(getAdminUser(a.getId()));
        }
        return results;
    }
    public AdminUserVO getAdminUser(Integer id) throws Exception {
        var role = userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("rid",id));
        if(role.getRid()!=1) {
            throw new Exception("This user is not adminUser");
        }
        var user = userMapper.selectById(id);
        var admin = adminUserMapper.selectById(id);
        return new AdminUserVO(user,admin,roleMapper.selectById(role.getRid()));
    }
}
