package com.kimdabang.kdbserver.star.infrastructure;

import com.kimdabang.kdbserver.star.domain.Star;

import java.util.Date;
import java.util.List;

public interface StarRepositoryCustom {
    List<Star> getStarWithDate(String uuid, Date start, Date end);
}
