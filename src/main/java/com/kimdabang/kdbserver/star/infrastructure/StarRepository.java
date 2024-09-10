package com.kimdabang.kdbserver.star.infrastructure;

import com.kimdabang.kdbserver.star.domain.Star;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StarRepository extends JpaRepository<Star, Long> {
    List<Star> findByUuid(String uuid);
    }
