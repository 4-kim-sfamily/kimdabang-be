package com.kimdabang.kdbserver.review.review.application;

import com.kimdabang.kdbserver.review.review.domain.Review;
import com.kimdabang.kdbserver.review.review.domain.ReviewDocument;
import com.kimdabang.kdbserver.review.review.domain.ReviewMapper;
import com.kimdabang.kdbserver.review.review.domain.ReviewPartialDocument;
import com.kimdabang.kdbserver.review.review.infrastructure.ReviewRepository;
import com.kimdabang.kdbserver.review.review.infrastructure.ReviewSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewSearchServiceImpl implements  ReviewSearchService {

    private final ReviewRepository reviewRepository;
    private final ReviewSearchRepository reviewSearchRepository;

    @Override
    public void indexReviews() {

        List<Review> reviews = reviewRepository.findAll();
        for (Review review : reviews) {
            ReviewDocument document = ReviewMapper.toDocument(review);
            reviewSearchRepository.save(document);
        }

    }

    public List<ReviewPartialDocument> searchReviews(String keyword, Pageable pageable) {

        List<ReviewDocument> searchResults = reviewSearchRepository.findByNicknameContainingOrTextContaining(keyword, keyword, pageable);

        return searchResults.stream()
                .map(review -> ReviewPartialDocument.builder()
                        .purchaseCode(review.getPurchaseCode())
                        .reviewCode(review.getReviewCode())
                        .productCode(review.getProductCode())
                        .options(review.getOptions())
                        .creationDate(review.getCreationDate())
                        .rating(review.getRating())
                        .nickname(review.getNickname())
                        .text(review.getText())
                        .build())
                .collect(Collectors.toList());

    }
}
