package com.kimdabang.kdbserver.notification.infrastructure;

import com.kimdabang.kdbserver.notification.domain.Notification;

import org. springframework. data. domain. Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findById(Long id);
    List<Notification> findByActiveDateBeforeAndExpireDateAfter(Date today1, Date today2, Sort sort);
}
