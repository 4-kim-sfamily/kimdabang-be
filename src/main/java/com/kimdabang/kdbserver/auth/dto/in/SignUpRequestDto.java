package com.kimdabang.kdbserver.auth.dto.in;

import com.kimdabang.kdbserver.agreement.domain.Agreement;
import com.kimdabang.kdbserver.auth.entity.OAuth;
import com.kimdabang.kdbserver.auth.vo.in.SignUpRequestVo;
import com.kimdabang.kdbserver.user.domain.Gender;
import com.kimdabang.kdbserver.user.domain.Grade;
import com.kimdabang.kdbserver.user.domain.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SignUpRequestDto {
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

    public static SignUpRequestDto toRequestDto(SignUpRequestVo signUpRequestVo) {
        return SignUpRequestDto.builder()
                .termsChecked(signUpRequestVo.getTermsChecked())
                .privacyChecked(signUpRequestVo.getPrivacyChecked())
                .cardChecked(signUpRequestVo.getCardChecked())
                .emailChecked(signUpRequestVo.getEmailChecked())
                .smsChecked(signUpRequestVo.getSmsChecked())
                .loginId(signUpRequestVo.getLoginId())
                .password(signUpRequestVo.getPassword())
                .provider(signUpRequestVo.getProvider())
                .providerId(signUpRequestVo.getProviderId())
                .name(signUpRequestVo.getName())
                .email(signUpRequestVo.getEmail())
                .phone(signUpRequestVo.getPhone())
                .gender(signUpRequestVo.getGender())
                .solar(signUpRequestVo.getSolar())
                .birth(signUpRequestVo.getBirth())
                .nickname(signUpRequestVo.getNickname())
                .build();

    }

    public User toEntity(PasswordEncoder passwordEncoder) {
        return User.builder()
                .loginId(loginId)
                .uuid(UUID.randomUUID().toString())
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .phone(phone)
                .gender(gender)
                .solar(solar)
                .birth(birth)
                .nickname(nickname)
                .grade(Grade.GRADE_WELCOME)
                .profileImg("기본이미지URL")
                .build();
    }
    public Agreement toAgreement(User user) {
        return Agreement.builder()
                .termsChecked(termsChecked)
                .privacyChecked(privacyChecked)
                .cardChecked(cardChecked)
                .emailChecked(emailChecked)
                .smsChecked(smsChecked)
                .user(user)
                .build();
    }
    public OAuth toOAuth(User user) {
        return OAuth.builder()
                .provider(provider)
                .providerId(providerId)
                .userUuid(user.getUuid())
                .build();
    }

}
