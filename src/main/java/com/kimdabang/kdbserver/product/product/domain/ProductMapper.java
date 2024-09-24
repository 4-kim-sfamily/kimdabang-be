package com.kimdabang.kdbserver.product.product.domain;

public class ProductMapper {

    public static ProductDocument toDocument(Product product) {
        return ProductDocument.builder()
                .id(product.getId().toString())
                .productName(product.getProductCode())
                .productDescription(product.getDescription())
                .build();
    }
}
