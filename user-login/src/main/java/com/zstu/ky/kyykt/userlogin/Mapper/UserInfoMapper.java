package com.zstu.ky.kyykt.userlogin.Mapper;

import com.zstu.ky.kyykt.userlogin.Entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Mapper
@Component
public interface UserInfoMapper {
//    @Select("select * from adminUser where adminId = #{adminId} and adminPassword = #{adminPassword}")
//    AdminUser selectAdminUser(String adminId,String adminPassword);

    @Select("select * from adminUser where adminId = #{adminId}")
    AdminUser selectAdminUser(String adminId);
}
