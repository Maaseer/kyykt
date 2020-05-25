package com.zstu.ky.kyykt.userinformation.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zstu.ky.kyykt.userinformation.Entity.DO.StudentUser;
import com.zstu.ky.kyykt.userinformation.Entity.DO.UserRole;
import com.zstu.ky.kyykt.userinformation.Entity.DTO.Roles;
import com.zstu.ky.kyykt.userinformation.Entity.VO.StudentUserVO;
import com.zstu.ky.kyykt.userinformation.Mapper.RoleMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.StudentUserMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.UserMapper;
import com.zstu.ky.kyykt.userinformation.Mapper.UserRoleMapper;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    Roles roles;

    public StudentUserService(StudentUserMapper studentUserMapper, UserMapper userMapper, UserRoleMapper userRoleMapper, RoleMapper roleMapper, PasswordEncoder passwordEncoder) {
        this.studentUserMapper = studentUserMapper;
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public StudentUserVO insertStudentUser(StudentUserVO studentUserVo) throws Exception {
        studentUserVo.getUser().setPassword(passwordEncoder.encode(studentUserVo.getUser().getPassword()));
        System.out.println(studentUserVo.getUser().getId());
        userMapper.insert(studentUserVo.getUser());

        studentUserVo.getStudent().setId(studentUserVo.getUser().getId());
        studentUserMapper.insert(studentUserVo.getStudent());

        UserRole userRole = new UserRole();
        userRole.setRid(2);
        userRole.setUid(studentUserVo.getUser().getId());
        userRoleMapper.insert(userRole);
        return getStudentUsers(studentUserVo.getUser().getId());
    }

    public StudentUserVO getStudentUsers(Integer id) throws Exception {
        var role = userRoleMapper.selectOne(new QueryWrapper<UserRole>().eq("uid", id));
        if (role.getRid() != 2) {
            throw new Exception("This user is not studentUser");
        }
        var user = userMapper.selectById(id);
        var student = studentUserMapper.selectById(id);
        return new StudentUserVO(user, student, roleMapper.selectById(role.getRid()));
    }

    public Page getStudentUsers(Page page, Wrapper wrapper) throws Exception {

        var students = studentUserMapper.selectPage(page, wrapper);
        if(students.getTotal() == 0)
        {
            return students;
        }
        List<StudentUserVO> results = new ArrayList<>();

        List<StudentUser> studentsRecords = students.getRecords();


        //使用流 将所有id组成新的数组
        List<Integer> userIds = studentsRecords.stream().map(StudentUser::getId).collect(Collectors.toList());
        var users = userMapper.selectBatchIds(userIds);
        System.out.println(users.size());
        System.out.println(studentsRecords.size());
        for (int i = 0; i < studentsRecords.size(); i++) {
            results.add(new StudentUserVO(users.get(i), studentsRecords.get(i), roles.roles.get(2)));
        }

        students.setRecords(results);
        return students;
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public void modifyStudentUser(StudentUserVO student) {
        userMapper.updateById(student.getUser());
        studentUserMapper.updateById(student.getStudent());
        var userRole = userRoleMapper.selectOne(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUid, student.getUser().getId()));
        userRole.setRid(student.getRole().getId());
        userRoleMapper.updateById(userRole);
    }

    @Transactional(rollbackFor = {RuntimeException.class, Error.class})
    public void deleteStudentUser(Integer id) {
        studentUserMapper.deleteById(id);
        int urid = userRoleMapper.selectOne(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUid, id)).getId();
        userRoleMapper.deleteById(urid);
        userMapper.deleteById(id);
    }


}
