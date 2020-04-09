package com.zstu.ky.kyykt.zuul.Mapper;

import com.zstu.ky.kyykt.zuul.Entity.DO.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    User loadUserByUserName(String userName);
    List<Role> getRoleById(int id);
    AdminUser getAdminUserById(int id);
    StudentUser getStudentUserById(int id);
    TeacherUser getTeacherUserById(int id);
}
