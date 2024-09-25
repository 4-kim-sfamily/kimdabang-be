package com.kimdabang.kdbserver.orders.purchase.application;

import com.kimdabang.kdbserver.common.entity.SnowFlakeGenerator;
import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.orders.payment.domain.Payment;
import com.kimdabang.kdbserver.orders.payment.infrastructure.PaymentRepository;
import com.kimdabang.kdbserver.orders.purchase.domain.Purchase;
import com.kimdabang.kdbserver.orders.purchase.dto.in.PurchaseRequestDto;
import com.kimdabang.kdbserver.orders.purchase.dto.out.PurchaseDetailResponseDto;
import com.kimdabang.kdbserver.orders.purchase.dto.out.PurchaseResponseDto;
import com.kimdabang.kdbserver.orders.purchase.infrastructure.PurchaseRepository;
import com.kimdabang.kdbserver.orders.purchaseitem.domain.PurchaseItem;
import com.kimdabang.kdbserver.orders.purchaseitem.infrastructure.PurchaseItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService{

    private final PaymentRepository paymentRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final SnowFlakeGenerator snowFlakeGenerator;

    @Transactional
    @Override
    public void addPurchase(PurchaseRequestDto purchaseRequestDto, String authorization) {
        try {
            String uuid = jwtTokenProvider.useToken(authorization);
            Payment payment = paymentRepository.save(purchaseRequestDto.toPayment(snowFlakeGenerator));
            Purchase purchase = purchaseRepository
                    .save(purchaseRequestDto.toPurchase(payment.getPaymentCode(), uuid, snowFlakeGenerator));
            List<PurchaseItem> purchaseItemList = purchaseRequestDto.toPurchaseItem(purchase);
            purchaseItemRepository.saveAll(purchaseItemList);
        } catch (Exception e) {
            // 로그를 남기고 예외 처리
            throw new CustomException(PURCHASE_PROCESS_FAILED);
        }
    }
    @Override
    public List<PurchaseResponseDto> getPurchaseList(Date start, Date end, String authorization) {
        String uuid = jwtTokenProvider.useToken(authorization);
        log.info("start:{},end:{}",start,end);
        List<Purchase> purchaseList = purchaseRepository.findByUserUuidAndPurchaseDateBetween(uuid, start, end);

        return purchaseList.stream().map(
                purchase -> PurchaseResponseDto.toResponseDto(purchase, purchaseItemRepository.findByPurchase(purchase))
            ).toList();
    }
    @Override
    public PurchaseDetailResponseDto getPurchase(Long purchaseCode) {
        Purchase purchase = purchaseRepository.findByPurchaseCode(purchaseCode).orElseThrow(
                () -> new CustomException(PURCHASE_NOT_FOUND)
        );

        Payment payment = paymentRepository.findByPaymentCode(purchase.getPaymentCode()).orElseThrow(
                () -> new CustomException(PAYMENT_NOT_FOUND)
        );
        return PurchaseDetailResponseDto.toResponseDto(purchase, payment, purchaseItemRepository.findByPurchase(purchase));

    }
}
