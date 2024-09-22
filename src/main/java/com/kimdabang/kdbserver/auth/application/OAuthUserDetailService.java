package com.kimdabang.kdbserver.auth.application;

import com.kimdabang.kdbserver.auth.infrastructure.AuthRepository;
import com.kimdabang.kdbserver.auth.entity.AuthUserDetail;
import com.kimdabang.kdbserver.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class OAuthUserDetailService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        return new AuthUserDetail(authRepository.findByUuid(uuid).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        ));
    }
}
