package com.kimdabang.kdbserver.auth.dto.out;

import com.kimdabang.kdbserver.auth.vo.out.KeyResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyResponseDto {
    private Boolean verification;

    public static KeyResponseVo toResponseVo(KeyResponseDto keyResponseDto) {
        return KeyResponseVo.builder()
                .verification(keyResponseDto.getVerification())
                .build();
    }
}
