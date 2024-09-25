package com.kimdabang.kdbserver.reviwe.media.infrastructure;

import com.kimdabang.kdbserver.reviwe.media.domain.ReviewMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewMediaRepository extends JpaRepository<ReviewMedia, Long> {
    List<ReviewMedia> findByReviewCode(Long reviewCode);
}
