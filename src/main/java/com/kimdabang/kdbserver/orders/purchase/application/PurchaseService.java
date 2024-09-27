package com.kimdabang.kdbserver.orders.purchase.application;

import com.kimdabang.kdbserver.orders.purchase.dto.in.PurchaseRequestDto;
import com.kimdabang.kdbserver.orders.purchase.dto.out.PurchaseDetailResponseDto;
import com.kimdabang.kdbserver.orders.purchase.dto.out.PurchaseResponseDto;

import java.util.Date;
import java.util.List;

public interface PurchaseService {
    void addPurchase(PurchaseRequestDto purchaseRequestDto, String authorization);
    List<PurchaseResponseDto> getPurchaseList(Date start, Date end, String authorization);
    PurchaseDetailResponseDto getPurchase(Long purchaseCode);
}
