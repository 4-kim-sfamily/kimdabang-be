package com.kimdabang.kdbserver.star.dto.in;

import com.kimdabang.kdbserver.star.domain.Star;
import com.kimdabang.kdbserver.star.vo.in.StarAddRequestVo;
import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StarAddRequestDto {
    private Date expirationDate;
    private String description;
    private Boolean isEcho;
    private Integer starAmount;

    public static StarAddRequestDto toRequestDto(StarAddRequestVo starAddRequestVo) {
        return StarAddRequestDto.builder()
                .expirationDate(starAddRequestVo.getExpirationDate())
                .description(starAddRequestVo.getDescription())
                .isEcho(starAddRequestVo.getIsEcho())
                .starAmount(starAddRequestVo.getStarAmount())
                .build();
    }
    public Star toEntity(String uuid , Date createdAt) {
        Date now = new Date();
        return Star.builder()
                .uuid(uuid)
                .expirationDate(expirationDate)
                //.createdAt(new Date(now.getTime()))
                .createdAt(createdAt)
                .isEcho(isEcho)
                .isUsed(false)
                .description(description)
                .build();
    }
}
