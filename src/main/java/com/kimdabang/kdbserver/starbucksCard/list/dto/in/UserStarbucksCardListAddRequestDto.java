package com.kimdabang.kdbserver.starbucksCard.list.dto.in;

import com.kimdabang.kdbserver.starbucksCard.card.domain.StarbucksCard;
import com.kimdabang.kdbserver.starbucksCard.list.domain.UserStarbucksCardList;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserStarbucksCardListAddRequestDto {
    private Long starbucksCardId;

    public UserStarbucksCardList toEntity(String uuid, StarbucksCard starbucksCard) {
        return UserStarbucksCardList.builder()
                .uuid(uuid)
                .starbucksCard(starbucksCard)
                .build();
    }

    @Builder
    public UserStarbucksCardListAddRequestDto(Long starbucksCardId) {
        this.starbucksCardId = starbucksCardId;
    }
}
