package com.kimdabang.kdbserver.auth.application;

import com.kimdabang.kdbserver.user.infrastructure.UserRepository;
import com.kimdabang.kdbserver.auth.entity.AuthUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        return new AuthUserDetail(userRepository.findByUuid(uuid).orElseThrow(() -> new UsernameNotFoundException(uuid)));
    }

}
