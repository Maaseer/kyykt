package com.zstu.ky.kyykt.userinformation.Entity.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserRole {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer uid;
    Integer rid;
}
