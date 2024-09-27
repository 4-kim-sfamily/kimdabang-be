package com.kimdabang.kdbserver.product.media.dto.in;

import com.kimdabang.kdbserver.product.media.domain.ProductMedia;
import com.kimdabang.kdbserver.product.product.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductMediaRequestDto {

    private Long productId;
    private String mediaName;
    private String mediaType;
    private String mediaURL;

    public ProductMedia toProductMediaEntity(Product product) {
        return ProductMedia.builder()
                .product(product)
                .mediaName(mediaName)
                .mediaType(mediaType)
                .mediaURL(mediaURL)
                .build();
    }

    @Builder
    public ProductMediaRequestDto(Long productId, String mediaName, String mediaType, String mediaURL) {

        this.productId = productId;
        this.mediaName = mediaName;
        this.mediaType = mediaType;
        this.mediaURL = mediaURL;

    }

}
