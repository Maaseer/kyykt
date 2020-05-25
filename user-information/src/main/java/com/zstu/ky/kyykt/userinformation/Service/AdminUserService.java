package com.zstu.ky.kyykt.userinformation.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zstu.ky.kyykt.userinformation.Entity.DO.AdminUser;
import com.zstu.ky.kyykt.userinformation.Entity.DO.UserRole;
import com.zstu.ky.kyykt.userinformation.Entity.DTO.Roles;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    final
    Roles roles;


    Log log = LogFactory.getLog(AdminUserService.class);

    public AdminUserService(AdminUserMapper adminUserMapper, UserMapper userMapper, UserRoleMapper userRoleMapper, RoleMapper roleMapper, PasswordEncoder passwordEncoder, Roles roles) {
        this.adminUserMapper = adminUserMapper;
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
        this.roles = roles;
    }

    @Transactional
    public void insertAdminUser(AdminUserVO... adminUserVos) {
        for (AdminUserVO adminUserVO : adminUserVos) {
            adminUserVO.getUser().setPassword(passwordEncoder.encode(adminUserVO.getUser().getPassword()));
            userMapper.insert(adminUserVO.getUser());
            //插入admin
            var admin = adminUserVO.getAdmin();
            admin.setId(adminUserVO.getUser().getId());
            adminUserMapper.insert(adminUserVO.getAdmin());
            //插入角色
            UserRole userRole = new UserRole();
            userRole.setRid(1);
            userRole.setUid(adminUserVO.getUser().getId());

            userRoleMapper.insert(userRole);
        }
    }

    public Page getAdminUsers(Wrapper wrapper, Page page) {

        var adminResult = adminUserMapper.selectPage(page, wrapper);
        List<AdminUser> adminUserList = adminResult.getRecords();
        if(adminUserList.size()==0) {
            return adminResult;
        }

        List results = new ArrayList<>();

        //使用流 将所有id组成新的数组
        List<Integer> userIds = adminUserList.stream().map(AdminUser::getId).collect(Collectors.toList());
        var users = userMapper.selectBatchIds(userIds);
        for (int i = 0; i < adminUserList.size(); i++) {
            results.add(new AdminUserVO(users.get(i), adminUserList.get(i), roles.roles.get(1)));
        }

        adminResult.setRecords(results);
        return adminResult;
    }

    @Transactional
    public void modifyAdminUser(AdminUserVO adminUserVO) {
        userMapper.updateById(adminUserVO.getUser());
        adminUserMapper.updateById(adminUserVO.getAdmin());
        var userRole = userRoleMapper.selectOne(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUid, adminUserVO.getUser().getId()));
        userRole.setRid(adminUserVO.getRole().getId());
        userRoleMapper.updateById(userRole);
    }

    @Transactional
    public void deleteAdminUser(Integer... id) throws Exception {
        var ids = Arrays.asList(id);
        if(adminUserMapper.selectBatchIds(ids).size()!=id.length) {
            throw new Exception("Some AdminUser Not Found.");
        }

        var urid = userRoleMapper.selectList(new QueryWrapper<UserRole>().in("uid",ids)).stream().map(UserRole::getId).collect(Collectors.toList());
        adminUserMapper.deleteBatchIds(ids);
        userRoleMapper.deleteBatchIds(urid);
        userMapper.deleteBatchIds(ids);
    }
}
