package com.kimdabang.kdbserver.coupon.coupon.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.coupon.coupon.domain.Coupon;
import com.kimdabang.kdbserver.coupon.coupon.dto.in.CouponAddRequestDto;
import com.kimdabang.kdbserver.coupon.coupon.dto.in.CouponUpdateRequestDto;
import com.kimdabang.kdbserver.coupon.coupon.dto.out.CouponResponseDto;
import com.kimdabang.kdbserver.coupon.coupon.infrastructure.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.COUPON_NOT_FOUND;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    @Transactional
    public void addCoupon(CouponAddRequestDto couponRequestAddDto) {
        couponRepository.save(couponRequestAddDto.toEntity());
    }

    @Override
    @Transactional
    public void updateCoupon(CouponUpdateRequestDto couponRequestUpdateDto) {
        couponRepository.findById(couponRequestUpdateDto.getId())
                .orElseThrow(() -> new CustomException(COUPON_NOT_FOUND));

        couponRepository.save(couponRequestUpdateDto.toEntity());
    }

    @Override
    @Transactional
    public void deleteCoupon(Long id) {
        Coupon deleteCoupon = couponRepository.findById(id)
                .orElseThrow(() -> new CustomException(COUPON_NOT_FOUND));

        couponRepository.delete(deleteCoupon);
    }

    @Override
    public CouponResponseDto getOneCoupon(Long id) {
        Coupon getCoupon = couponRepository.findById(id)
                .orElseThrow(() -> new CustomException(COUPON_NOT_FOUND));

        return CouponResponseDto.builder()
                .id(getCoupon.getId())
                .name(getCoupon.getName())
                .couponType(getCoupon.getCouponType())
                .expiredDate(getCoupon.getExpiredDate())
                .value(getCoupon.getValue())
                .validity(getCoupon.getValidity())
                .build();
    }

    @Override
    public List<CouponResponseDto> getAllCoupon() {
        List<Coupon> coupons = couponRepository.findAll();

            return coupons.stream()
                    .map(coupon -> CouponResponseDto.builder()
                            .id(coupon.getId())
                            .name(coupon.getName())
                            .couponType(coupon.getCouponType())
                            .expiredDate(coupon.getExpiredDate())
                            .value(coupon.getValue())
                            .validity(coupon.getValidity())
                            .build())
                    .toList();
    }
}
