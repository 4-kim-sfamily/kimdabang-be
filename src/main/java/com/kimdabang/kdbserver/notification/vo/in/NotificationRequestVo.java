package com.kimdabang.kdbserver.notification.vo.in;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
public class NotificationRequestVo {
    private String title;
    private Date activeDate;
    private Date expireDate;
    private String mediaUrl;

}
