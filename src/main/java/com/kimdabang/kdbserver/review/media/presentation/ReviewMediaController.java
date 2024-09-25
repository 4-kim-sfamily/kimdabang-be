package com.kimdabang.kdbserver.review.media.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.review.media.application.ReviewMediaService;
import com.kimdabang.kdbserver.review.media.dto.ReviewMediaResponseDto;
import com.kimdabang.kdbserver.review.media.vo.ReviewMediaResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
public class ReviewMediaController {

    private final ReviewMediaService reviewMediaService;

    @PostMapping(value = "/uplode",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponseEntity<String> uplode(@RequestParam("file") MultipartFile file) {
        String url = reviewMediaService.uplodeFile(file);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "파일 업로드 성공",
                url
        );
    }
    @GetMapping("/get-reviewmedia")
    public CommonResponseEntity<ReviewMediaResponseVo> getReviewMedia(
            @RequestParam(value = "reviewCode") Long reviewCode) throws ParseException {
        ReviewMediaResponseDto reviewMediaResponseDto = reviewMediaService.getMedia(reviewCode);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "내 리뷰 리스트 조회 성공",
                reviewMediaResponseDto.toResponseVo()
        );
    }
}
