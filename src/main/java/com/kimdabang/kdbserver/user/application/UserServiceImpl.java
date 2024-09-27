package com.kimdabang.kdbserver.user.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.user.domain.User;
import com.kimdabang.kdbserver.user.dto.UserRequestDto;
import com.kimdabang.kdbserver.user.dto.UserResponseDto;
import com.kimdabang.kdbserver.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.USER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserResponseDto getUser(String accessToken) {
        User user = userRepository.findByUuid(jwtTokenProvider.useToken(accessToken)).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );
        return UserResponseDto.builder()
                .loginId(user.getLoginId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .solar(user.getSolar())
                .birth(user.getBirth())
                .gender(user.getGender())
                .grade(user.getGrade())
                .nickname(user.getNickname())
                .profileImg(user.getProfileImg())
                .build();
    }

    @Override
    public void putUser(UserRequestDto userRequestDto,String accessToken) {
        User user = userRepository.findByUuid(jwtTokenProvider.useToken(accessToken)).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );

        userRepository.save(userRequestDto.toEntity(userRequestDto, user));
    }
}