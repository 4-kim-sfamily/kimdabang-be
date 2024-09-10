package com.kimdabang.kdbserver.auth.presentation;

import com.kimdabang.kdbserver.auth.application.AuthService;
import com.kimdabang.kdbserver.auth.dto.in.*;
import com.kimdabang.kdbserver.auth.dto.out.KeyResponseDto;
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
import org.modelmapper.ModelMapper;
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
        ModelMapper modelMapper = new ModelMapper();
        SignInRequestDto signInRequestDto = SignInRequestDto.builder().
                loginId(signInRequestVo.getLoginId()).
                password(signInRequestVo.getPassword()).
                build();
        SignInResponseVo signInResponseVo = modelMapper.map(authService.signIn(signInRequestDto), SignInResponseVo.class);
        log.info("signInResponseVo: {}", signInResponseVo);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                signInResponseVo);

    }

    @Operation(summary = "join API", description = "join API 입니다.", tags = {"Auth"})
    @PostMapping("/join")
    public CommonResponseEntity<Void> signUp(
            @RequestBody SignUpRequestVo signUpRequestVo) {
        authService.signUp(new ModelMapper().map(signUpRequestVo, SignUpRequestDto.class));
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }

//    @Operation(summary = "kakaojoin API", description = "kakaojoin API 입니다.", tags = {"Auth"})
//    @PostMapping("/kakaologin")
//    public CommonResponseEntity<SignInResponseVo> kakaoLogin(
//            @RequestBody KakaoLoginRequestVo kakaoLoginRequestVo) {
//        ModelMapper modelMapper = new ModelMapper();
//        KakaoLoginRequestDto kakaoLoginRequestDto = KakaoLoginRequestDto.builder().
//                providerAccountId(kakaoLoginRequestVo.getProviderAccountId()).
//                build();
//        SignInResponseVo signInResponseVo = modelMapper.map(authService.kakoLogin(kakaoLoginRequestDto), SignInResponseVo.class);
//        log.info("signInResponseVo: {}", signInResponseVo);
//        return new CommonResponseEntity<>(
//                    HttpStatus.OK,
//                    CommonResponseMessage.SUCCESS.getMessage(),
//                    signInResponseVo);
//
//    }

    @Operation(summary = "findid API", description = "findid API 입니다.", tags = {"Auth"})
    @PostMapping("/findid")
    public CommonResponseEntity<LoginIdFindResponseVo> findId(
            @RequestBody KeyRequestVo keyRequestVo) {
        ModelMapper modelMapper = new ModelMapper();
        KeyRequestDto keyRequestDto = KeyRequestDto.toRequestDto(keyRequestVo);
        LoginIdFindResponseVo loginIdFindResponseVo =modelMapper.map(authService.findEmail(keyRequestDto), LoginIdFindResponseVo.class);
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), loginIdFindResponseVo);
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
        ModelMapper modelMapper = new ModelMapper();
        TestTokenRequestDto testTokenRequestDto = TestTokenRequestDto.builder().
                accessToken(testTokenRequestVo.getAccessToken()).
                build();
        TestTokenResponseVo testTokenResponseVo = modelMapper.map(authService.testToken(testTokenRequestDto), TestTokenResponseVo.class);
        log.info("accessResponseVo : {}", testTokenResponseVo);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                testTokenResponseVo);
    }
}
