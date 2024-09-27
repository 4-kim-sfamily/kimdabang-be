package com.kimdabang.kdbserver.star.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import java.util.Date;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class Star {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("유효기간")
    @Column(nullable = false, length = 20)
    private Date expirationDate;

    @Comment("적립일자")
    @Column(nullable = false, length = 20)
    private Date createdAt;

    @Comment("에코별 여부")
    @Column(nullable = false)
    private Boolean isEcho;

    @Comment("고객 UUID")
    @Column(nullable = false, length = 100)
    private String uuid;

    @Comment("사용 여부")
    @Column(nullable = false)
    private Boolean isUsed;

    @Comment("사용 일자")
    private Date usedAt;

    @Comment("적립 경로")
    @Column(nullable = false, length = 100)
    private String description;

    @Builder
    public Star(
            Long id,
            Date expirationDate,
            Date createdAt,
            Boolean isEcho,
            String uuid,
            Boolean isUsed,
            Date usedAt,
            String description

    ) {
        this.id = id;
        this.expirationDate = expirationDate;
        this.createdAt = createdAt;
        this.isEcho = isEcho;
        this.uuid = uuid;
        this.isUsed = isUsed;
        this.usedAt = usedAt;
        this.description = description;
    }
}

