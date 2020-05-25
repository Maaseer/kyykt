package com.zstu.ky.kyykt.userinformation.Entity.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Role {
    @TableId
    private Integer id;
    private String name;
    private String nameZh;
}
