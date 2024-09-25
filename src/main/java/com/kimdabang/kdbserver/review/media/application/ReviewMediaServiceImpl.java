package com.kimdabang.kdbserver.review.media.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.kimdabang.kdbserver.common.entity.SnowFlakeGenerator;
import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.review.media.domain.ReviewMedia;
import com.kimdabang.kdbserver.review.media.dto.ReviewMediaResponseDto;
import com.kimdabang.kdbserver.review.media.infrastructure.ReviewMediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewMediaServiceImpl implements ReviewMediaService {
    private final SnowFlakeGenerator snowFlakeGenerator;
    private final ReviewMediaRepository reviewMediaRepository;

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}") // YAML 파일에서 버킷 이름 주입
    private String bucketName;
    @Value("${cloud.aws.s3.imagedns}") // YAML 파일에서 버킷 이름 주입
    private String imageDns;

    public String uplodeFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new CustomException(MEDIA_UPLODE_FAILED);
        }
        try {
            String originalFilename = file.getOriginalFilename();
            int lastIndexOfDot = originalFilename.lastIndexOf('.');
            String fileName = "review/" + snowFlakeGenerator.generateUniqueId()
                    + "." + originalFilename.substring(lastIndexOfDot + 1);

            InputStream inputStream = file.getInputStream();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            amazonS3.putObject(bucketName, fileName, inputStream, metadata);

            return imageDns+fileName; // 업로드한 파일의 URL 반환

        } catch (Exception e) {
            log.error("File upload failed: " + e.getMessage(), e);
            throw new CustomException(MEDIA_PROCESS_FAILED);
        }
    }
    public ReviewMediaResponseDto getMedia(Long reviewCode) {
        ReviewMedia reviewMedia = reviewMediaRepository.findByReviewCode(reviewCode).orElseThrow(
            () -> new CustomException(DATA_NOT_FOUND)
        );
        return ReviewMediaResponseDto.toResponseDto(reviewMedia);
    }
}
