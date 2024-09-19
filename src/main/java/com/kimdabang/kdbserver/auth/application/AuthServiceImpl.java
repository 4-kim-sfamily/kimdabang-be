package com.kimdabang.kdbserver.auth.application;

import com.kimdabang.kdbserver.agreement.infrastructure.AgreementRepository;
import com.kimdabang.kdbserver.auth.dto.in.*;
import com.kimdabang.kdbserver.auth.dto.out.LoginIdFindResponseDto;
import com.kimdabang.kdbserver.auth.dto.out.KeyResponseDto;
import com.kimdabang.kdbserver.auth.dto.out.SignInResponseDto;
import com.kimdabang.kdbserver.auth.dto.out.TestTokenResponseDto;
import com.kimdabang.kdbserver.auth.entity.OAuth;
import com.kimdabang.kdbserver.auth.infrastructure.AuthRepository;
import com.kimdabang.kdbserver.auth.infrastructure.OAuthRepository;
import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthRepository authRepository;
    private final OAuthRepository oAuthRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AgreementRepository agreementRepository;


    @Override
    public void signUp(SignUpRequestDto signUpRequestDto) {
        User findUser = authRepository.findByLoginId(signUpRequestDto.getLoginId()).orElse(null);
        if (findUser != null) {
            throw new CustomException(DUPLICATED_LOGIN_ID);
        }
        User user = authRepository.save(signUpRequestDto.toEntity(passwordEncoder));
        agreementRepository.save(signUpRequestDto.toAgreement(user));
        log.info("signUpRequestDto.getProvider():{}",signUpRequestDto.getProvider());
        if(!signUpRequestDto.getProvider().isEmpty()) {
            oAuthRepository.save(signUpRequestDto.toOAuth(user));
        }
    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {

        User findUser = authRepository.findByLoginId(signInRequestDto.getLoginId()).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );
        try {
           Authentication authentication =
                   authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            findUser.getUuid(),
                            signInRequestDto.getPassword()
                    )
           );
           return SignInResponseDto.builder()
                   .accessToken(createToken(authentication))
                   .name(findUser.getNickname())
                   .build();
        } catch (Exception e) {
            throw new CustomException(BAD_CREDENTIALS);
        }
    }

    @Override
    public LoginIdFindResponseDto findEmail(KeyRequestDto keyRequestDto) {
        User user = authRepository.findByEmail(keyRequestDto.getKey()).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );
        return LoginIdFindResponseDto.builder()
                    .loginId(user.getLoginId())
                    .build();
    }

    @Override
    public void putPassword(KeyRequestDto keyRequestDto, String accessToken) {
        String uuid = jwtTokenProvider.useToken(accessToken);
        User user = authRepository.findByUuid(uuid).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );
        user.updatePassword(passwordEncoder.encode(keyRequestDto.getKey()));
        authRepository.save(user);
    }

    @Override
    public KeyResponseDto verifyPassword(KeyRequestDto keyRequestDto, String accessToken) {
        String uuid = jwtTokenProvider.useToken(accessToken);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            uuid,
                            keyRequestDto.getKey()
                            )
                    );
            return KeyResponseDto.builder()
                    .verification(true)
                    .build();
        } catch (Exception e) {
            return KeyResponseDto.builder()
                    .verification(false)
                    .build();
        }
    }

    @Override
    public KeyResponseDto verifyEmail(KeyRequestDto keyRequestDto) {
        Optional<User> user = authRepository.findByEmail(keyRequestDto.getKey());
        if(user.isPresent()) {
            return KeyResponseDto.builder()
                .verification(false)
                .build();
        }
        else {
                return KeyResponseDto.builder()
                    .verification(true)
                    .build();
            }
        }
    @Override
    public KeyResponseDto verifyLoginId(KeyRequestDto keyRequestDto) {
        Optional<User> user = authRepository.findByLoginId(keyRequestDto.getKey());

        if(user.isPresent()) {
            return KeyResponseDto.builder()
                    .verification(false)
                    .build();
        }
        else {
            return KeyResponseDto.builder()
                    .verification(true)
                    .build();
        }
    }

    @Override
    public SignInResponseDto oAuthSignIn(OAuthSignInRequestDto oAuthSignInRequestDto) {

        OAuth oAuth = oAuthRepository.findByProviderAndProviderId(oAuthSignInRequestDto.getProvider(), oAuthSignInRequestDto.getProviderAccountId()).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );
        User user = authRepository.findByUuid(oAuth.getUserUuid()).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );
        String token = createToken(oAuthAuthenticate(oAuth.getUserUuid()));

        return SignInResponseDto.builder().accessToken(token).name(user.getName()).build();

    }
    private Authentication oAuthAuthenticate(String uuid) {
        log.info("uuid : {}", uuid);
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        uuid,
                        null
                )
        );
    }
    @Override
    public void oAuthSignUp(OAuthSignInRequestDto oAuthSignInRequestDto, String accessToken) {
        String uuid = jwtTokenProvider.useToken(accessToken);
        User user = authRepository.findByUuid(uuid).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );
        oAuthRepository.findByProviderAndUserUuid(oAuthSignInRequestDto.getProvider(), uuid)
                .ifPresentOrElse( (existingUser) -> {
                    throw new CustomException(BAD_USER_REQUEST);
                }, () -> {
                    oAuthRepository.save(oAuthSignInRequestDto.toEntity(user.getUuid()));
                }
        );
    }

    @Override
    public void oAuthDelete(KeyRequestDto keyRequestDto, String accessToken) {
        String uuid = jwtTokenProvider.useToken(accessToken);

        OAuth oAuth =  oAuthRepository.findByProviderAndUserUuid(keyRequestDto.getKey(), uuid).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );

        oAuthRepository.delete(oAuth);

    }

    @Override
    public TestTokenResponseDto testToken(TestTokenRequestDto testTokenRequestDto) {
        String uuid = jwtTokenProvider.useToken(testTokenRequestDto.getAccessToken());
        return TestTokenResponseDto.builder()
                .uuid(uuid).build();
    }

    private String createToken(Authentication authentication) {
        return jwtTokenProvider.generateAccessToken(authentication);
        //return jwtTokenProvider.generateAccessToken(uuid);
    }

}
