package com.kimdabang.kdbserver.notification.dto.out;

import com.kimdabang.kdbserver.notification.vo.out.NotificationResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDto {
    private Long id;
    private String title;
    private Date activeDate;
    private Date expireDate;
    private String mediaUrl;

    public NotificationResponseVo toResponseVo() {
        return NotificationResponseVo.builder()
                .id(id)
                .title(title)
                .activeDate(activeDate)
                .expireDate(expireDate)
                .mediaUrl(mediaUrl)
                .build();
    }
}
