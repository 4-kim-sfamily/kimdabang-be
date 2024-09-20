package com.kimdabang.kdbserver.mobileGift.mobileGift.application;

import com.kimdabang.kdbserver.mobileGift.mobileGift.dto.in.MobileGifticonAddRequestDto;
import com.kimdabang.kdbserver.mobileGift.mobileGift.dto.in.MobileGifticonUpdateRequestDto;
import com.kimdabang.kdbserver.mobileGift.mobileGift.dto.out.MobileGifticonResponseDto;
import com.kimdabang.kdbserver.mobileGift.mobileGift.infrastructure.MobileGifticonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        // TODO: 9/20/24  
    }

    @Override
    @Transactional
    public void deleteMobileGifticon(Long id) {
        // TODO: 9/20/24  
    }

    @Override
    public MobileGifticonResponseDto getOneMobileGifticon(Long id) {
        // TODO: 9/20/24  
        return null;
    }

    @Override
    public List<MobileGifticonResponseDto> getAllMobileGifticon() {
        // TODO: 9/20/24  
        return null;
    }
}
