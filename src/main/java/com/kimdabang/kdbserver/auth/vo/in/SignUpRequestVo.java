package com.kimdabang.kdbserver.auth.vo.in;

import com.kimdabang.kdbserver.user.domain.Gender;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
public class SignUpRequestVo {

    private Boolean termsChecked;
    private Boolean privacyChecked;
    private Boolean cardChecked;
    private Boolean emailChecked;
    private Boolean smsChecked;
    private String loginId;
    private String password;
    private String provider;
    private String providerId;
    private String name;
    private String email;
    private String phone;
    private Gender gender;
    private Boolean solar;
    private Date birth;
    private String nickname;
}
