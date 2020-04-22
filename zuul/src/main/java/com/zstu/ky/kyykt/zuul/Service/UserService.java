package com.zstu.ky.kyykt.zuul.Service;

import com.zstu.ky.kyykt.zuul.Entity.DO.Role;
import com.zstu.ky.kyykt.zuul.Entity.DO.User;
import com.zstu.ky.kyykt.zuul.Entity.DTO.UserDTO;
import com.zstu.ky.kyykt.zuul.Mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final
    UserMapper userMapper;
    private Logger logger= LoggerFactory.getLogger(UserService.class);
    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUserName(s);
        if(user == null)
        {
            throw new UsernameNotFoundException("账户不存在！");
        }
        List<Role> roles = userMapper.getRoleById(user.getId());
        logger.warn(user.toString());

        return new UserDTO(user,roles);
    }

}
