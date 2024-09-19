package com.kimdabang.kdbserver.notification.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.notification.application.NotificationService;
import com.kimdabang.kdbserver.notification.dto.in.NotificationRequestDto;
import com.kimdabang.kdbserver.notification.dto.out.NotificationResponseDto;
import com.kimdabang.kdbserver.notification.vo.in.NotificationRequestVo;
import com.kimdabang.kdbserver.notification.vo.out.NotificationResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary = "NotificationGet API", description = "NotificationGet API 입니다.", tags = {"Notification-controller"})
    @GetMapping("/get-Notification")
    public CommonResponseEntity<List<NotificationResponseVo>> getNotification(
            ) throws ParseException {
        List<NotificationResponseDto> notificationResponseDtoList =
                notificationService.getNotification();
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "공지 조회 성공",
                notificationResponseDtoList.stream()
                        .map(NotificationResponseDto::toResponseVo)
                        .toList()
        );
    }

    @Operation(summary = "NotificationAdd API", description = "NotificationAdd API 입니다.", tags = {"Notification-controller"})
    @PostMapping("/add-Notification")
    public CommonResponseEntity<Void> addNotification(
            @RequestBody NotificationRequestVo notificationRequestVo) {
        NotificationRequestDto notificationRequestDto = NotificationRequestDto.builder()
                .title(notificationRequestVo.getTitle())
                .expireDate(notificationRequestVo.getExpireDate())
                .activeDate(notificationRequestVo.getActiveDate())
                .mediaUrl(notificationRequestVo.getMediaUrl())
                .build();
        notificationService.addNotification(notificationRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "공지 추가 성공",
                null
        );
    }

    @Operation(summary = "NotificationGet API", description = "NotificationGet API 입니다.", tags = {"Notification-controller"})
    @DeleteMapping("/delete-Notification")
    public CommonResponseEntity<Void> deleteNotification(
            @RequestParam(value = "id") Long id) {
            //@RequestBody NotificationRequestVo notificationRequestVo) {
        notificationService.deleteNotification(id);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "공지 삭제 성공",
                null
        );
    }
}
