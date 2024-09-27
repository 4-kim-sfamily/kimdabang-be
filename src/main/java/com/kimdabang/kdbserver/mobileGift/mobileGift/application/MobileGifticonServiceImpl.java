package com.kimdabang.kdbserver.mobileGift.mobileGift.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.common.exception.ErrorCode;
import com.kimdabang.kdbserver.mobileGift.mobileGift.domain.MobileGifticon;
import com.kimdabang.kdbserver.mobileGift.mobileGift.dto.in.MobileGifticonAddRequestDto;
import com.kimdabang.kdbserver.mobileGift.mobileGift.dto.in.MobileGifticonUpdateRequestDto;
import com.kimdabang.kdbserver.mobileGift.mobileGift.dto.out.MobileGifticonResponseDto;
import com.kimdabang.kdbserver.mobileGift.mobileGift.infrastructure.MobileGifticonRepository;
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
public class MobileGifticonServiceImpl implements MobileGifticonService {
    
    private final MobileGifticonRepository mobileGifticonRepository;
    
    @Override
    @Transactional
    public void addMobileGifticon(MobileGifticonAddRequestDto mobileGifticonAddRequestDto) {
        mobileGifticonRepository.save(mobileGifticonAddRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void updateMobileGifticon(MobileGifticonUpdateRequestDto mobileGifticonUpdateRequestDto) {
        mobileGifticonRepository.findById(mobileGifticonUpdateRequestDto.getId())
                .orElseThrow(() -> new CustomException(MOBILEGIFTICON_NOT_FOUND));

        mobileGifticonRepository.save(mobileGifticonUpdateRequestDto.toEntity());
    }

    @Override
    @Transactional
    public void deleteMobileGifticon(Long id) {
        MobileGifticon mobileGifticon = mobileGifticonRepository.findById(id)
                .orElseThrow(() -> new CustomException(MOBILEGIFTICON_NOT_FOUND));
        mobileGifticonRepository.delete(mobileGifticon);
    }

    @Override
    public MobileGifticonResponseDto getOneMobileGifticon(Long id) {
        MobileGifticon mobileGifticon = mobileGifticonRepository.findById(id)
                .orElseThrow(() -> new CustomException(MOBILEGIFTICON_NOT_FOUND));

        return MobileGifticonResponseDto.builder()
                .id(mobileGifticon.getId())
                .number(mobileGifticon.getNumber())
                .price(mobileGifticon.getPrice())
                .build();
    }

    @Override
    public List<MobileGifticonResponseDto> getAllMobileGifticon() {
        List<MobileGifticon> mobileGifticons = mobileGifticonRepository.findAll();

        return mobileGifticons.stream()
                .map(mobileGifticon -> MobileGifticonResponseDto.builder()
                        .id(mobileGifticon.getId())
                        .number(mobileGifticon.getNumber())
                        .price(mobileGifticon.getPrice())
                        .build())
                .toList();
    }
}
