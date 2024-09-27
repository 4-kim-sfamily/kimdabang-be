package com.kimdabang.kdbserver.auth.entity;

import com.kimdabang.kdbserver.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@ToString
@Slf4j
@Getter
@NoArgsConstructor
public class AuthUserDetail implements UserDetails {

    private String uuid;
    private String password;
    private String nickname;

    public AuthUserDetail(User user) {
        this.uuid = user.getUuid();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.uuid;
    }

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
