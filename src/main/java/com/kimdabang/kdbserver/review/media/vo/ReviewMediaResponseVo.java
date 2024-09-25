package com.kimdabang.kdbserver.review.media.vo;

import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class ReviewMediaResponseVo {
    private String mediaType;
    private String mediaURL;
}
