package com.kimdabang.kdbserver.user.dto;

import com.kimdabang.kdbserver.user.domain.Gender;
import com.kimdabang.kdbserver.user.domain.Grade;
import com.kimdabang.kdbserver.user.vo.UserResponseVo;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserResponseDto {
    private String loginId;
    private String name;
    private String email;
    private String phone;
    private Gender gender;
    private Boolean solar;
    private Date birth;
    private String nickname;
    private Grade grade;
    private String profileImg;
    public UserResponseVo toResponseVo() {
        return UserResponseVo.builder()
                .loginId(loginId)
                .name(name)
                .email(email)
                .phone(phone)
                .gender(gender)
                .solar(solar)
                .birth(birth)
                .nickname(nickname)
                .grade(grade)
                .profileImg(profileImg)
                .build();
    }
}
