package com.zstu.ky.kyykt.userlogin.Service;

import com.zstu.ky.kyykt.userlogin.Entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final
    UserInfoService infoService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserInfoService infoService, PasswordEncoder passwordEncoder) {
        this.infoService = infoService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AdminUser adminUser = infoService.getAdminUser(s);
        if(adminUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> authorities = new ArrayList<>(new ArrayList<>(
            Arrays.asList(
                    new SimpleGrantedAuthority("ROLE_ADMIN")
                    //new SimpleGrantedAuthority("ROLE_TEACHER"),
                    //new SimpleGrantedAuthority("ROLE_STUDENT")
            )));
            return new User(adminUser.getAdminName(),passwordEncoder.encode(adminUser.getAdminPassword()),authorities);


    }
}
