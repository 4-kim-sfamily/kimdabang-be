package com.kimdabang.kdbserver.auth.presentation;

import com.kimdabang.kdbserver.auth.application.AuthService;
import com.kimdabang.kdbserver.auth.dto.in.*;
import com.kimdabang.kdbserver.auth.dto.out.KeyResponseDto;
import com.kimdabang.kdbserver.auth.dto.out.LoginIdFindResponseDto;
import com.kimdabang.kdbserver.auth.dto.out.SignInResponseDto;
import com.kimdabang.kdbserver.auth.dto.out.TestTokenResponseDto;
import com.kimdabang.kdbserver.auth.vo.in.*;
import com.kimdabang.kdbserver.auth.vo.out.LoginIdFindResponseVo;
import com.kimdabang.kdbserver.auth.vo.out.KeyResponseVo;
import com.kimdabang.kdbserver.auth.vo.out.SignInResponseVo;
import com.kimdabang.kdbserver.auth.vo.out.TestTokenResponseVo;
import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.common.entity.CommonResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "login API", description = "login API 입니다.", tags = {"Auth"})
    @PostMapping("/login")
    public CommonResponseEntity<SignInResponseVo> signIn(
            @RequestBody SignInRequestVo signInRequestVo) {
        SignInRequestDto signInRequestDto = SignInRequestDto.builder().
                loginId(signInRequestVo.getLoginId()).
                password(signInRequestVo.getPassword()).
                build();
        SignInResponseDto signInResponseDto = authService.signIn(signInRequestDto);
        log.info("signInResponseDto: {}", signInResponseDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                signInResponseDto.toResponseVo());

    }

    @Operation(summary = "join API", description = "join API 입니다.", tags = {"Auth"})
    @PostMapping("/join")
    public CommonResponseEntity<Void> signUp(
            @RequestBody SignUpRequestVo signUpRequestVo) {
        authService.signUp(SignUpRequestDto.toRequestDto(signUpRequestVo));
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }

    @Operation(summary = "OAuth login API", description = "OAuth login API 입니다.", tags = {"OAuth"})
    @PostMapping("/sociallogin")
    public CommonResponseEntity<SignInResponseVo> oAuthSignIn(
            @RequestBody OAuthSignInRequestVo oAuthSignInRequestVo) {
        OAuthSignInRequestDto oAuthSignInRequestDto = OAuthSignInRequestDto.toRequestDto(oAuthSignInRequestVo);
        SignInResponseDto signInResponseDto = authService.oAuthSignIn(oAuthSignInRequestDto);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                signInResponseDto.toResponseVo());
    }

    @Operation(summary = "OAuth join API", description = "OAuth join API 입니다.", tags = {"OAuth"})
    @PostMapping("/socialjoin")
    public CommonResponseEntity<Void> oAuthSignUp(
            @RequestHeader ("Authorization") String Authorization,
            @RequestBody OAuthSignInRequestVo oAuthSignInRequestVo) {
        OAuthSignInRequestDto oAuthSignInRequestDto = OAuthSignInRequestDto.toRequestDto(oAuthSignInRequestVo);
        authService.oAuthSignUp(oAuthSignInRequestDto, Authorization);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(), null);
    }

    @Operation(summary = "OAuth delete API", description = "OAuth delete API 입니다.", tags = {"OAuth"})
    @DeleteMapping("/socialdelete")
    public CommonResponseEntity<Void> oAuthDelete(
            @RequestHeader ("Authorization") String Authorization,
            @RequestBody KeyRequestVo keyRequestVo) {
        KeyRequestDto keyRequestDto = KeyRequestDto.toRequestDto(keyRequestVo);
        authService.oAuthDelete(keyRequestDto, Authorization);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(), null);
    }
    @Operation(summary = "findid API", description = "findid API 입니다.", tags = {"Auth"})
    @PostMapping("/findid")
    public CommonResponseEntity<LoginIdFindResponseVo> findId(
            @RequestBody KeyRequestVo keyRequestVo) {
        KeyRequestDto keyRequestDto = KeyRequestDto.toRequestDto(keyRequestVo);
        LoginIdFindResponseDto loginIdFindResponseDto = authService.findEmail(keyRequestDto);
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), loginIdFindResponseDto.toResponseVo());
    }

    @Operation(summary = "verifypassword API", description = "verifypassword API 입니다.", tags = {"Auth"})
    @PostMapping("/verifypassword")
    public CommonResponseEntity<KeyResponseVo> verifyPassword(
            @RequestHeader ("Authorization") String Authorization,
            @RequestBody KeyRequestVo keyRequestVo) {
        KeyRequestDto keyRequestDto = KeyRequestDto.toRequestDto(keyRequestVo);
        KeyResponseVo keyResponseVo = KeyResponseDto.toResponseVo(authService.verifyPassword(keyRequestDto, Authorization));
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), keyResponseVo);
    }

    @Operation(summary = "verifyloginid API", description = "verifyloginid API 입니다.", tags = {"Auth"})
    @PostMapping("/verifyloginid")
    public CommonResponseEntity<KeyResponseVo> verifyLoginId(
            @RequestBody KeyRequestVo keyRequestVo) {
        KeyRequestDto keyRequestDto = KeyRequestDto.toRequestDto(keyRequestVo);
        KeyResponseVo keyResponseVo = KeyResponseDto.toResponseVo(authService.verifyLoginId(keyRequestDto));
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), keyResponseVo);
    }

    @Operation(summary = "verifyemail API", description = "verifyemail API 입니다.", tags = {"Auth"})
    @PostMapping("/verifyemail")
    public CommonResponseEntity<KeyResponseVo> verifyEmail(
            @RequestBody KeyRequestVo keyRequestVo) {
        KeyRequestDto keyRequestDto = KeyRequestDto.toRequestDto(keyRequestVo);
        KeyResponseVo keyResponseVo = KeyResponseDto.toResponseVo(authService.verifyEmail(keyRequestDto));
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), keyResponseVo);
    }

    @Operation(summary = "putpassword API", description = "putpassword API 입니다.", tags = {"Auth"})
    @PostMapping("/putpassword")
    public CommonResponseEntity<Void> putPassword(
            @RequestHeader ("Authorization") String Authorization,
            @RequestBody KeyRequestVo keyRequestVo) {
        KeyRequestDto keyRequestDto = KeyRequestDto.toRequestDto(keyRequestVo);
        authService.putPassword(keyRequestDto, Authorization);
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }

    @Operation(summary = "TestToken API", description = "토큰 테스트 API 입니다.", tags = {"Auth"})
    @PostMapping("/token-test")
    public CommonResponseEntity<TestTokenResponseVo> testToken(
            @RequestBody TestTokenRequestVo testTokenRequestVo) {
        TestTokenRequestDto testTokenRequestDto = TestTokenRequestDto.builder().
                accessToken(testTokenRequestVo.getAccessToken()).
                build();
        TestTokenResponseDto testTokenResponseDto =  authService.testToken(testTokenRequestDto);
        log.info("accessResponseVo : {}", testTokenResponseDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                testTokenResponseDto.toResponseVo());
    }
}
