package com.kimdabang.kdbserver.orders.purchase.dto.in;

import com.kimdabang.kdbserver.common.entity.SnowFlakeGenerator;
import com.kimdabang.kdbserver.orders.payment.domain.Payment;
import com.kimdabang.kdbserver.orders.purchase.domain.Purchase;
import com.kimdabang.kdbserver.orders.purchase.vo.in.PurchaseRequestVo;
import com.kimdabang.kdbserver.orders.purchaseitem.domain.PurchaseItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequestDto {

    private String address;
    private String name;
    private String phone;
    private Long couponId;
    private String method;
    private Integer totalPrice;
    private Integer discountPrice;
    private Integer shippingPrice;
    private Integer amount;
    private List<PurchaseRequestVo.Item> itemList;

    public static PurchaseRequestDto toRequestDto(PurchaseRequestVo purchaseRequestVo) {
        return PurchaseRequestDto.builder()
                .address(purchaseRequestVo.getAddress())
                .name(purchaseRequestVo.getName())
                .phone(purchaseRequestVo.getPhone())
                .couponId(purchaseRequestVo.getCouponId())
                .method(purchaseRequestVo.getMethod())
                .totalPrice(purchaseRequestVo.getTotalPrice())
                .discountPrice(purchaseRequestVo.getDiscountPrice())
                .shippingPrice(purchaseRequestVo.getShippingPrice())
                .amount(purchaseRequestVo.getAmount())
                .itemList(purchaseRequestVo.getItems())
                .build();
    }

    public Payment toPayment(SnowFlakeGenerator snowFlakeGenerator) {
        return Payment.builder()
                .paymentCode(snowFlakeGenerator.generateUniqueId())
                .paymentDate(new Date())
                .couponId(couponId)
                .totalPrice(totalPrice)
                .discountPrice(discountPrice)
                .shippingPrice(shippingPrice)
                .amount(amount)
                .method(method)
                .build();
    }
    public Purchase toPurchase(Long paymentCode, String uuid, SnowFlakeGenerator snowFlakeGenerator) {
        return Purchase.builder()
                .purchaseCode(snowFlakeGenerator.generateUniqueId())
                .purchaseDate(new Date())
                .paymentCode(paymentCode)
                .userUuid(uuid)
                .status("주문확인중")
                .address(address)
                .name(name)
                .phone(phone)
                .build();
    }
    public List<PurchaseItem> toPurchaseItem(Purchase purchase) {
         return itemList.stream().map(
                item -> PurchaseItem.builder()
                        .productCode(item.getProductCode())
                        .optionsId(item.getOptionsId())
                        .options(item.getOptions())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .purchase(purchase)
                        .build())
                 .toList();
    }
}
