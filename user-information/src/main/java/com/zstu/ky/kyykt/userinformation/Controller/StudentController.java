package com.zstu.ky.kyykt.userinformation.Controller;

import com.zstu.ky.kyykt.userinformation.Entity.VO.StudentUserVO;
import com.zstu.ky.kyykt.userinformation.Service.StudentUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    final
    StudentUserService studentUserService;

    public StudentController(StudentUserService studentUserService) {
        this.studentUserService = studentUserService;
    }

    @GetMapping("/getStudent")
    public StudentUserVO getStudent(Integer id) throws Exception {
        if(id==null) {
            throw new Exception("Id can not be null.");
        }
        return studentUserService.getStudentUser(id);
    }
}
