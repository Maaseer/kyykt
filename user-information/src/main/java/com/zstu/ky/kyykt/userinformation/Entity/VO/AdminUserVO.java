package com.zstu.ky.kyykt.userinformation.Entity.VO;

import com.zstu.ky.kyykt.userinformation.Entity.DO.AdminUser;
import com.zstu.ky.kyykt.userinformation.Entity.DO.Role;
import com.zstu.ky.kyykt.userinformation.Entity.DO.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserVO {
   private User user;
   private AdminUser admin;
   private Role role;
}
