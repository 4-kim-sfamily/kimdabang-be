package com.kimdabang.kdbserver.product.product.dto.in;

import com.kimdabang.kdbserver.product.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class ProductRequestDto {

    private String productCode;
    private String productName;
    private Long productPrice;
    private String description;
    private Date releaseDate;
    private String content;
    private Long categoryId;

    public Product toProductEntity(String productCode) {
        return Product.builder()
                .productCode(productCode)
                .productName(productName)
                .productPrice(productPrice)
                .description(description)
                .releaseDate(releaseDate)
                .content(content)
                .categoryId(categoryId)
                .build();
    }

    @Builder
    public ProductRequestDto(String productCode, String productName, Long productPrice, String description, Date releaseDate, String content, Long categoryId) {

        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.releaseDate = releaseDate;
        this.content = content;
        this.categoryId = categoryId;

    }
}
