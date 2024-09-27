package com.kimdabang.kdbserver.notification.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.notification.domain.Notification;
import com.kimdabang.kdbserver.notification.dto.in.NotificationRequestDto;
import com.kimdabang.kdbserver.notification.dto.out.NotificationResponseDto;
import com.kimdabang.kdbserver.notification.infrastructure.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.DATA_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;

    @Override
    public List<NotificationResponseDto> getNotification() {
        Date now = new Date();
        Sort sort = Sort.by(Sort.Direction.ASC, "activeDate");

        List<Notification> notificationList = notificationRepository.findByActiveDateBeforeAndExpireDateAfter(now, now, sort);
        log.info("notificationList: {}", notificationList);
        if (notificationList != null) {

            return notificationList.stream()
                    .map(notification -> {
                        return NotificationResponseDto.builder()
                                .id(notification.getId())
                                .title(notification.getTitle())
                                .activeDate(notification.getActiveDate())
                                .expireDate(notification.getExpireDate())
                                .mediaUrl(notification.getMediaUrl())
                                .build();
                    })
                    .toList();
        }
        return List.of();
    }

    @Override
    public void addNotification(NotificationRequestDto notificationRequestDto) {
        notificationRepository.save(notificationRequestDto.toEntity());
    }

    @Override
    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new CustomException(DATA_NOT_FOUND));
        notificationRepository.delete(notification);
    }
}
