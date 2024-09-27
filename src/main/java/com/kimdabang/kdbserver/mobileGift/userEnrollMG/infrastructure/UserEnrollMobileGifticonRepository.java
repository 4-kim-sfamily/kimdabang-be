package com.kimdabang.kdbserver.mobileGift.userEnrollMG.infrastructure;

import com.kimdabang.kdbserver.mobileGift.userEnrollMG.domain.UserEnrollMobileGifticon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserEnrollMobileGifticonRepository extends JpaRepository<UserEnrollMobileGifticon, Long> {
    boolean existsByUuidAndMobileGifticonId(String uuid, Long mobileGifticonId);
    Optional<UserEnrollMobileGifticon> findByIdAndUuid(Long id, String uuid);
    List<UserEnrollMobileGifticon> findAllByUuid(String uuid);
}
