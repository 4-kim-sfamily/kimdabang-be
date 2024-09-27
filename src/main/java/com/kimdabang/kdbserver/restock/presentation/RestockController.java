package com.kimdabang.kdbserver.restock.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.common.entity.CommonResponseMessage;
import com.kimdabang.kdbserver.restock.application.RestockService;
import com.kimdabang.kdbserver.restock.dto.in.RestockRequestDto;
import com.kimdabang.kdbserver.restock.dto.out.RestockResponseDto;
import com.kimdabang.kdbserver.restock.vo.in.RestockRequestVo;
import com.kimdabang.kdbserver.restock.vo.out.RestockResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/restock")
public class RestockController {
    private final RestockService restockService;

    @Operation(summary = "RestockAdd API", description = "RestockAdd API 입니다.", tags = {"restock-controller"})
    @PostMapping("/add-restock")
    public CommonResponseEntity<Void> addRestock(
            @RequestHeader ("Authorization") String authorization,
            @RequestBody RestockRequestVo restockRequestVo) {
        restockService.addRestock(RestockRequestDto.toRequestDto(restockRequestVo), authorization);
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }
    @Operation(summary = "RestockGet API", description = "RestockGet API 입니다.", tags = {"restock-controller"})
    @GetMapping("/get-restock")
    public CommonResponseEntity<List<RestockResponseVo>> getStar(
            @RequestHeader ("Authorization") String authorization) throws ParseException {
        List<RestockResponseDto> restockResponseDtoList =
                restockService.getRestock(authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "Restock 조회 성공",
                restockResponseDtoList.stream()
                        .map(RestockResponseDto::toResponseVo)
                        .toList()
        );
    }
    @Operation(summary = "RestockDelete API", description = "RestockDelete API 입니다.", tags = {"restock-controller"})
    @DeleteMapping("/delete-restock")
    public CommonResponseEntity<Void> deleteRestock(
            @RequestHeader ("Authorization") String authorization,
            @RequestParam(value = "id") String productCode) {
        restockService.deleteRestock(productCode, authorization);
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }
}
