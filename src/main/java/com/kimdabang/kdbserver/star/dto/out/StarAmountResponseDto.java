package com.kimdabang.kdbserver.star.dto.out;

import com.kimdabang.kdbserver.star.vo.out.StarAmountResponseVo;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StarAmountResponseDto {
    private Long starAmount;
    private Long greenStarAmount;

    public StarAmountResponseVo toResponseVo() {
        return StarAmountResponseVo.builder()
                .starAmount(starAmount)
                .greenStarAmount(greenStarAmount)
                .build();
    }
}
