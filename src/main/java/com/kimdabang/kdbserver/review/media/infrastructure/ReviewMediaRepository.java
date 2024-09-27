package com.kimdabang.kdbserver.review.media.infrastructure;

import com.kimdabang.kdbserver.review.media.domain.ReviewMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewMediaRepository extends JpaRepository<ReviewMedia, Long> {
    Optional<ReviewMedia> findByReviewCode(Long reviewCode);
}
