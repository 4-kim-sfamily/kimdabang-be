package com.kimdabang.kdbserver.common.entity;

import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Component
@NoArgsConstructor
public class SnowFlakeGenerator implements IdentifierGenerator {

    private static final int CASE_ONE_BITS = 3;  // 3비트로 줄임 (최대 7)
    private static final int CASE_TWO_BITS = 3;  // 3비트로 줄임 (최대 7)
    private static final int SEQUENCE_BITS = 9;  // 9자리 숫자를 위해 9비트로 설정

    private static final int maxSequence = (int) (Math.pow(2, SEQUENCE_BITS) - 1);  // 9자리 최대값
    private static final long CUSTOM_EPOCH = 1420070400000L;    // 사용자 정의 epoch (2015년 1월 1일)

    private volatile long sequence = 0L;
    private int case_one = 1;  // 최대 3비트로 표현할 값
    private int case_two = 1;  // 최대 3비트로 표현할 값
    private volatile long lastTimestamp = -1L;

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return generateUniqueId();
    }

    // 현재 날짜를 yyyyMMdd 형식으로 반환 (20240920)
    private String getDatePrefix() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    // 타임스탬프 기반의 9자리 고유 숫자 생성
    public synchronized long nextId() {
        long currentTimestamp = timestamp();

        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0) {
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;
        return makeId(currentTimestamp);
    }

    // epoch 기준 현재 타임스탬프를 계산
    private static long timestamp() {
        return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
    }

    // 9자리 고유 숫자를 생성하는 메서드
    private Long makeId(long currentTimestamp) {
        long id = 0;

        id |= (currentTimestamp << (CASE_ONE_BITS + CASE_TWO_BITS + SEQUENCE_BITS));  // 타임스탬프
        id |= (case_one << (CASE_TWO_BITS + SEQUENCE_BITS));  // case_one 비트
        id |= (case_two << SEQUENCE_BITS);  // case_two 비트
        id |= sequence;  // 시퀀스 비트

        return id % 1_000_000_000L;  // 9자리로 제한 (0 ~ 999,999,999)
    }

    // 최종 ID 생성 (날짜 + 9자리 고유 ID)
    public Long generateUniqueId() {
        String datePrefix = getDatePrefix();  // 앞 8자리 (날짜 정보)
        long uniqueNumber = nextId();         // 뒤 9자리 (고유한 숫자)

        // 날짜와 9자리 숫자를 결합하여 ID 생성
        return Long.valueOf(datePrefix + String.format("%09d", uniqueNumber));
    }

    // 동일한 타임스탬프일 경우 기다림
    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }
}
