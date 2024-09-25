package com.kimdabang.kdbserver.orders.purchase.dto.out;

import com.kimdabang.kdbserver.orders.purchase.domain.Purchase;
import com.kimdabang.kdbserver.orders.purchase.vo.out.PurchaseResponseVo;
import com.kimdabang.kdbserver.orders.purchaseitem.domain.PurchaseItem;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponseDto {
    private Long purchaseCode;
    private Date purchaseDate;
    private String status;
    private List<PurchaseResponseVo.Item> itemList;

    public static PurchaseResponseDto toResponseDto(Purchase purchase, List<PurchaseItem> purchaseItemList) {
        return PurchaseResponseDto.builder()
                .purchaseCode(purchase.getPurchaseCode())
                .purchaseDate(purchase.getPurchaseDate())
                .status(purchase.getStatus())
                .itemList(purchaseItemList.stream().map(
                        purchaseItem -> PurchaseResponseVo.Item.builder()
                                .productCode(purchaseItem.getProductCode())
                                .options(purchaseItem.getOptions())
                                .quantity(purchaseItem.getQuantity())
                                .price(purchaseItem.getPrice())
                                .build()).toList())
                .build();
    }

    public PurchaseResponseVo toResponseVo() {
        return PurchaseResponseVo.builder()
                .purchaseCode(purchaseCode)
                .purchaseDate(purchaseDate)
                .status(status)
                .itemList(itemList)
                .build();
    }
}
