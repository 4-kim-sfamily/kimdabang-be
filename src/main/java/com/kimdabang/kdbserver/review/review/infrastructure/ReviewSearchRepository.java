package com.kimdabang.kdbserver.review.review.infrastructure;

import com.kimdabang.kdbserver.review.review.domain.ReviewDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ReviewSearchRepository extends ElasticsearchRepository<ReviewDocument, String> {

    List<ReviewDocument> findByNicknameContainingOrTextContaining(String nickname, String text, Pageable pageable);

}
