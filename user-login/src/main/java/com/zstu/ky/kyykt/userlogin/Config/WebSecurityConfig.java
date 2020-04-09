package com.zstu.ky.kyykt.userlogin.Config;

import com.zstu.ky.kyykt.userlogin.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.naming.AuthenticationException;
import java.net.PasswordAuthentication;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService userDatailService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(CustomUserDetailsService userDatailService,PasswordEncoder passwordEncoder) {
        this.userDatailService = userDatailService;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDatailService).passwordEncoder(passwordEncoder);
    }


}
