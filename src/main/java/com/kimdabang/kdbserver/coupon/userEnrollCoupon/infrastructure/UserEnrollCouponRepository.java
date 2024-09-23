package com.kimdabang.kdbserver.coupon.userEnrollCoupon.infrastructure;

import com.kimdabang.kdbserver.coupon.coupon.domain.Coupon;
import com.kimdabang.kdbserver.coupon.userEnrollCoupon.domain.UserEnrollCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserEnrollCouponRepository extends JpaRepository<UserEnrollCoupon, Long> {
    List<UserEnrollCoupon> findAllByUuid(String uuid);

    Optional<UserEnrollCoupon> findByIdAndUuid(Long id, String uuid);

    Optional<Long> countByUuid(String uuid);

    boolean existsByUuidAndCouponId(String uuid, Long couponId);

    @Query("SELECT c FROM Coupon c LEFT JOIN UserEnrollCoupon uec ON c.id = uec.coupon.id AND uec.uuid = :uuid " +
            "WHERE uec.coupon.id IS NULL AND c.expiredDate > CURRENT_TIMESTAMP")
    List<Coupon> findNotEnrolledCouponsByUuid(@Param("uuid") String uuid);

    @Query("SELECT uec FROM UserEnrollCoupon uec WHERE uec.uuid = :uuid AND uec.expiredDate > CURRENT_TIMESTAMP")
    List<UserEnrollCoupon> findValidCouponsByUuid(@Param("uuid") String uuid);
}
