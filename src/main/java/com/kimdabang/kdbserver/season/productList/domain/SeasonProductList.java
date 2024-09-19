package com.kimdabang.kdbserver.season.productList.domain;

import com.kimdabang.kdbserver.common.entity.BaseEntity;
import com.kimdabang.kdbserver.season.season.domain.Season;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class SeasonProductList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("시즌 pk")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    @Comment("상품 코드")
    @Column(nullable = false, length = 50)
    private String productCode;

    @Builder
    public SeasonProductList(Long id, Season season, String productCode) {
        this.id = id;
        this.season = season;
        this.productCode = productCode;
    }
}
