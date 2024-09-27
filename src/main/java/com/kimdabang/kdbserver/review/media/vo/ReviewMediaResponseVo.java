package com.kimdabang.kdbserver.review.media.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class ReviewMediaResponseVo {
    private String mediaType;
    private String mediaURL;
}
