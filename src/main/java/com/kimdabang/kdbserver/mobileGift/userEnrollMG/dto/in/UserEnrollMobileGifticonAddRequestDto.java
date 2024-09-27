package com.kimdabang.kdbserver.mobileGift.userEnrollMG.dto.in;

import com.kimdabang.kdbserver.mobileGift.mobileGift.domain.MobileGifticon;
import com.kimdabang.kdbserver.mobileGift.userEnrollMG.domain.UserEnrollMobileGifticon;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserEnrollMobileGifticonAddRequestDto {
    private Long mobileGifticonId;

    public UserEnrollMobileGifticon toEntity(String uuid, MobileGifticon mobileGifticon) {
        return UserEnrollMobileGifticon.builder()
                .uuid(uuid)
                .mobileGifticon(mobileGifticon)
                .build();
    }

    @Builder
    public UserEnrollMobileGifticonAddRequestDto(Long mobileGifticonId) {
        this.mobileGifticonId = mobileGifticonId;
    }
}
