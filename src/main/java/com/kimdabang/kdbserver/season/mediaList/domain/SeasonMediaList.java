package com.kimdabang.kdbserver.season.mediaList.domain;

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
public class SeasonMediaList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("시즌 pk")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    @Comment("미디어 경로 URL")
    @Column(nullable = false)
    private String mediaURL;

    @Comment("미디어 이름")
    @Column(nullable = false)
    private String imageName;

    @Comment("미디어 타입")
    @Column(nullable = false)
    private String mediaType;

    @Builder
    public SeasonMediaList(Long id, Season season, String mediaURL, String imageName, String mediaType) {
        this.id = id;
        this.season = season;
        this.mediaURL = mediaURL;
        this.imageName = imageName;
        this.mediaType = mediaType;
    }
}
