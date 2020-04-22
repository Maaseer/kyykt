package com.zstu.ky.kyykt.userinformation.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zstu.ky.kyykt.userinformation.Entity.DO.UserRole;
import com.zstu.ky.kyykt.userinformation.Entity.VO.TeacherUserVO;
import com.zstu.ky.kyykt.userinformation.Mapper.RoleMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.TeacherUserMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.UserMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.UserRoleMapper;
import lombok.experimental.var;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeacherUserService {
    final
    TeacherUserMapper teacherUserMapper;
    final
    UserMapper userMapper;
    final
    UserRoleMapper userRoleMapper;
    final
    RoleMapper roleMapper;

    final
    PasswordEncoder passwordEncoder;
    public TeacherUserService(TeacherUserMapper teacherUserMapper, UserMapper userMapper, UserRoleMapper userRoleMapper, RoleMapper roleMapper, PasswordEncoder passwordEncoder) {
        this.teacherUserMapper = teacherUserMapper;
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public TeacherUserVO insertTeacherUser(TeacherUserVO teacherUserVo) throws Exception {
        teacherUserVo.getUser().setPassword(passwordEncoder.encode(teacherUserVo.getUser().getPassword()));
        System.out.println(teacherUserVo.getUser().getId());
        userMapper.insert(teacherUserVo.getUser());

        teacherUserVo.getTeacher().setId(teacherUserVo.getUser().getId());
        teacherUserMapper.insert(teacherUserVo.getTeacher());

        UserRole userRole = new UserRole();
        userRole.setRid(2);
        userRole.setUid(teacherUserVo.getUser().getId());
        userRoleMapper.insert(userRole);
        return getTeacherUser(teacherUserVo.getUser().getId());
    }
    public TeacherUserVO getTeacherUser(Integer id) throws Exception {
        var role = userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("rid",id));
        if(role.getRid()!=3) {
            throw new Exception("This user is not teacherUser");
        }
        var user = userMapper.selectById(id);
        var teacher = teacherUserMapper.selectById(id);
        return new TeacherUserVO(user,teacher,roleMapper.selectById(role.getRid()));
    }
}
