package com.kimdabang.kdbserver.review.review.infrastructure;

import com.kimdabang.kdbserver.review.review.domain.Review;
import com.kimdabang.kdbserver.review.review.domain.ReviewDocument;
import com.kimdabang.kdbserver.review.review.vo.out.ReviewResponseVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReviewCode(Long reviewCode);
    Optional<Review> findByReviewCodeAndUuid(Long reviewCode, String uuid);
    Optional<Review> findByPurchaseCodeAndUuid(Long purchaseCode, String uuid);
    List<Review> findByUuidAndCreationDateBetween(String uuid, Date startDate, Date endDate);
    Page<Review> findByProductCode(String productCode, Pageable pageable);
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.productCode = :productCode")
    Double findAverageRatingByProductCode(@Param("productCode") String productCode);
    Integer countByProductCode(String productCode);

    //검색 성능 테스트
    List<Review> findByNicknameContainingOrTextContaining(String nickname, String text, Pageable pageable);
}
