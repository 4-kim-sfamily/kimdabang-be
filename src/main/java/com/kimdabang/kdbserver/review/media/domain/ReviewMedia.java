package com.kimdabang.kdbserver.review.media.domain;

import com.kimdabang.kdbserver.review.review.domain.Review;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class ReviewMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("리뷰")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Review review;

    @Comment("리뷰 코드")
    @Column(nullable = false)
    private Long reviewCode;

    @Comment("미디어 타입")
    @Column(nullable = false)
    private String mediaType;

    @Comment("미디어 URL")
    @Column(nullable = false)
    private String mediaURL;

    @Builder
    public ReviewMedia(
            Long reviewCode,
            Review review,
            String mediaType,
            String mediaURL
    ) {
        this.reviewCode = reviewCode;
        this.review = review;
        this.mediaType = mediaType;
        this.mediaURL = mediaURL;
    }
}
