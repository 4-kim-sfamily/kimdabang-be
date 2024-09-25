package com.kimdabang.kdbserver.reviwe.reviwe.infrastructure;

import com.kimdabang.kdbserver.reviwe.reviwe.domain.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReviewCode(Long reviewCode);
    List<Review> findByUuidAndCreationDateBetween(String uuid, Date startDate, Date endDate);
    List<Review> findByProductCode(String productCode, Pageable pageable);
}
