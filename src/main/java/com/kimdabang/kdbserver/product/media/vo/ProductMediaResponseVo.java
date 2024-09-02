package com.kimdabang.kdbserver.product.media.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMediaResponseVo {

    private Long id;
    private Long productId;
    private String mediaName;
    private String mediaType;
    private String mediaURL;

}