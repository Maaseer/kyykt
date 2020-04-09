package com.zstu.ky.kyykt.zuul.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @GetMapping("/api/admin/hello")
    public String adminHello()
    {
        return "admin_hello";
    }
    @GetMapping("/api/teacher/hello")
    public String teacherHello(){
        return "teacher_hello";
    }
    @GetMapping("/api/student/hello")
    public String studentHello(){
        return "student_hello";
    }

}
