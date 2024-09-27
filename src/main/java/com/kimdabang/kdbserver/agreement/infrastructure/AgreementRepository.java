package com.kimdabang.kdbserver.agreement.infrastructure;

import com.kimdabang.kdbserver.agreement.domain.Agreement;
import com.kimdabang.kdbserver.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {
    Optional<Agreement> findByUser(User user);
}
