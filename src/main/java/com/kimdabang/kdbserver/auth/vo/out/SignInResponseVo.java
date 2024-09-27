package com.kimdabang.kdbserver.auth.vo.out;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseVo {

    private String accessToken;
//    private String refreshToken;
    private String name;

}
