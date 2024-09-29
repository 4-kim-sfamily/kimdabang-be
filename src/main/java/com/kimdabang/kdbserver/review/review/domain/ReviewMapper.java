package com.kimdabang.kdbserver.review.review.domain;

import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

public class ReviewMapper {

    public static ReviewDocument toDocument(Review review) {
        return ReviewDocument.builder()
                .id(review.getId().toString())
                .purchaseCode(review.getPurchaseCode())
                .reviewCode(review.getReviewCode())
                .productCode(review.getProductCode())
                .options(review.getOptions())
                .creationDate(review.getCreationDate())
                .rating(review.getRating())
                .nickname(review.getNickname())
                .text(review.getText())
                .build();
    }
}