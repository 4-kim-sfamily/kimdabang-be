package com.kimdabang.kdbserver.coupon.userEnrollCoupon.infrastructure;

import com.kimdabang.kdbserver.coupon.userEnrollCoupon.domain.UserEnrollCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEnrollCouponRepository extends JpaRepository<UserEnrollCoupon, Long> {
    List<UserEnrollCoupon> findAllByUuid(String uuid);
}
