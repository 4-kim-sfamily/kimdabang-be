package com.kimdabang.kdbserver.orders.orders.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    @Transactional
    public Long order(Long userId, Long productId, int count) {

    }
}
