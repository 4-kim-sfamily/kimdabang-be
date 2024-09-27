package com.kimdabang.kdbserver.review.media.application;

import com.kimdabang.kdbserver.review.media.dto.ReviewMediaResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface ReviewMediaService {
    String uplodeFile(MultipartFile file);
    ReviewMediaResponseDto getMedia(Long reviewCode);
}
