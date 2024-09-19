package com.kimdabang.kdbserver.user.domain;
import com.kimdabang.kdbserver.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@ToString
@NoArgsConstructor
@DynamicUpdate  //특정 칼럼만 update하기 위해 사용
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("고객 UUID")
    @Column(nullable = false, length = 100)
    private String uuid;

    @Comment("고객 로그인아이디")
    @Column(nullable = false, length = 100)
    private String loginId;

    @Comment("고객 비밀번호")
    @Column(nullable = false, length = 100)
    private String password;

    @Comment("고객 이름")
    @Column(nullable = false, length = 100)
    private String name;

    @Comment("고객 이메일")
    @Column(nullable = false, length = 100)
    private String email;

    @Comment("고객 전화번호")
    @Column(nullable = false, length = 30)
    private String phone;

    @Comment("고객 성별")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Comment("양력 음력")
    @Column(nullable = false)
    private Boolean solar;

    @Comment("고객 생년월일")
    @Column(nullable = false, length = 20)
    private Date birth;

    @Comment("고객 별명")
    @Column(length = 100)
    private String nickname;

    @Comment("고객 등급")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Comment("고객 별명")
    @Column(length = 100)
    private String profileImg;



    @Builder
    public User(
            Long id,
            String loginId,
            String uuid,
            String password,
            String name,
            String email,
            String phone,
            Gender gender,
            Boolean solar,
            Date birth,
            String nickname,
            Grade grade,
            String profileImg
    ) {
        this.id = id;
        this.loginId = loginId;
        this.uuid = uuid;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.solar = solar;
        this.birth = birth;
        this.nickname = nickname;
        this.grade = grade;
        this.profileImg = profileImg;
    }


    public void updatePassword(String password){
        this.password = password;
    }

    public void hashPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
        return List.of();
    }

    @Override
    public String getUsername() { return this.uuid;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}