package com.kimdabang.kdbserver.season.season.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class Season extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("시즌 이름")
    @Column(nullable = false)
    private String name;

    @Comment("시즌 설명")
    private String description;

    @Comment("시즌 시작일")
    @Column(nullable = false)
    private LocalDateTime startDate;

    @Comment("시즌 종료일")
    @Column(nullable = false)
    private LocalDateTime endDate;

    @Comment("할인율")
    @Column(nullable = false)
    private int discount;

    @Builder
    public Season(Long id, String name, String description, LocalDateTime startDate, LocalDateTime endDate, int discount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
    }
}
