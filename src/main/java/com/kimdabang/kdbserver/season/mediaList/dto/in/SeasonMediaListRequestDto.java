package com.kimdabang.kdbserver.season.mediaList.dto.in;

import com.kimdabang.kdbserver.season.mediaList.domain.SeasonMediaList;
import com.kimdabang.kdbserver.season.mediaList.dto.out.SeasonMediaListResponseDto;
import com.kimdabang.kdbserver.season.season.domain.Season;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SeasonMediaListRequestDto {

    private Long seasonId;
    private String mediaURL;
    private String imageName;
    private String mediaType;

    public SeasonMediaList toEntity(Season season) {
        return SeasonMediaList.builder()
                .season(season)
                .mediaURL(mediaURL)
                .imageName(imageName)
                .mediaType(mediaType)
                .build();
    }

    @Builder
    public SeasonMediaListRequestDto(Long seasonId, String mediaURL, String imageName, String mediaType) {
        this.seasonId = seasonId;
        this.mediaURL = mediaURL;
        this.imageName = imageName;
        this.mediaType = mediaType;
    }
}
