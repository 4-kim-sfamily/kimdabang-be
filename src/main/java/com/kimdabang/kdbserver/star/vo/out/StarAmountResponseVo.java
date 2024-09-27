package com.kimdabang.kdbserver.star.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StarAmountResponseVo {

    private Long starAmount;
    private Long greenStarAmount;
}
