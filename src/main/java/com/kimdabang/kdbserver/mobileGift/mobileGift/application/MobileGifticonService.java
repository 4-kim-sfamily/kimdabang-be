package com.kimdabang.kdbserver.mobileGift.mobileGift.application;

import com.kimdabang.kdbserver.mobileGift.mobileGift.dto.in.MobileGifticonAddRequestDto;
import com.kimdabang.kdbserver.mobileGift.mobileGift.dto.in.MobileGifticonUpdateRequestDto;
import com.kimdabang.kdbserver.mobileGift.mobileGift.dto.out.MobileGifticonResponseDto;

import java.util.List;

public interface MobileGifticonService {

    void addMobileGifticon(MobileGifticonAddRequestDto mobileGifticonAddRequestDto);
    void updateMobileGifticon(MobileGifticonUpdateRequestDto mobileGifticonUpdateRequestDto);
    void deleteMobileGifticon(Long id);
    MobileGifticonResponseDto getOneMobileGifticon(Long id);
    List<MobileGifticonResponseDto> getAllMobileGifticon();
}
