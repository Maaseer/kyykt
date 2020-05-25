package com.zstu.ky.kyykt.userinformation.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zstu.ky.RestResult.Result;
import com.zstu.ky.RestResult.ResultCode;
import com.zstu.ky.RestResult.ResultGenerator;
import com.zstu.ky.kyykt.userinformation.Entity.VO.StudentUserVO;
import com.zstu.ky.kyykt.userinformation.Service.StudentUserService;
import io.swagger.annotations.Api;
import lombok.experimental.var;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@RestController
@RequestMapping("/student/user")
@Api("学生用户信息管理")
public class StudentController {
    final
    StudentUserService studentUserService;

    public StudentController(StudentUserService studentUserService) {
        this.studentUserService = studentUserService;
    }

    @GetMapping
    public Result getStudentUsers(Integer userId, String username, String studentName, String sex, String studentClass, String weChatId, String studentTel, Integer index, Integer pageSize, String orderBy, String orderByDesc,HttpServletResponse response ) throws Exception {
        QueryWrapper wrapper = new QueryWrapper();
//        if (studentName != null) {
//            wrapper.like("studentName", "%" + studentName + "%");
//        }
        if (userId != null) {
            wrapper.eq("id", userId);
        }
        if (studentClass != null) {
            wrapper.like("studentClass", studentClass);
        }
        if (sex != null) {
            wrapper.eq("sex", sex);
        }
        if (weChatId != null) {
            wrapper.like("weChatId", weChatId);
        }
        if (studentTel != null) {
            wrapper.like("studentTel", studentTel);
        }

        if (orderBy != null) {
            var orders = orderBy.split("-");
            wrapper.orderByAsc(Arrays.asList(orders));

        }
        if (orderByDesc != null) {
            var orders = orderByDesc.split("-");
            wrapper.orderByAsc(Arrays.asList(orders));
        }
        Page page = new Page<>(index == null ? 1 : index, pageSize == null ? 20 : pageSize);
//        try {
            var data = studentUserService.getStudentUsers(page, wrapper);
            return ResultGenerator.genSuccessResult().setData(data);
//        }catch (Exception e){
//            return ResultGenerator.genFailResult(e.getMessage());
//        }
    }

    @PostMapping
    public Result postUser(@RequestBody StudentUserVO studentUser,HttpServletResponse response) throws Exception {
        if (studentUser == null || studentUser.getUser() == null || studentUser.getStudent() == null) {
            return ResultGenerator.genFailResult("创建用户失败，请检查参数。").setCode(ResultCode.BAD_REQUEST);
        }
        try {
            var result = studentUserService.insertStudentUser(studentUser);
            return ResultGenerator.genSuccessResult(result);
        } catch (Exception e) {
            response.setStatus(500);
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }

    @PutMapping
    public Result putUser(@RequestBody StudentUserVO studentUserVO) {
        System.out.println(studentUserVO);

        if (studentUserVO == null) {
            return ResultGenerator.genFailResult("Data is empty.");
        }
        if (studentUserVO.getStudent() == null || studentUserVO.getUser() == null) {
            return ResultGenerator.genFailResult("Data is defect.");
        }
        try {
            studentUserService.modifyStudentUser(studentUserVO);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }

    @DeleteMapping
    public Result deleteUser(Integer id) {
        if (id == null) {
            return ResultGenerator.genFailResult("Id can not be null.");
        }
        try {
            studentUserService.deleteStudentUser(id);
            return ResultGenerator.genSuccessResult();
        } catch (Exception e) {
            return ResultGenerator.genFailResult(e.getMessage());
        }
    }
}
