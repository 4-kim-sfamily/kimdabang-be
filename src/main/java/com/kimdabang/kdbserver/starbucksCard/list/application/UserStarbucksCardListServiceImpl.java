package com.kimdabang.kdbserver.starbucksCard.list.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.starbucksCard.card.domain.StarbucksCard;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardChargeRequestDto;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardUseRequestDto;
import com.kimdabang.kdbserver.starbucksCard.card.infrastructure.StarbucksCardRepository;
import com.kimdabang.kdbserver.starbucksCard.list.domain.UserStarbucksCardList;
import com.kimdabang.kdbserver.starbucksCard.list.dto.in.UserStarbucksCardListAddRequestDto;
import com.kimdabang.kdbserver.starbucksCard.list.dto.out.UserStarbucksCardListResponseDto;
import com.kimdabang.kdbserver.starbucksCard.list.infrastructure.UserStarbucksCardListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserStarbucksCardListServiceImpl implements UserStarbucksCardListService {

    private final UserStarbucksCardListRepository userStarbucksCardListRepository;
    private final StarbucksCardRepository starbucksCardRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public void addUserStarbucksCardList(UserStarbucksCardListAddRequestDto userStarbucksCardListAddRequestDto, String authorization) {
        String uuid = jwtTokenProvider.useToken(authorization);

        StarbucksCard starbucksCard = starbucksCardRepository.findById(userStarbucksCardListAddRequestDto.getStarbucksCardId())
                .orElseThrow(() -> new CustomException(STARBUCKSCARD_NOT_FOUND));

        boolean alreadyEnrolled = userStarbucksCardListRepository.existsByUuidAndStarbucksCardId(uuid, userStarbucksCardListAddRequestDto.getStarbucksCardId());
        if (alreadyEnrolled) {
            throw new CustomException(STARBUCKSCARD_ALREADY_ENROLLED);
        }

        userStarbucksCardListRepository.save(userStarbucksCardListAddRequestDto.toEntity(uuid, starbucksCard));
    }

    @Override
    @Transactional
    public void deleteUserStarbucksCardList(Long id, String authorization) {
        String uuid = jwtTokenProvider.useToken(authorization);

        UserStarbucksCardList userStarbucksCardList = userStarbucksCardListRepository.findByIdAndUuid(id, uuid)
                .orElseThrow(() -> new CustomException(STARBUCKSCARD_NOT_ENROLL));

        userStarbucksCardListRepository.delete(userStarbucksCardList);
    }

    @Override
    public UserStarbucksCardListResponseDto getOneUserStarbucksCardList(Long id, String authorization) {
        String uuid = jwtTokenProvider.useToken(authorization);
        UserStarbucksCardList userStarbucksCardList = userStarbucksCardListRepository.findByIdAndUuid(id, uuid)
                .orElseThrow(() -> new CustomException(STARBUCKSCARD_NOT_ENROLL));
        return UserStarbucksCardListResponseDto.builder()
                .id(userStarbucksCardList.getId())
                .uuid(userStarbucksCardList.getUuid())
                .starbucksCardId(userStarbucksCardList.getStarbucksCard().getId())
                .build();
    }

    @Override
    public List<UserStarbucksCardListResponseDto> getAllUserStarbucksCardList(String authorization) {
        String uuid = jwtTokenProvider.useToken(authorization);
        List<UserStarbucksCardList> userStarbucksCardLists = userStarbucksCardListRepository.findAllByUuid(uuid);
        return userStarbucksCardLists.stream()
                .map(userStarbucksCardList -> UserStarbucksCardListResponseDto.builder()
                        .id(userStarbucksCardList.getId())
                        .uuid(userStarbucksCardList.getUuid())
                        .starbucksCardId(userStarbucksCardList.getStarbucksCard().getId())
                        .build())
                .toList();
    }

    @Override
    @Transactional
    public void chargeStarbucksCard(Long id, StarbucksCardChargeRequestDto starbucksCardChargeRequestDto, String authorization) {
        String uuid = jwtTokenProvider.useToken(authorization);

        boolean isExist = userStarbucksCardListRepository.existsByIdAndUuidAndStarbucksCard_Id(id, uuid, starbucksCardChargeRequestDto.getId());
        if (!isExist) {
            throw new CustomException(STARBUCKSCARD_NOT_ENROLL);
        }

        StarbucksCard starbucksCard = starbucksCardRepository.findById(starbucksCardChargeRequestDto.getId())
                .orElseThrow(() -> new CustomException(STARBUCKSCARD_NOT_FOUND));

        starbucksCardRepository.save(starbucksCardChargeRequestDto.toEntity(starbucksCard));
    }

    @Override
    @Transactional
    public void useStarbucksCard(Long id, StarbucksCardUseRequestDto starbucksCardUseRequestDto, String authorization) {
        String uuid = jwtTokenProvider.useToken(authorization);

        boolean isExist = userStarbucksCardListRepository.existsByIdAndUuidAndStarbucksCard_Id(id, uuid, starbucksCardUseRequestDto.getId());
        if (!isExist) {
            throw new CustomException(STARBUCKSCARD_NOT_ENROLL);
        }

        StarbucksCard starbucksCard = starbucksCardRepository.findById(starbucksCardUseRequestDto.getId())
                .orElseThrow(() -> new CustomException(STARBUCKSCARD_NOT_FOUND));

        if (starbucksCard.getBalance() < starbucksCardUseRequestDto.getCharge()) {
            throw new CustomException(STARBUCKSCARD_NOT_ENOUGH_BALANCE);
        }

        starbucksCardRepository.save(starbucksCardUseRequestDto.toEntity(starbucksCard));
    }
}
