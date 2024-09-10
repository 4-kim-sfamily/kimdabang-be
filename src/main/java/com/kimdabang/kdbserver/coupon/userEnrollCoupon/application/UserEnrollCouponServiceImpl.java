package com.kimdabang.kdbserver.coupon.userEnrollCoupon.application;

import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.coupon.coupon.domain.Coupon;
import com.kimdabang.kdbserver.coupon.coupon.infrastructure.CouponRepository;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.domain.UserEnrollCoupon;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.dto.in.UserEnrollCouponAddRequestDto;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.dto.in.UserEnrollCouponUpdateRequestDto;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.dto.out.UserEnrollCouponResponseDto;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.infrastructure.UserEnrollCouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserEnrollCouponServiceImpl implements UserEnrollCouponService {

    private final UserEnrollCouponRepository userEnrollCouponRepository;
    private final CouponRepository couponRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public void addUserEnrollCoupon(UserEnrollCouponAddRequestDto userEnrollCouponAddRequestDto) {

        String uuid = jwtTokenProvider.useToken(userEnrollCouponAddRequestDto.getAccessToken());

        LocalDateTime now = LocalDateTime.now();

        Coupon coupon = couponRepository.findById(userEnrollCouponAddRequestDto.getCouponId()).get();

        now.plus(coupon.getValidityYear()).plus(coupon.getValidityMonth()).plus(coupon.getValidityDay());
        LocalDateTime expiredDate = coupon.getExpiredDate();

        if (now.isBefore(expiredDate)) {
            expiredDate = now;
        }

        userEnrollCouponRepository.save(userEnrollCouponAddRequestDto.toEntity(uuid, LocalDateTime.now(), expiredDate, coupon));
    }

    @Override
    @Transactional
    public void updateUserEnrollCoupon(UserEnrollCouponUpdateRequestDto userEnrollCouponRequestDto) {
        UserEnrollCoupon userEnrollCoupon = userEnrollCouponRepository.findById(userEnrollCouponRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 쿠폰이 등록되지 않았습니다."));
        userEnrollCouponRepository.save(userEnrollCouponRequestDto.toEntity(userEnrollCoupon));
    }

    @Override
    @Transactional
    public void deleteUserEnrollCoupon(Long id) {
        UserEnrollCoupon deleteUserEnrollCoupon = userEnrollCouponRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 쿠폰이 등록되지 않았습니다."));
        userEnrollCouponRepository.delete(deleteUserEnrollCoupon);
    }

    @Override
    public UserEnrollCouponResponseDto getOneUserEnrollCoupon(Long id) {
        UserEnrollCoupon userEnrollCoupon = userEnrollCouponRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 쿠폰이 등록되지 않았습니다."));
        return UserEnrollCouponResponseDto.builder()
                .id(userEnrollCoupon.getId())
                .uuid(userEnrollCoupon.getUuid())
                .coupon(userEnrollCoupon.getCoupon())
                .createdAt(userEnrollCoupon.getCreatedAt())
                .isUsed(userEnrollCoupon.getIsUsed())
                .usedAt(userEnrollCoupon.getUsedAt())
                .expiredDate(userEnrollCoupon.getExpiredDate())
                .build();
    }

    @Override
    public List<UserEnrollCouponResponseDto> getAllUserEnrollCoupon() {
        List<UserEnrollCoupon> userEnrollCoupons = userEnrollCouponRepository.findAll();

        return userEnrollCoupons.stream()
                .map(userEnrollCoupon -> UserEnrollCouponResponseDto.builder()
                        .id(userEnrollCoupon.getId())
                        .uuid(userEnrollCoupon.getUuid())
                        .coupon(userEnrollCoupon.getCoupon())
                        .createdAt(userEnrollCoupon.getCreatedAt())
                        .isUsed(userEnrollCoupon.getIsUsed())
                        .usedAt(userEnrollCoupon.getUsedAt())
                        .expiredDate(userEnrollCoupon.getExpiredDate())
                        .build())
                .toList();
    }
}
