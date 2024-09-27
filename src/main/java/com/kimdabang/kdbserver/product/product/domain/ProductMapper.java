package com.kimdabang.kdbserver.product.product.domain;

public class ProductMapper {

    public static ProductDocument toDocument(Product product) {
        return ProductDocument.builder()
                .id(product.getId().toString())
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .categoryId(product.getCategoryId())
                .description(product.getDescription())
                .build();
    }

    public static ProductDocument toPartialDocument(ProductDocument document) {
        return ProductDocument.builder()
                .productCode(document.getProductCode())
                .productName(document.getProductName())
                .categoryId(document.getCategoryId())
                .build();
    }

}
