package com.kimdabang.kdbserver.coupon.userEnrollCoupon.infrastructure;

import com.kimdabang.kdbserver.coupon.userEnrollCoupon.domain.UserEnrollCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserEnrollCouponRepository extends JpaRepository<UserEnrollCoupon, Long> {
    List<UserEnrollCoupon> findAllByUuid(String uuid);

    Optional<UserEnrollCoupon> findByIdAndUuid(Long id, String uuid);

    long countByUuid(String uuid);

    boolean existsByUuidAndCouponId(String uuid, Long couponId);
}
