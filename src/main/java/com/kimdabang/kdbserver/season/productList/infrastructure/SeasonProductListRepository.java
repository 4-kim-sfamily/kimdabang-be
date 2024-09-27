package com.kimdabang.kdbserver.season.productList.infrastructure;

import com.kimdabang.kdbserver.season.productList.domain.SeasonProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeasonProductListRepository extends JpaRepository<SeasonProductList, Long> {
    @Query("SELECT s.productCode FROM SeasonProductList s WHERE s.season.id = :seasonId")
    List<String> findProductCodesBySeasonId(Long seasonId);
}
