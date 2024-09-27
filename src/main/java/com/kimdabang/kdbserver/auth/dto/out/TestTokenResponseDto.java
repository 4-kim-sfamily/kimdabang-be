package com.kimdabang.kdbserver.auth.dto.out;

import com.kimdabang.kdbserver.auth.vo.out.TestTokenResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestTokenResponseDto {
    private String uuid;

    public TestTokenResponseVo toResponseVo() {
        return TestTokenResponseVo.builder()
                .uuid(uuid)
                .build();
    }
}
