package com.kimdabang.kdbserver.auth.dto.in;

import com.kimdabang.kdbserver.auth.entity.OAuth;
import com.kimdabang.kdbserver.auth.vo.in.OAuthSignInRequestVo;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OAuthSignInRequestDto {
    private String provider;
    private String providerAccountId;

    public static OAuthSignInRequestDto toRequestDto(OAuthSignInRequestVo oAuthSignInRequestVo) {
        return OAuthSignInRequestDto.builder()
                .provider(oAuthSignInRequestVo.getProvider())
                .providerAccountId(oAuthSignInRequestVo.getProviderAccountId())
                .build();
    }

    public OAuth toEntity(String userUuid) {
        return OAuth.builder()
                .provider(provider)
                .providerId(providerAccountId)
                .userUuid(userUuid)
                .build();
    }
}
