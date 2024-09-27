package com.kimdabang.kdbserver.user.vo;

import lombok.*;

import java.util.Date;

@ToString
@Getter
public class UserRequestVo {
    private String name;
    private String email;
    private String phone;
    private Boolean solar;
    private Date birth;
    private String nickname;
    private String profileImg;
}
