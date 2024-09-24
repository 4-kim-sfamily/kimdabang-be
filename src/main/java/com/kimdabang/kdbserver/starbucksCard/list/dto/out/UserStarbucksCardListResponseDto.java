package com.kimdabang.kdbserver.starbucksCard.list.dto.out;

import com.kimdabang.kdbserver.starbucksCard.list.vo.UserStarbucksCardListResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserStarbucksCardListResponseDto {
    private Long id;
    private String uuid;
    private Long starbucksCardId;

    public UserStarbucksCardListResponseVo toResponseVo() {
        return UserStarbucksCardListResponseVo.builder()
                .id(id)
                .uuid(uuid)
                .starbucksCardId(starbucksCardId)
                .build();
    }

    @Builder
    public UserStarbucksCardListResponseDto(Long id, String uuid, Long starbucksCardId) {
        this.id = id;
        this.uuid = uuid;
        this.starbucksCardId = starbucksCardId;
    }
}
