package com.zstu.ky.kyykt.userinformation.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zstu.ky.kyykt.userinformation.Entity.DO.UserRole;
import com.zstu.ky.kyykt.userinformation.Entity.VO.StudentUserVO;
import com.zstu.ky.kyykt.userinformation.Mapper.RoleMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.StudentUserMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.UserMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.UserRoleMapper;
import lombok.experimental.var;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentUserService {
    final
    StudentUserMapper studentUserMapper;
    final
    UserMapper userMapper;
    final
    UserRoleMapper userRoleMapper;
    final
    RoleMapper roleMapper;

    final
    PasswordEncoder passwordEncoder;
    public StudentUserService(StudentUserMapper studentUserMapper, UserMapper userMapper, UserRoleMapper userRoleMapper, RoleMapper roleMapper, PasswordEncoder passwordEncoder) {
        this.studentUserMapper = studentUserMapper;
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public StudentUserVO insertTeacherUser(StudentUserVO studentUserVo) throws Exception {
        studentUserVo.getUser().setPassword(passwordEncoder.encode(studentUserVo.getUser().getPassword()));
        System.out.println(studentUserVo.getUser().getId());
        userMapper.insert(studentUserVo.getUser());

        studentUserVo.getStudent().setId(studentUserVo.getUser().getId());
        studentUserMapper.insert(studentUserVo.getStudent());

        UserRole userRole = new UserRole();
        userRole.setRid(3);
        userRole.setUid(studentUserVo.getUser().getId());
        userRoleMapper.insert(userRole);
        return getStudentUser(studentUserVo.getUser().getId());
    }
    public StudentUserVO getStudentUser(Integer id) throws Exception {
        var role = userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("rid",id));
        if(role.getRid()!=2) {
            throw new Exception("This user is not studentUser");
        }
        var user = userMapper.selectById(id);
        var student = studentUserMapper.selectById(id);
        return new StudentUserVO(user,student,roleMapper.selectById(role.getRid()));
    }

}
