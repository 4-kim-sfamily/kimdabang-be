package com.kimdabang.kdbserver.user.infrastructure;

import com.kimdabang.kdbserver.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUuid(String uuid);
    Optional<User> findByLoginId(String loginId);
}
