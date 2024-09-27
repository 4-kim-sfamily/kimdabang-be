package com.kimdabang.kdbserver.star.dto.out;

import com.kimdabang.kdbserver.star.vo.out.StarResponseVo;
import lombok.*;

import java.util.Date;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StarResponseDto {
    private Date expirationDate;
    private Date createdAt;
    private String description;
    private Boolean isEcho;
    private Integer starAmount;

    public StarResponseVo toResponseVo() {
        return StarResponseVo.builder()
                .expirationDate(expirationDate)
                .createdAt(createdAt)
                .description(description)
                .isEcho(isEcho)
                .starAmount(starAmount)
                .build();
    }

    //중복 체크를 특정 갑으로 하기 위해 추가
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarResponseDto that = (StarResponseDto) o;
        return isEcho == that.isEcho &&
                Objects.equals(description, that.description) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, createdAt, isEcho);
    }
}
