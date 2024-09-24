package com.kimdabang.kdbserver.starbucksCard.list.application;

import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.common.exception.ErrorCode;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.starbucksCard.card.domain.StarbucksCard;
import com.kimdabang.kdbserver.starbucksCard.card.infrastructure.StarbucksCardRepository;
import com.kimdabang.kdbserver.starbucksCard.list.domain.UserStarbucksCardList;
import com.kimdabang.kdbserver.starbucksCard.list.dto.in.UserStarbucksCardListAddRequestDto;
import com.kimdabang.kdbserver.starbucksCard.list.dto.in.UserStarbucksCardListUpdateRequestDto;
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
    public void addUserStarbucksCardList(UserStarbucksCardListAddRequestDto userStarbucksCardListAddRequestDto, String Authorization) {
        String uuid = jwtTokenProvider.useToken(Authorization);

        StarbucksCard starbucksCard = starbucksCardRepository.findById(userStarbucksCardListAddRequestDto.getStarbucksCardId())
                .orElseThrow(() -> new CustomException(STARBUCKSCARD_NOT_FOUND));

        boolean alreadyEnrolled = userStarbucksCardListRepository.existsByUuidAndStarbucksCardId(uuid, userStarbucksCardListAddRequestDto.getStarbucksCardId());
        if (alreadyEnrolled) {
            throw new CustomException(STARBUCKSCARD_ALREADY_ENROLLED);
        }

        userStarbucksCardListRepository.save(userStarbucksCardListAddRequestDto.toEntity(uuid, starbucksCard));
    }

    @Override
    public void deleteUserStarbucksCardList(Long id, String Authorization) {
        String uuid = jwtTokenProvider.useToken(Authorization);

        UserStarbucksCardList userStarbucksCardList = userStarbucksCardListRepository.findByIdAndUuid(id, uuid)
                .orElseThrow(() -> new CustomException(STARBUCKSCARD_NOT_ENROLL));

        userStarbucksCardListRepository.delete(userStarbucksCardList);
    }

    @Override
    public UserStarbucksCardListResponseDto getOneUserStarbucksCardList(Long id, String Authorization) {
        String uuid = jwtTokenProvider.useToken(Authorization);
        UserStarbucksCardList userStarbucksCardList = userStarbucksCardListRepository.findByIdAndUuid(id, uuid)
                .orElseThrow(() -> new CustomException(STARBUCKSCARD_NOT_ENROLL));
        return UserStarbucksCardListResponseDto.builder()
                .id(userStarbucksCardList.getId())
                .uuid(userStarbucksCardList.getUuid())
                .starbucksCardId(userStarbucksCardList.getStarbucksCard().getId())
                .build();
    }

    @Override
    public List<UserStarbucksCardListResponseDto> getAllUserStarbucksCardList(String Authorization) {
        String uuid = jwtTokenProvider.useToken(Authorization);
        List<UserStarbucksCardList> userStarbucksCardLists = userStarbucksCardListRepository.findAllByUuid(uuid);
        return userStarbucksCardLists.stream()
                .map(userStarbucksCardList -> UserStarbucksCardListResponseDto.builder()
                        .id(userStarbucksCardList.getId())
                        .uuid(userStarbucksCardList.getUuid())
                        .starbucksCardId(userStarbucksCardList.getStarbucksCard().getId())
                        .build())
                .toList();
    }
}
