package com.zstu.ky.kyykt.userinformation.Entity.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TeacherUser {
    @TableId
    Integer id;
    String teacherName;
    String profilePhoto;
    String weChatId;
    String information;
    String teacherTel;
}
