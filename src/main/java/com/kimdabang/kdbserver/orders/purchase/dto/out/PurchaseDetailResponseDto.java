package com.kimdabang.kdbserver.orders.purchase.dto.out;

import com.kimdabang.kdbserver.orders.payment.domain.Payment;
import com.kimdabang.kdbserver.orders.purchase.domain.Purchase;
import com.kimdabang.kdbserver.orders.purchase.vo.out.PurchaseDetailResponseVo;
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
public class PurchaseDetailResponseDto {

    private Long purchaseCode;
    private Long paymentCode;
    private Date purchaseDate;
    private String address;
    private String name;
    private String phone;
    private Long couponId;
    private String method;
    private Integer totalPrice;
    private Integer discountPrice;
    private Integer shippingPrice;
    private Integer amount;
    private String status;
    private List<PurchaseDetailResponseVo.Item> itemList;

    public static PurchaseDetailResponseDto toResponseDto(Purchase purchase, Payment payment, List<PurchaseItem> purchaseItemList) {
        return PurchaseDetailResponseDto.builder()
                .purchaseCode(purchase.getPurchaseCode())
                .paymentCode(purchase.getPaymentCode())
                .purchaseDate(purchase.getPurchaseDate())
                .address(purchase.getAddress())
                .name(purchase.getName())
                .phone(purchase.getPhone())
                .couponId(payment.getCouponId())
                .method(payment.getMethod())
                .totalPrice(payment.getTotalPrice())
                .discountPrice(payment.getDiscountPrice())
                .shippingPrice(payment.getShippingPrice())
                .amount(payment.getAmount())
                .status(purchase.getStatus())
                .itemList(purchaseItemList.stream().map(
                        purchaseItem -> PurchaseDetailResponseVo.Item.builder()
                                .productCode(purchaseItem.getProductCode())
                                .options(purchaseItem.getOptions())
                                .quantity(purchaseItem.getQuantity())
                                .price(purchaseItem.getPrice())
                                .build()).toList())
                .build();
    }
    public PurchaseDetailResponseVo toResponseVo() {
        return PurchaseDetailResponseVo.builder()
                .purchaseCode(purchaseCode)
                .paymentCode(paymentCode)
                .purchaseDate(purchaseDate)
                .address(address)
                .name(name)
                .phone(phone)
                .couponId(couponId)
                .method(method)
                .totalPrice(totalPrice)
                .discountPrice(discountPrice)
                .shippingPrice(shippingPrice)
                .amount(amount)
                .status(status)
                .itemList(itemList)
                .build();
    }

}
