package com.zstu.ky.kyykt.zuul.Service;

import com.zstu.ky.kyykt.zuul.Entity.DO.Role;
import com.zstu.ky.kyykt.zuul.Entity.DO.User;
import com.zstu.ky.kyykt.zuul.Entity.DTO.UserDTO;
import com.zstu.ky.kyykt.zuul.Mapper.UserMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserService implements UserDetailsService {
    private final
    UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUserName(s);
        if(user == null)
        {
            throw new UsernameNotFoundException("账户不存在！");
        }
        List<Role> roles = userMapper.getRoleById(user.getId());

        return new UserDTO(user,roles);
    }

}
