package com.zstu.ky.kyykt.userinformation.Entity.VO;

import com.zstu.ky.kyykt.userinformation.Entity.DO.Role;
import com.zstu.ky.kyykt.userinformation.Entity.DO.StudentUser;
import com.zstu.ky.kyykt.userinformation.Entity.DO.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentUserVO {
    User user;
    StudentUser student;
    Role role;
}
