package com.kimdabang.kdbserver.reviwe.media.application;

import com.kimdabang.kdbserver.common.entity.SnowFlakeGenerator;
import com.kimdabang.kdbserver.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewMediaServiceImpl implements ReviewMediaService {
    private final SnowFlakeGenerator snowFlakeGenerator;

    public String uplodeFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new CustomException(MEDIA_UPLODE_FAILED);
        }

        try {
            String uploadDir = "C://code/uploads/";
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            int lastIndexOfDot = originalFilename.lastIndexOf('.');
            File uploadFile = new File(uploadDir +
                    snowFlakeGenerator.generateUniqueId() + "." + originalFilename.substring(lastIndexOfDot + 1));

            // 파일이 저장될 디렉토리의 부모 디렉토리 생성
            File parentDir = uploadFile.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                boolean success = parentDir.mkdirs();

                // 폴더 생성 성공 여부 확인
                if (!success) {
                    log.error("Failed to create directory: " + parentDir.getAbsolutePath());
                    throw new CustomException(MEDIA_PROCESS_FAILED);  // 폴더 생성 실패 시 예외 처리
                }
            }

            // 파일 저장
            file.transferTo(uploadFile);
            log.info("File uploaded successfully: " + uploadFile.getName());
            return uploadFile.getName();

        } catch (Exception e) {
            log.error("File upload failed: " + e.getMessage(), e);
            throw new CustomException(MEDIA_PROCESS_FAILED);
        }
    }
}
