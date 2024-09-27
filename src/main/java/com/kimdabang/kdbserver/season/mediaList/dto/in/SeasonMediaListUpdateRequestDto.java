package com.kimdabang.kdbserver.season.mediaList.dto.in;

import com.kimdabang.kdbserver.season.mediaList.domain.SeasonMediaList;
import com.kimdabang.kdbserver.season.season.domain.Season;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SeasonMediaListUpdateRequestDto {

    private Long id;
    private Long seasonId;
    private String mediaURL;
    private String imageName;
    private String mediaType;

    public SeasonMediaList toEntity(SeasonMediaList seasonMediaList) {
        return SeasonMediaList.builder()
                .id(id)
                .season(seasonMediaList.getSeason())
                .mediaURL(mediaURL)
                .imageName(imageName)
                .mediaType(mediaType)
                .build();
    }

    @Builder
    public SeasonMediaListUpdateRequestDto(Long id, Long seasonId, String mediaURL, String imageName, String mediaType) {
        this.id = id;
        this.seasonId = seasonId;
        this.mediaURL = mediaURL;
        this.imageName = imageName;
        this.mediaType = mediaType;
    }
}
