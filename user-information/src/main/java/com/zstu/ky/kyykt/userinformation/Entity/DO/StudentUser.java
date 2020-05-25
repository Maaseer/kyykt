package com.zstu.ky.kyykt.userinformation.Entity.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class StudentUser {
    @TableId
    Integer id;
    String studentName;
    String sex;
    String studentClass;
    String weChatId;
    String profilePhoto;
    String studentTel;

}
