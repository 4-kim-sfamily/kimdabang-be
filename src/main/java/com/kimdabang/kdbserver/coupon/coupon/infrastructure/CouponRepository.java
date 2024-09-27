package com.kimdabang.kdbserver.coupon.coupon.infrastructure;

import com.kimdabang.kdbserver.coupon.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
//    Optional<Coupon> findbycoupon~~~~~~(long couponid)
//    Optional<LocalDateTime> findExpiredDateByCoupon(Coupon coupon);
//    Optional<Period> findValidityYearByCoupon(Coupon coupon);
//    Optional<Period> findValidityMonthByCoupon(Coupon coupon);
//    Optional<Period> findValidityDayByCoupon(Coupon coupon);
}
