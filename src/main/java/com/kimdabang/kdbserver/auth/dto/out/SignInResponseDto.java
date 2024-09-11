package com.kimdabang.kdbserver.auth.dto.out;

import com.kimdabang.kdbserver.auth.vo.out.SignInResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDto {

    private String accessToken;
//    private String refreshToken;
    private String name;

    public SignInResponseVo toResponseVo() {
        return SignInResponseVo.builder()
                .accessToken(accessToken)
                .name(name)
                .build();
    }

}
