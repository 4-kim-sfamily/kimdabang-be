package com.kimdabang.kdbserver.user.dto;

import com.kimdabang.kdbserver.user.domain.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserRequestDto {
    private String name;
    private String email;
    private String phone;
    private Boolean solar;
    private Date birth;
    private String nickname;
    private String profileImg;

    public User toEntity(UserRequestDto userRequestDto, User user) {
        return User.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .uuid(user.getUuid())
                .password(user.getPassword())
                .name(userRequestDto.getName())
                .email(userRequestDto.email)
                .phone(userRequestDto.phone)
                .gender(user.getGender())
                .solar(userRequestDto.solar)
                .nickname(userRequestDto.nickname)
                .grade(user.getGrade())
                .profileImg(userRequestDto.profileImg)
                .build();
    }
}
