package com.zstu.ky.kyykt.zuul.Entity.DTO;

import com.zstu.ky.kyykt.zuul.Entity.DO.Role;
import com.zstu.ky.kyykt.zuul.Entity.DO.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDTO implements UserDetails {
    private int id;
    private String username;
    private String password;
    private boolean enabled;
    private boolean locked;
    private List<Role> roles;
    private List<SimpleGrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }
    public UserDTO(User user, List<Role> roles) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.locked = user.isLocked();
        this.roles = roles;

        authorities = new ArrayList<>();
        roles.forEach(s-> authorities.add(new SimpleGrantedAuthority(s.getName())));
    }



    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return  true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setEnable(boolean enable) {
        this.enabled = enable;
    }


    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
