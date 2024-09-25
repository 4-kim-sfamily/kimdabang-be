package com.kimdabang.kdbserver.review.media.application;

import org.springframework.web.multipart.MultipartFile;

public interface ReviewMediaService {

    String uplodeFile(MultipartFile file);
}
