package com.kimdabang.kdbserver.auth.dto.in;

import com.kimdabang.kdbserver.auth.vo.in.KeyRequestVo;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class KeyRequestDto {
    private String key;

    public static KeyRequestDto toRequestDto(KeyRequestVo keyRequestVo) {
        return KeyRequestDto.builder()
                .key(keyRequestVo.getKey())
                .build();
    }
}
