package com.kimdabang.kdbserver.common.provider;

import com.kimdabang.kdbserver.auth.application.OAuthUserDetailService;
import com.kimdabang.kdbserver.auth.entity.AuthUserDetail;
import com.kimdabang.kdbserver.common.entity.OAuthAuthenticationToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@Primary
public class OAuthAuthenticationProvider implements AuthenticationProvider {
    
    private final OAuthUserDetailService authUserDetailService;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof OAuthAuthenticationToken)) {
            return null; // OAuthAuthenticationToken만 지원
        }

        String uuid = authentication.getName();
        log.info("OAuthuuid: {}", uuid);

        AuthUserDetail authUserDetail = (AuthUserDetail) authUserDetailService.loadUserByUsername(uuid);
        return new UsernamePasswordAuthenticationToken(authUserDetail, null, authUserDetail.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OAuthAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
