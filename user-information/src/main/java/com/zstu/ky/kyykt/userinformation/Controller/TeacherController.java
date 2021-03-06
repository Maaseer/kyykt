package com.zstu.ky.kyykt.userinformation.Controller;

import com.zstu.ky.kyykt.userinformation.Entity.VO.TeacherUserVO;
import com.zstu.ky.kyykt.userinformation.Service.TeacherUserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
@Api("教师用户信息管理")
public class TeacherController {
    final
    TeacherUserService service;

    public TeacherController(TeacherUserService service) {
        this.service = service;
    }

    @GetMapping("/getUser")
    public TeacherUserVO getUser(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("Id can not be null.");
        }
        return service.getTeacherUser(id);
    }
}
