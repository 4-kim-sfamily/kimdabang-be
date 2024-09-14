package com.kimdabang.kdbserver.season.mediaList.vo;

import com.kimdabang.kdbserver.season.season.domain.Season;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SeasonMediaListResponseVo {
    private Long id;
    private Season season;
    private String mediaURL;
    private String imageName;
    private String mediaType;
}
