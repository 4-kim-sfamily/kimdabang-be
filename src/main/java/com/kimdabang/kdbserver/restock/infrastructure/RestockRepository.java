package com.kimdabang.kdbserver.restock.infrastructure;

import com.kimdabang.kdbserver.restock.domain.Restock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestockRepository extends JpaRepository<Restock, Long> {
    List<Restock> findByUuid(String uuid);
    Optional<Restock> findByUuidAndProductCode(String uuid, String productCode);
}
