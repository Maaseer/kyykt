package com.zstu.ky.kyykt.userinformation.Entity.DO;

import lombok.Data;

@Data
public class TeacherUser {
    Integer id;
    String teacherName;
    String profilePhoto;
    String weChatId;
    String information;
    String teacherTel;
}
