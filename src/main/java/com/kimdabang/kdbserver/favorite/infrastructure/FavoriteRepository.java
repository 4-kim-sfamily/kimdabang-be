package com.kimdabang.kdbserver.favorite.infrastructure;

import com.kimdabang.kdbserver.favorite.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Optional<Favorite> findByProductCodeAndUserUuid(String productCode, String userUuid);
    boolean existsByProductCode(String productCode);
    boolean existsByUserUuid(String userUuid);
    List<Favorite> findAllByUserUuid(String userUuid);

}
