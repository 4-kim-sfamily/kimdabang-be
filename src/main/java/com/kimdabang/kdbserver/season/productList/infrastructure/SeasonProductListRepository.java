package com.kimdabang.kdbserver.season.productList.infrastructure;

import com.kimdabang.kdbserver.season.productList.domain.SeasonProductList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonProductListRepository extends JpaRepository<SeasonProductList, Long> {
}
