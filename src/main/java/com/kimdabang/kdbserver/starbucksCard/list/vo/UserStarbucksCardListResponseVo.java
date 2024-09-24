package com.kimdabang.kdbserver.starbucksCard.list.vo;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserStarbucksCardListResponseVo {
    private Long id;
    private String uuid;
    private Long starbucksCardId;
}
