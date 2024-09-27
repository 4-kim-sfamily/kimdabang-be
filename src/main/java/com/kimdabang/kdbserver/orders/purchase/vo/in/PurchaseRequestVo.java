package com.kimdabang.kdbserver.orders.purchase.vo.in;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PurchaseRequestVo {
    private String address;
    private String name;
    private String phone;
    private Long couponId;
    private String method;
    private Integer totalPrice;
    private Integer discountPrice;
    private Integer shippingPrice;
    private Integer amount;
    private List<Item> items;

    @Getter
    @ToString
    public static class Item {
        private String productCode;
        private Long optionsId;
        private String options;
        private Integer quantity;
        private Integer price;

    }
}
