package com.kimdabang.kdbserver.orders.orders.infrastructure;

import com.kimdabang.kdbserver.orders.orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {


}
