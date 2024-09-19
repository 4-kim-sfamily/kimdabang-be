package com.kimdabang.kdbserver.auth.application;

import com.kimdabang.kdbserver.auth.dto.in.*;
import com.kimdabang.kdbserver.auth.dto.out.LoginIdFindResponseDto;
import com.kimdabang.kdbserver.auth.dto.out.KeyResponseDto;
import com.kimdabang.kdbserver.auth.dto.out.SignInResponseDto;
import com.kimdabang.kdbserver.auth.dto.out.TestTokenResponseDto;

public interface AuthService {

    void signUp(SignUpRequestDto signUpRequestDto);
    SignInResponseDto signIn(SignInRequestDto signInRequestDto);
    TestTokenResponseDto testToken(TestTokenRequestDto testTokenRequestDto);
    void putPassword(KeyRequestDto keyRequestDto, String accessToken);
    KeyResponseDto verifyPassword(KeyRequestDto keyRequestDto, String accessToken);
    LoginIdFindResponseDto findEmail(KeyRequestDto keyRequestDto);
    KeyResponseDto verifyEmail(KeyRequestDto keyRequestDto);
    KeyResponseDto verifyLoginId(KeyRequestDto keyRequestDto);
    SignInResponseDto oAuthSignIn(OAuthSignInRequestDto oAuthSignInRequestDto);
    void oAuthSignUp(OAuthSignInRequestDto oAuthSignInRequestDto, String accessToken);
    void oAuthDelete(OAuthSignInRequestDto oAuthSignInRequestDto, String accessToken);
}
