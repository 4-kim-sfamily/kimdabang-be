package com.kimdabang.kdbserver.review.review.application;

import com.kimdabang.kdbserver.review.review.domain.ReviewPartialDocument;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewSearchService {

    public void indexReviews();
    public List<ReviewPartialDocument> searchReviews(String keyword, Pageable pageable);

}
