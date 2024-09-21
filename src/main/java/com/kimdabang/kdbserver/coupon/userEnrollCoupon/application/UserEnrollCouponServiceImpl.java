package com.kimdabang.kdbserver.coupon.userEnrollCoupon.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
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
import java.util.Optional;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.*;

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
    public void addUserEnrollCoupon(UserEnrollCouponAddRequestDto userEnrollCouponAddRequestDto, String Authorization) {

        String uuid = jwtTokenProvider.useToken(Authorization);

        Coupon coupon = couponRepository.findById(userEnrollCouponAddRequestDto.getCouponId())
                .orElseThrow(() -> new CustomException(COUPON_NOT_FOUND));

        boolean alreadyEnrolled = userEnrollCouponRepository.existsByUuidAndCouponId(uuid, userEnrollCouponAddRequestDto.getCouponId());
        if (alreadyEnrolled) {
            throw new CustomException(COUPON_ALREADY_ENROLLED);
        }

        LocalDateTime expiredDate = coupon.getExpiredDate();

        if (expiredDate.isAfter(userEnrollCouponAddRequestDto.getExpiredDate())) {
            expiredDate = userEnrollCouponAddRequestDto.getExpiredDate();
        }

        userEnrollCouponRepository.save(userEnrollCouponAddRequestDto.toEntity(uuid, userEnrollCouponAddRequestDto.getCreatedAt(), expiredDate, coupon));
    }

    @Override
    @Transactional
    public void updateUserEnrollCoupon(UserEnrollCouponUpdateRequestDto userEnrollCouponRequestDto, String Authorization) {
        String userUuid = jwtTokenProvider.useToken(Authorization);

        UserEnrollCoupon userEnrollCoupon = userEnrollCouponRepository.findByIdAndUuid(userEnrollCouponRequestDto.getId(), userUuid)
                .orElseThrow(() -> new CustomException(COUPON_NOT_ENROLL));
        userEnrollCouponRepository.save(userEnrollCouponRequestDto.toEntity(userEnrollCoupon));
    }

    @Override
    @Transactional
    public void deleteUserEnrollCoupon(Long id) {
        UserEnrollCoupon deleteUserEnrollCoupon = userEnrollCouponRepository.findById(id)
                .orElseThrow(() -> new CustomException(COUPON_NOT_ENROLL));
        userEnrollCouponRepository.delete(deleteUserEnrollCoupon);
    }

//    @Override
//    public UserEnrollCouponResponseDto getOneUserEnrollCoupon(Long id) {
//        UserEnrollCoupon userEnrollCoupon = userEnrollCouponRepository.findById(id)
//                .orElseThrow(() -> new CustomException(COUPON_NOT_ENROLL));
//        return UserEnrollCouponResponseDto.builder()
//                .id(userEnrollCoupon.getId())
//                .uuid(userEnrollCoupon.getUuid())
//                .coupon(userEnrollCoupon.getCoupon())
//                .createdAt(userEnrollCoupon.getCreatedAt())
//                .isUsed(userEnrollCoupon.getIsUsed())
//                .usedAt(userEnrollCoupon.getUsedAt())
//                .expiredDate(userEnrollCoupon.getExpiredDate())
//                .build();
//    }

    @Override
    public List<UserEnrollCouponResponseDto> getAllUserEnrollCoupon(String Authorization) {
        String userUuid = jwtTokenProvider.useToken(Authorization);
        List<UserEnrollCoupon> userEnrollCoupons = userEnrollCouponRepository.findAllByUuid(userUuid);

        return userEnrollCoupons.stream()
                .map(userEnrollCoupon -> UserEnrollCouponResponseDto.builder()
                        .id(userEnrollCoupon.getId())
                        .uuid(userEnrollCoupon.getUuid())
                        .couponId(userEnrollCoupon.getCoupon().getId())
                        .createdAt(userEnrollCoupon.getCreatedAt())
                        .isUsed(userEnrollCoupon.getIsUsed())
                        .usedAt(userEnrollCoupon.getUsedAt())
                        .expiredDate(userEnrollCoupon.getExpiredDate())
                        .build())
                .toList();
    }

    @Override
    public Long countEnrollCoupon(String Authorization) {
        String userUuid = jwtTokenProvider.useToken(Authorization);
        return userEnrollCouponRepository.countByUuid(userUuid)
                .orElseThrow(() -> new CustomException(COUPON_NOT_ENROLL));
    }

}
