package com.kimdabang.kdbserver.starbucksCard.list.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardChargeRequestDto;
import com.kimdabang.kdbserver.starbucksCard.card.dto.in.StarbucksCardUseRequestDto;
import com.kimdabang.kdbserver.starbucksCard.list.application.UserStarbucksCardListService;
import com.kimdabang.kdbserver.starbucksCard.list.dto.in.UserStarbucksCardListAddRequestDto;
import com.kimdabang.kdbserver.starbucksCard.list.dto.out.UserStarbucksCardListResponseDto;
import com.kimdabang.kdbserver.starbucksCard.list.vo.UserStarbucksCardChargeRequestVo;
import com.kimdabang.kdbserver.starbucksCard.list.vo.UserStarbucksCardListAddRequestVo;
import com.kimdabang.kdbserver.starbucksCard.list.vo.UserStarbucksCardListResponseVo;
import com.kimdabang.kdbserver.starbucksCard.list.vo.UserStarbucksCardUseRequestVo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-starbucks-card-list")
public class UserStarbucksCardListController {

    private final UserStarbucksCardListService userStarbucksCardListService;

    @PostMapping
    public CommonResponseEntity<Void> createUserStarbucksCardList(
            @RequestHeader("Authorization") String Authorization,
            @RequestBody UserStarbucksCardListAddRequestVo userStarbucksCardListAddRequestVo) {
        log.info("userStarbucksCardListAddRequestVo = {}", userStarbucksCardListAddRequestVo);
        UserStarbucksCardListAddRequestDto userStarbucksCardListAddRequestDto = UserStarbucksCardListAddRequestDto.builder()
                .starbucksCardId(userStarbucksCardListAddRequestVo.getStarbucksCardId())
                .build();
        userStarbucksCardListService.addUserStarbucksCardList(userStarbucksCardListAddRequestDto, Authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "유저 스타벅스카드 등록 성공",
                null
        );
    }

    @DeleteMapping
    public CommonResponseEntity<Void> deleteUserStarbucksCardList(
            @RequestParam Long id,
            @RequestHeader("Authorization") String Authorization) {
        log.info("userStarbucksCardListId = {}", id);
        userStarbucksCardListService.deleteUserStarbucksCardList(id, Authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "유저 스타벅스카드 삭제 성공",
                null
        );
    }

    @GetMapping
    public CommonResponseEntity<List<UserStarbucksCardListResponseVo>> getAllUserStarbucksCardList(@RequestHeader("Authorization") String Authorization) {
        List<UserStarbucksCardListResponseDto> userStarbucksCardListResponseDtos = userStarbucksCardListService.getAllUserStarbucksCardList(Authorization);
        List<UserStarbucksCardListResponseVo> userStarbucksCardListResponseVos = userStarbucksCardListResponseDtos.stream()
                .map(UserStarbucksCardListResponseDto::toResponseVo)
                .toList();
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "유저 스타벅스카드 전체 조회 성공",
                userStarbucksCardListResponseVos
        );
    }

    @GetMapping("/{id}")
    public CommonResponseEntity<UserStarbucksCardListResponseVo> getOneUserStarbucksCardList(
            @PathVariable Long id,
            @RequestHeader("Authorization") String Authorization) {
        UserStarbucksCardListResponseDto userStarbucksCardListDto = userStarbucksCardListService.getOneUserStarbucksCardList(id, Authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "유저 스타벅스카드 조회 성공",
                userStarbucksCardListDto.toResponseVo()
        );
    }

    @PutMapping("/charge")
    public CommonResponseEntity<Void> ChargeStarbucksCard(
            @RequestHeader("Authorization") String Authorization,
            @RequestBody UserStarbucksCardChargeRequestVo userStarbucksCardChargeRequestVo) {
        log.info("userStarbucksCardChargeRequestVo = {}", userStarbucksCardChargeRequestVo);
        StarbucksCardChargeRequestDto starbucksCardChargeRequestDto = StarbucksCardChargeRequestDto.builder()
                .id(userStarbucksCardChargeRequestVo.getStarbucksCardId())
                .charge(userStarbucksCardChargeRequestVo.getCharge())
                .build();
        userStarbucksCardListService.chargeStarbucksCard(userStarbucksCardChargeRequestVo.getId(), starbucksCardChargeRequestDto, Authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "유저 스타벅스카드 충전 성공",
                null
        );
    }

    @PutMapping("/use")
    public CommonResponseEntity<Void> UseStarbucksCard(
            @RequestHeader("Authorization") String Authorization,
            @RequestBody UserStarbucksCardUseRequestVo userStarbucksCardUseRequestVo) {
        log.info("userStarbucksCardUseRequestVo = {}", userStarbucksCardUseRequestVo);
        StarbucksCardUseRequestDto starbucksCardUseRequestDto = StarbucksCardUseRequestDto.builder()
                .id(userStarbucksCardUseRequestVo.getStarbucksCardId())
                .charge(userStarbucksCardUseRequestVo.getCharge())
                .build();
        userStarbucksCardListService.useStarbucksCard(userStarbucksCardUseRequestVo.getId(), starbucksCardUseRequestDto, Authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "유저 스타벅스카드 사용 성공",
                null
        );
    }
}
