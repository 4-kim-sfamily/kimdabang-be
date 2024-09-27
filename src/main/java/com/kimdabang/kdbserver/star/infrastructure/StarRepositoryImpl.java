package com.kimdabang.kdbserver.star.infrastructure;

import com.kimdabang.kdbserver.star.domain.QStar;
import com.kimdabang.kdbserver.star.domain.Star;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class StarRepositoryImpl implements StarRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Star> getStarWithDate(String uuid, Date start, Date end) {
        QStar star = QStar.star;

        return jpaQueryFactory.selectFrom(star)
                .where(star.createdAt.between(start, end).and(star.uuid.eq(uuid)))  // uuid 조건 추가
                .fetch();
    }
}
