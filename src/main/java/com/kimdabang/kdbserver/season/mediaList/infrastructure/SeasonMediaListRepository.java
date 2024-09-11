package com.kimdabang.kdbserver.season.mediaList.infrastructure;

import com.kimdabang.kdbserver.season.mediaList.domain.SeasonMediaList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonMediaListRepository extends JpaRepository<SeasonMediaList, Long> {
}
