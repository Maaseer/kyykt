package com.zstu.ky.kyykt.userinformation.Entity.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AdminUser {
    @TableId
    Integer id;
    String adminName;
    String adminTel;

}
