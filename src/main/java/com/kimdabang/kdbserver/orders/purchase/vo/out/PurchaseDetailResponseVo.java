package com.kimdabang.kdbserver.orders.purchase.vo.out;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetailResponseVo {
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
    private List<Item> itemList;

    @Getter
    @ToString
    @Builder
    public static class Item {
        private String productCode;
        private String options;
        private Integer quantity;
        private Integer price;

    }
}
