package com.kimdabang.kdbserver.mobileGift.userEnrollMG.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.common.exception.ErrorCode;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.mobileGift.mobileGift.domain.MobileGifticon;
import com.kimdabang.kdbserver.mobileGift.mobileGift.infrastructure.MobileGifticonRepository;
import com.kimdabang.kdbserver.mobileGift.userEnrollMG.domain.UserEnrollMobileGifticon;
import com.kimdabang.kdbserver.mobileGift.userEnrollMG.dto.in.UserEnrollMobileGifticonAddRequestDto;
import com.kimdabang.kdbserver.mobileGift.userEnrollMG.dto.out.UserEnrollMobileGifticonResponseDto;
import com.kimdabang.kdbserver.mobileGift.userEnrollMG.infrastructure.UserEnrollMobileGifticonRepository;
import com.kimdabang.kdbserver.mobileGift.userEnrollMG.vo.UserEnrollMobileGifticonResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserEnrollMobileGifticonServiceImpl implements UserEnrollMobileGifticonService {

    private final UserEnrollMobileGifticonRepository userEnrollMobileGifticonRepository;
    private final MobileGifticonRepository mobileGifticonRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public void addUserEnrollMobileGifticon(UserEnrollMobileGifticonAddRequestDto userEnrollMobileGifticonAddRequestDto, String Authorization) {
        String uuid = jwtTokenProvider.useToken(Authorization);

        MobileGifticon mobileGifticon = mobileGifticonRepository.findById(userEnrollMobileGifticonAddRequestDto.getMobileGifticonId())
                .orElseThrow(() -> new CustomException(MOBILEGIFTICON_NOT_FOUND));

        boolean alreadyEnrolled = userEnrollMobileGifticonRepository.existsByUuidAndMobileGifticonId(uuid, userEnrollMobileGifticonAddRequestDto.getMobileGifticonId());
        if (alreadyEnrolled) {
            throw new CustomException(MOBILEGIFTICON_ALREADY_ENROLL);
        }

        userEnrollMobileGifticonRepository.save(userEnrollMobileGifticonAddRequestDto.toEntity(uuid, mobileGifticon));
    }

    @Override
    @Transactional
    public void deleteUserEnrollMobileGifticon(Long id, String Authorization) {
        String uuid = jwtTokenProvider.useToken(Authorization);

        UserEnrollMobileGifticon userEnrollMobileGifticon = userEnrollMobileGifticonRepository.findByIdAndUuid(id, uuid)
                .orElseThrow(() -> new CustomException(MOBILEGIFTICON_NOT_ENROLL));

        userEnrollMobileGifticonRepository.delete(userEnrollMobileGifticon);
    }

    @Override
    public UserEnrollMobileGifticonResponseDto getOneUserEnrollMobileGifticon(Long id, String Authorization) {
        String uuid = jwtTokenProvider.useToken(Authorization);
        UserEnrollMobileGifticon userEnrollMobileGifticon = userEnrollMobileGifticonRepository.findByIdAndUuid(id, uuid)
                .orElseThrow(() -> new CustomException(MOBILEGIFTICON_NOT_ENROLL));
        return UserEnrollMobileGifticonResponseDto.builder()
                .id(userEnrollMobileGifticon.getId())
                .uuid(userEnrollMobileGifticon.getUuid())
                .mobileGifticonId(userEnrollMobileGifticon.getMobileGifticon().getId())
                .build();
    }

    @Override
    public List<UserEnrollMobileGifticonResponseDto> getAllUserEnrollMobileGifticon(String Authorization) {
        String uuid = jwtTokenProvider.useToken(Authorization);
        List<UserEnrollMobileGifticon> userEnrollMobileGifticons = userEnrollMobileGifticonRepository.findAllByUuid(uuid);
        return userEnrollMobileGifticons.stream()
                .map(userEnrollMobileGifticon -> UserEnrollMobileGifticonResponseDto.builder()
                        .id(userEnrollMobileGifticon.getId())
                        .uuid(userEnrollMobileGifticon.getUuid())
                        .mobileGifticonId(userEnrollMobileGifticon.getMobileGifticon().getId())
                        .build())
                .toList();
    }
}
