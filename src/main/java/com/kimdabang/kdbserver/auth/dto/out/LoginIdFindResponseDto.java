package com.kimdabang.kdbserver.auth.dto.out;

import com.kimdabang.kdbserver.auth.vo.out.LoginIdFindResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginIdFindResponseDto {
    private String loginId;

    public LoginIdFindResponseVo toResponseVo() {
        return LoginIdFindResponseVo.builder()
                .loginId(loginId)
                .build();
    }
}
