package com.kimdabang.kdbserver.orders.payment.infrastructure;

import com.kimdabang.kdbserver.orders.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByPaymentCode(Long paymentCode);
}
