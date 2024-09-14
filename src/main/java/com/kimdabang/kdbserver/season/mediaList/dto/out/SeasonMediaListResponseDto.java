package com.kimdabang.kdbserver.season.mediaList.dto.out;

import com.kimdabang.kdbserver.season.mediaList.domain.SeasonMediaList;
import com.kimdabang.kdbserver.season.mediaList.vo.SeasonMediaListResponseVo;
import com.kimdabang.kdbserver.season.season.domain.Season;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SeasonMediaListResponseDto {
    private Long id;
    private Season season;
    private String mediaURL;
    private String imageName;
    private String mediaType;

    public SeasonMediaListResponseVo toResponseVo() {
        return SeasonMediaListResponseVo.builder()
                .id(id)
                .season(season)
                .mediaURL(mediaURL)
                .imageName(imageName)
                .mediaType(mediaType)
                .build();
    }

    @Builder
    public SeasonMediaListResponseDto(Long id, Season season, String mediaURL, String imageName, String mediaType) {
        this.id = id;
        this.season = season;
        this.mediaURL = mediaURL;
        this.imageName = imageName;
        this.mediaType = mediaType;
    }
}
