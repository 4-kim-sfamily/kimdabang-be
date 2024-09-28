package com.kimdabang.kdbserver.orders.purchaseitem.infrastructure;

import com.kimdabang.kdbserver.orders.purchase.domain.Purchase;
import com.kimdabang.kdbserver.orders.purchaseitem.domain.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {
    List<PurchaseItem> findByPurchase(Purchase purchase);
    Optional<PurchaseItem> findById(Long id);
    List<PurchaseItem> findByPurchaseIn(List<Purchase> purchases);
    Integer countByProductCode(String productCode);
}
