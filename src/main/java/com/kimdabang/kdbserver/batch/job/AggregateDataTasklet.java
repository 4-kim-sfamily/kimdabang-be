package com.kimdabang.kdbserver.batch.job;

import com.kimdabang.kdbserver.favorite.infrastructure.FavoriteRepository;
import com.kimdabang.kdbserver.orders.purchaseitem.infrastructure.PurchaseItemRepository;
import com.kimdabang.kdbserver.product.product.domain.Product;
import com.kimdabang.kdbserver.product.product.infrastructure.ProductRepository;
import com.kimdabang.kdbserver.product.score.domain.ProductScore;
import com.kimdabang.kdbserver.product.score.infrastructure.ProductScoreRepository;
import com.kimdabang.kdbserver.review.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AggregateDataTasklet implements Tasklet {

    private final ProductRepository productRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteRepository favoriteRepository;
    private final ProductScoreRepository productScoreRepository;

    // 예를 들어 전체 평균 점수를 3.5로 설정 (다른 방식으로 계산할 수도 있음)
    private static final double C = 3.5;

    // 보정을 위한 기준 리뷰 수
    private static final int M = 10;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<Product> products = productRepository.findAll();  // 모든 상품을 가져옴

        for (Product product : products) {
            String productCode = product.getProductCode();

            // 주문수 집계
            int purchaseCount = purchaseItemRepository.countByProductCode(productCode);

            // 리뷰 점수 평균 집계 (평균 값이 null일 수 있으므로 null 처리)
            Double averageScore = reviewRepository.findAverageRatingByProductCode(productCode);
            if (averageScore == null) {
                averageScore = 0.0;
            }
            int reviewCount = reviewRepository.countByProductCode(productCode);

            double adjustedScore = ((double) reviewCount / (reviewCount + M)) * averageScore
                    + ((double) M / (reviewCount + M)) * C;
            // 좋아요 수 집계
            int favoriteCount = favoriteRepository.countByProductCode(productCode);

            // 스코어 계산 (가중치를 활용한 점수 계산)
            double score = (purchaseCount * 0.3) + (adjustedScore * 0.4) + (favoriteCount * 0.3);

            // 스코어 테이블에 저장
            Optional<ProductScore> findScore = productScoreRepository.findByProductCode(productCode);
            if(findScore.isPresent()){
                ProductScore updateScore = ProductScore.builder()
                        .id(findScore.get().getId())
                        .categoryId(findScore.get().getCategoryId())
                        .productCode(findScore.get().getProductCode())
                        .purchaseCount(purchaseCount)
                        .reviewCount(reviewCount)
                        .rating(averageScore)
                        .favoriteCount(favoriteCount)
                        .score(score)
                        .build();
                productScoreRepository.save(updateScore);
            }
            else{
                ProductScore updateScore = ProductScore.builder()
                        .productCode(productCode)
                        .categoryId(product.getCategoryId())
                        .purchaseCount(purchaseCount)
                        .reviewCount(reviewCount)
                        .rating(averageScore)
                        .favoriteCount(favoriteCount)
                        .score(score)
                        .build();
                productScoreRepository.save(updateScore);
            }
        }

        return RepeatStatus.FINISHED;
    }
}