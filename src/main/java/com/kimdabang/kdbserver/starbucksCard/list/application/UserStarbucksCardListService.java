package com.kimdabang.kdbserver.starbucksCard.list.application;

import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardChargeRequestDto;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardUseRequestDto;
import com.kimdabang.kdbserver.starbucksCard.list.dto.in.UserStarbucksCardListAddRequestDto;
import com.kimdabang.kdbserver.starbucksCard.list.dto.out.UserStarbucksCardListResponseDto;

import java.util.List;

public interface UserStarbucksCardListService {
    void addUserStarbucksCardList(UserStarbucksCardListAddRequestDto userStarbucksCardListAddRequestDto, String Authorization);
    void deleteUserStarbucksCardList(Long id, String Authorization);
    UserStarbucksCardListResponseDto getOneUserStarbucksCardList(Long id, String Authorization);
    List<UserStarbucksCardListResponseDto> getAllUserStarbucksCardList(String Authorization);
    void chargeStarbucksCard(Long id, StarbucksCardChargeRequestDto starbucksCardChargeRequestDto, String Authorization);
    void useStarbucksCard(Long id, StarbucksCardUseRequestDto starbucksCardUseRequestDto, String Authorization);
}
