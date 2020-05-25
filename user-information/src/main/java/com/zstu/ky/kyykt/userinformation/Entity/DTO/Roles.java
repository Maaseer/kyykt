package com.zstu.ky.kyykt.userinformation.Entity.DTO;

import com.zstu.ky.kyykt.userinformation.Entity.DO.Role;
import com.zstu.ky.kyykt.userinformation.Mapper.RoleMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Roles {
    final
    RoleMapper roleMapper;

    public Roles(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
        roles = roleMapper.selectList(null);
        roles.add(0,null);
    }
    public List<Role> roles;

}
