package com.kimdabang.kdbserver.product.media.dto.out;

import com.kimdabang.kdbserver.product.media.vo.ProductMediaResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductMediaResponseDto {

    private String mediaName;
    private String mediaType;
    private String mediaURL;

    public ProductMediaResponseVo toProductMediaResponseVo() {
        return ProductMediaResponseVo.builder()
                .mediaName(mediaName)
                .mediaType(mediaType)
                .mediaURL(mediaURL)
                .build();
    }

    @Builder
    public ProductMediaResponseDto(String mediaName, String mediaType, String mediaURL) {
        this.mediaName = mediaName;
        this.mediaType = mediaType;
        this.mediaURL = mediaURL;

    }
}
