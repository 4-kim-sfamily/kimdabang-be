package com.kimdabang.kdbserver.reviwe.media.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.reviwe.media.application.ReviewMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/review")
public class ReviewMediaController {

    private final ReviewMediaService reviewMediaService;

    @PostMapping(value = "/uplode",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponseEntity<String> uplode(@RequestParam("file") MultipartFile file) {
        //String url = reviewMediaService.uplodeFile(file);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "파일 업로드 성공",
                null
        );
    }
}
