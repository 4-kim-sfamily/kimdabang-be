package com.kimdabang.kdbserver.auth.application;

import com.kimdabang.kdbserver.auth.dto.in.*;
import com.kimdabang.kdbserver.auth.dto.out.LoginIdFindResponseDto;
import com.kimdabang.kdbserver.auth.dto.out.KeyResponseDto;
import com.kimdabang.kdbserver.auth.dto.out.SignInResponseDto;
import com.kimdabang.kdbserver.auth.dto.out.TestTokenResponseDto;
import com.kimdabang.kdbserver.auth.infrastructure.AuthRepository;
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

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final AuthRepository authRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void signUp(SignUpRequestDto signUpRequestDto) {
        User user = authRepository.findByLoginId(signUpRequestDto.getLoginId()).orElse(null);
        if (user != null) {
            throw new IllegalArgumentException("이미 가입된 회원입니다.");
        }
        authRepository.save(signUpRequestDto.toEntity(passwordEncoder));
    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        try {
           Authentication authentication =
                   authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequestDto.getLoginId(),
                            signInRequestDto.getPassword()
                    )
           );
           return SignInResponseDto.builder()
                   .accessToken(createToken(authentication))
                   .name(authentication.getName())
                   .build();
        } catch (Exception e) {
            throw new IllegalArgumentException("로그인 실패");
        }
    }

    @Override
    public LoginIdFindResponseDto findEmail(KeyRequestDto keyRequestDto) {
        User user = authRepository.findByEmail(keyRequestDto.getKey()).orElse(null);
        if (user != null) {
            return LoginIdFindResponseDto.builder()
                    .loginId(user.getLoginId())
                    .build();
        }
        throw new IllegalArgumentException("일치하는 이메일이 없습니다");
    }

    @Override
    public void putPassword(KeyRequestDto keyRequestDto, String accessToken) {
        String uuid = jwtTokenProvider.useToken(accessToken);
        User user = authRepository.findByUuid(uuid).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );
        user.updatePassword(passwordEncoder.encode(keyRequestDto.getKey()));
        authRepository.save(user);
    }

    @Override
    public KeyResponseDto verifyPassword(KeyRequestDto keyRequestDto, String accessToken) {
        String uuid = jwtTokenProvider.useToken(accessToken);
        User user = authRepository.findByUuid(uuid).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getLoginId(),
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

//    @Override
//    public SignInResponseDto kakoLogin(KakaoLoginRequestDto kakaoLoginRequestDto) {
//        User user = authRepository.findByKakaoId(kakaoLoginRequestDto.getProviderAccountId()).orElse(null);
//        if (user != null) {
//            try {
//                Authentication authentication =
//                        authenticationManager.authenticate(
//                                new UsernamePasswordAuthenticationToken(
//                                        user.getLoginId(),
//                                        user.getId()
//                                )
//                        );
//                return SignInResponseDto.builder()
//                        .accessToken(createToken(authentication))
//                        .name(user.getName())
//                        .build();
//            } catch (Exception e) {
//                throw new IllegalArgumentException("로그인 실패");
//            }
//        }
//        throw new IllegalArgumentException("카카오 아이디로 가입된 정보가 없습니다");
//    }

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
