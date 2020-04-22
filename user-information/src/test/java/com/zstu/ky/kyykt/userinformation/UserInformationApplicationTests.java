package com.zstu.ky.kyykt.userinformation;

import com.zstu.ky.kyykt.userinformation.Entity.DO.Role;
import com.zstu.ky.kyykt.userinformation.Entity.DO.TeacherUser;
import com.zstu.ky.kyykt.userinformation.Entity.DO.User;
import com.zstu.ky.kyykt.userinformation.Entity.VO.TeacherUserVO;
import com.zstu.ky.kyykt.userinformation.Service.TeacherUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserInformationApplicationTests {

    @Autowired
    private TeacherUserService teacherUserService;

    @Test
    void contextLoads() throws Exception {
        User user = new User();
        user.setUsername("Xb16620121");
        user.setPassword("q763759021");
        TeacherUser teacherUser = new TeacherUser();
        teacherUser.setTeacherName("Maasteer");
        Role role = new Role();
        role.setId(3);
        TeacherUserVO teacherUserVo = new TeacherUserVO();
        teacherUserVo.setUser(user);
        teacherUserVo.setTeacher(teacherUser);
        teacherUserVo.setRole(role);

        teacherUserService.insertTeacherUser(teacherUserVo);
    }

}
