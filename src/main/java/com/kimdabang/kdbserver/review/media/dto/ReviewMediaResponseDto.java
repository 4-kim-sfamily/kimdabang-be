package com.kimdabang.kdbserver.review.media.dto;

import com.kimdabang.kdbserver.review.media.domain.ReviewMedia;
import com.kimdabang.kdbserver.review.media.vo.ReviewMediaResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMediaResponseDto {

    private String mediaType;
    private String mediaURL;

    public static ReviewMediaResponseDto toResponseDto(ReviewMedia reviewMedia) {
        return ReviewMediaResponseDto.builder()
                .mediaType(reviewMedia.getMediaType())
                .mediaURL(reviewMedia.getMediaURL())
                .build();
    }

    public ReviewMediaResponseVo toResponseVo() {
        return ReviewMediaResponseVo.builder()
                .mediaType(mediaType)
                .mediaURL(mediaURL)
                .build();
    }
}
