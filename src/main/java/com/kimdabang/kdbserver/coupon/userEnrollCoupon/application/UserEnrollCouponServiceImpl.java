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
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;
import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.COUPON_NOT_ENROLL;
import static com.kimdabang.kdbserver.common.exception.ErrorCode.COUPON_NOT_FOUND;

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

        Coupon coupon = couponRepository.findById(userEnrollCouponAddRequestDto.getCouponId())
                .orElseThrow(() -> new CustomException(COUPON_NOT_FOUND));

        LocalDateTime toolTime = LocalDateTime.now().plus(coupon.getValidityYear()).plus(coupon.getValidityMonth()).plus(coupon.getValidityDay());
        LocalDateTime expiredDate = coupon.getExpiredDate();

        if (toolTime.isBefore(expiredDate)) {
            expiredDate = toolTime;
        }

        userEnrollCouponRepository.save(userEnrollCouponAddRequestDto.toEntity(uuid, LocalDateTime.now(), expiredDate, coupon));
    }

    @Override
    @Transactional
    public void updateUserEnrollCoupon(UserEnrollCouponUpdateRequestDto userEnrollCouponRequestDto) {
        UserEnrollCoupon userEnrollCoupon = userEnrollCouponRepository.findById(userEnrollCouponRequestDto.getId())
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

        if (userEnrollCoupons != null) {
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
        return List.of();
    }
}
