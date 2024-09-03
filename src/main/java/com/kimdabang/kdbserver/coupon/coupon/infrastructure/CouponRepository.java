package com.kimdabang.kdbserver.coupon.coupon.infrastructure;

import com.kimdabang.kdbserver.coupon.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

}
