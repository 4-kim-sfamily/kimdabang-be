package com.kimdabang.kdbserver.auth.infrastructure;

import com.kimdabang.kdbserver.auth.entity.OAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthRepository extends JpaRepository<OAuth, Long> {
    Optional<OAuth> findByUserUuid(String userUuid);
    Optional<OAuth> findByProviderAndProviderId(String provider, String providerId);
    Optional<OAuth> findByProviderAndUserUuid(String provider, String uuid);
}
