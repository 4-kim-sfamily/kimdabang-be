package com.kimdabang.kdbserver.user.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.common.entity.CommonResponseMessage;
import com.kimdabang.kdbserver.user.dto.UserRequestDto;
import com.kimdabang.kdbserver.user.dto.UserResponseDto;
import com.kimdabang.kdbserver.user.infrastructure.UserRepository;
import com.kimdabang.kdbserver.user.vo.UserRequestVo;
import com.kimdabang.kdbserver.user.application.UserService;

import com.kimdabang.kdbserver.user.vo.UserResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Operation(summary = "UserGet API", description = "UserGet API 입니다.", tags = {"user-controller"})
    @GetMapping("/get-user")
    public CommonResponseEntity<UserResponseVo> getUser(
            @RequestHeader ("Authorization") String authorization) {
        UserResponseDto userResponseDto = userService.getUser(authorization);
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), userResponseDto.toResponseVo());
    }

    @Operation(summary = "UserPut API", description = "UserPut API 입니다.", tags = {"user-controller"})
    @PutMapping("/put-user")
    public CommonResponseEntity<Void> putUser(
            @RequestHeader ("Authorization") String authorization,
            @RequestBody UserRequestVo userRequestVo) {
            userService.putUser(new ModelMapper().map(userRequestVo, UserRequestDto.class), authorization);
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }
}
