package com.kimdabang.kdbserver.mobileGift.userEnrollMG.application;

import com.kimdabang.kdbserver.mobileGift.userEnrollMG.dto.in.UserEnrollMobileGifticonAddRequestDto;
import com.kimdabang.kdbserver.mobileGift.userEnrollMG.dto.out.UserEnrollMobileGifticonResponseDto;

import java.util.List;

public interface UserEnrollMobileGifticonService {

    void addUserEnrollMobileGifticon(UserEnrollMobileGifticonAddRequestDto userEnrollMobileGifticonAddRequestDto, String Authorization);
    void deleteUserEnrollMobileGifticon(Long id, String Authorization);
    UserEnrollMobileGifticonResponseDto getOneUserEnrollMobileGifticon(Long id, String Authorization);
    List<UserEnrollMobileGifticonResponseDto> getAllUserEnrollMobileGifticon(String Authorization);
}
