package com.zstu.ky.kyykt.userinformation.Entity.VO;

import com.zstu.ky.kyykt.userinformation.Entity.DO.Role;
import com.zstu.ky.kyykt.userinformation.Entity.DO.TeacherUser;
import com.zstu.ky.kyykt.userinformation.Entity.DO.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherUserVO {
    User user;
    TeacherUser teacher;
    Role role;
}
