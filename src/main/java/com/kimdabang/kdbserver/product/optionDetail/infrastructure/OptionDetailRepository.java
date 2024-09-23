package com.kimdabang.kdbserver.product.optionDetail.infrastructure;

import com.kimdabang.kdbserver.product.optionDetail.domain.OptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionDetailRepository extends JpaRepository<OptionDetail, Long> {

    Optional<OptionDetail> findByProductCodeAndOptionId(String productCode, Long optionId);

}
