package com.kimdabang.kdbserver.notification.application;

import com.kimdabang.kdbserver.notification.dto.in.NotificationRequestDto;
import com.kimdabang.kdbserver.notification.dto.out.NotificationResponseDto;

import java.util.List;

public interface NotificationService {
    List<NotificationResponseDto> getNotification();
    void addNotification(NotificationRequestDto notificationRequestDto);
    void deleteNotification(Long id);
}
