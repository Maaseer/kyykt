package com.zstu.ky.kyykt.userinformation.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zstu.ky.kyykt.userinformation.Entity.DO.TeacherUser;
import com.zstu.ky.kyykt.userinformation.Entity.DO.UserRole;
import com.zstu.ky.kyykt.userinformation.Entity.DTO.Roles;
import com.zstu.ky.kyykt.userinformation.Entity.VO.TeacherUserVO;
import com.zstu.ky.kyykt.userinformation.Mapper.RoleMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.TeacherUserMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.UserMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.UserRoleMapper;
import lombok.experimental.var;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherUserService {
    final
    TeacherUserMapper teacherMapper;
    final
    UserMapper userMapper;
    final
    UserRoleMapper userRoleMapper;
    final
    RoleMapper roleMapper;
    final
    PasswordEncoder passwordEncoder;
    final
    Roles roles;

    public TeacherUserService(TeacherUserMapper teacherUserMapper, UserMapper userMapper, UserRoleMapper userRoleMapper, RoleMapper roleMapper, PasswordEncoder passwordEncoder, Roles roles) {
        this.teacherMapper = teacherUserMapper;
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
        this.roles = roles;
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public TeacherUserVO insertTeacherUser(TeacherUserVO teacherUserVo) throws Exception {
        teacherUserVo.getUser().setPassword(passwordEncoder.encode(teacherUserVo.getUser().getPassword()));
        System.out.println(teacherUserVo.getUser().getId());
        userMapper.insert(teacherUserVo.getUser());

        teacherUserVo.getTeacher().setId(teacherUserVo.getUser().getId());
        teacherMapper.insert(teacherUserVo.getTeacher());

        UserRole userRole = new UserRole();
        userRole.setRid(3);
        userRole.setUid(teacherUserVo.getUser().getId());
        userRoleMapper.insert(userRole);
        return getTeacherUser(teacherUserVo.getUser().getId());
    }

    public TeacherUserVO getTeacherUser(Integer id) throws Exception {
        var role = userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("uid", id));
        if (role.getRid() != 2) {
            throw new Exception("This user is not teacherUser");
        }
        var user = userMapper.selectById(id);
        var teacher = teacherMapper.selectById(id);
        return new TeacherUserVO(user, teacher, roleMapper.selectById(role.getRid()));
    }

    @Transactional
    public void modifyTeacherUser(TeacherUserVO teacherUserVO) {
        userMapper.updateById(teacherUserVO.getUser());
        teacherMapper.updateById(teacherUserVO.getTeacher());
        var userRole = userRoleMapper.selectOne(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUid, teacherUserVO.getUser().getId()));
        userRole.setRid(teacherUserVO.getRole().getId());
        userRoleMapper.updateById(userRole);
    }

    @Transactional
    public void deleteTeacherUser(Integer id) {
        userRoleMapper.deleteById(id);
        teacherMapper.deleteById(id);
        int urid = userRoleMapper.selectOne(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUid, id)).getId();
        userRoleMapper.deleteById(urid);
    }

    public Page getAdminUsers(Wrapper wrapper, Page page) {

        var teacherResults = teacherMapper.selectPage(page, wrapper);

        List<TeacherUserVO> results = new ArrayList<>();

        List<TeacherUser> teacherUsers = teacherResults.getRecords();
        //使用流 将所有id组成新的数组
        List<Integer> userIds = teacherUsers.stream().map(TeacherUser::getId).collect(Collectors.toList());
        var users = userMapper.selectBatchIds(userIds);
        for (int i = 0; i < teacherUsers.size(); i++) {
            results.add(new TeacherUserVO(users.get(i), teacherUsers.get(i), roles.roles.get(1)));
        }

        teacherResults.setRecords(results);
        return teacherResults;
    }

}
