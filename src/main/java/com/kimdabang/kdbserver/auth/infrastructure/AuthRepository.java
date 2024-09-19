package com.kimdabang.kdbserver.auth.infrastructure;

import com.kimdabang.kdbserver.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByUuid(String uuid);
    Optional<User> findByEmail(String email);
}
