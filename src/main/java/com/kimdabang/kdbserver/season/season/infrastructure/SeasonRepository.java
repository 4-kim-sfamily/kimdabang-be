package com.kimdabang.kdbserver.season.season.infrastructure;

import com.kimdabang.kdbserver.season.season.domain.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
}
