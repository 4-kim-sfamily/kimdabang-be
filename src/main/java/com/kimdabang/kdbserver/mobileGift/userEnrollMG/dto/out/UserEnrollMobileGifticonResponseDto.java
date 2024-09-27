package com.kimdabang.kdbserver.mobileGift.userEnrollMG.dto.out;

import com.kimdabang.kdbserver.mobileGift.userEnrollMG.vo.UserEnrollMobileGifticonResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserEnrollMobileGifticonResponseDto {
    private Long id;
    private String uuid;
    private Long mobileGifticonId;

    public UserEnrollMobileGifticonResponseVo toResponseVo() {
        return UserEnrollMobileGifticonResponseVo.builder()
                .id(id)
                .uuid(uuid)
                .mobileGifticonId(mobileGifticonId)
                .build();
    }

    @Builder
    public UserEnrollMobileGifticonResponseDto(Long id, String uuid, Long mobileGifticonId) {
        this.id = id;
        this.uuid = uuid;
        this.mobileGifticonId = mobileGifticonId;
    }
}
