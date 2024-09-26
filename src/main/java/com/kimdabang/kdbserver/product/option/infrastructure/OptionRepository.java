package com.kimdabang.kdbserver.product.option.infrastructure;

import com.kimdabang.kdbserver.product.option.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findAllByProductCodeAndDepth(String productCode, int depth);
    List<Option> findAllByProductCodeAndParentOptionsId(String productCode, Option parentId);

    Optional<Option> findByProductCodeAndId(String productCode, Long optionsId);

}
