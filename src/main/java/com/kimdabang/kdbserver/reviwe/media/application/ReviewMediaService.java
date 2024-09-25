package com.kimdabang.kdbserver.reviwe.media.application;

import org.springframework.web.multipart.MultipartFile;

public interface ReviewMediaService {

    String uplodeFile(MultipartFile file);
}
