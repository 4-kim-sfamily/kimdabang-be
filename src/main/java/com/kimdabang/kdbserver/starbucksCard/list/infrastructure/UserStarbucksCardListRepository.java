package com.kimdabang.kdbserver.starbucksCard.list.infrastructure;

import com.kimdabang.kdbserver.starbucksCard.list.domain.UserStarbucksCardList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserStarbucksCardListRepository extends JpaRepository<UserStarbucksCardList, Long> {
    boolean existsByUuidAndStarbucksCardId(String uuid, Long starbucksCardId);
    Optional<UserStarbucksCardList> findByIdAndUuid(Long id, String Uuid);
    List<UserStarbucksCardList> findAllByUuid(String Uuid);
    boolean existsByIdAndUuidAndStarbucksCard_Id(Long id, String uuid, Long starbucksCardId);
}
