package com.kimdabang.kdbserver.orders.purchase.vo.out;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponseVo {
    private Long purchaseCode;
    private Date purchaseDate;
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
