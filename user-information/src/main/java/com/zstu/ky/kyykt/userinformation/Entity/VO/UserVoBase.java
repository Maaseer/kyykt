package com.zstu.ky.kyykt.userinformation.Entity.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVoBase {
    private Integer id;
    private Integer username;
    private String password;
    private Boolean enabled;
    private Boolean locked;
    private String token;

    private Integer roleId;
    private String roleName;
    private String nameZh;
}
