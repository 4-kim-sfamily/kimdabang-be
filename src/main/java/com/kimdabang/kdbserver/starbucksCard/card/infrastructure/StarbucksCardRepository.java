package com.kimdabang.kdbserver.starbucksCard.card.infrastructure;

import com.kimdabang.kdbserver.starbucksCard.card.domain.StarbucksCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarbucksCardRepository extends JpaRepository<StarbucksCard, Long> {
}
