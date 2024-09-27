package com.kimdabang.kdbserver.user.application;

import com.kimdabang.kdbserver.user.dto.UserRequestDto;
import com.kimdabang.kdbserver.user.dto.UserResponseDto;

public interface UserService {
    UserResponseDto getUser(String accessToken);
    void putUser(UserRequestDto userRequestDto, String accessToken);

}
