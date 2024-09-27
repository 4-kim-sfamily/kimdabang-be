package com.kimdabang.kdbserver.notification.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseVo {
    private Long id;
    private String title;
    private Date activeDate;
    private Date expireDate;
    private String mediaUrl;

}
