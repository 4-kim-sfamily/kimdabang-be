package com.kimdabang.kdbserver.notification.dto.in;

import com.kimdabang.kdbserver.notification.domain.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDto {
    private String title;
    private Date activeDate;
    private Date expireDate;
    private String mediaUrl;

    public Notification toEntity() {
        return Notification.builder()
                .title(title)
                .activeDate(activeDate)
                .expireDate(expireDate)
                .mediaUrl(mediaUrl)
                .build();
    }
}
