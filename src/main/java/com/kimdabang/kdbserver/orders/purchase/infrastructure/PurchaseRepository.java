package com.kimdabang.kdbserver.orders.purchase.infrastructure;

import com.kimdabang.kdbserver.orders.purchase.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Optional<Purchase> findByPurchaseCode(Long purchaseCode);
    List<Purchase> findByUserUuidAndPurchaseDateBetween(String uuid, Date startDate, Date endDate);
}
