package com.kimdabang.kdbserver.orders.purchase.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.orders.purchase.application.PurchaseService;
import com.kimdabang.kdbserver.orders.purchase.dto.in.PurchaseRequestDto;
import com.kimdabang.kdbserver.orders.purchase.dto.out.PurchaseDetailResponseDto;
import com.kimdabang.kdbserver.orders.purchase.dto.out.PurchaseResponseDto;
import com.kimdabang.kdbserver.orders.purchase.vo.in.PurchaseRequestVo;
import com.kimdabang.kdbserver.orders.purchase.vo.out.PurchaseDetailResponseVo;
import com.kimdabang.kdbserver.orders.purchase.vo.out.PurchaseResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping("/add-purchase")
    public CommonResponseEntity<Void> addPurchase(
            @RequestHeader ("Authorization") String authorization,
            @RequestBody PurchaseRequestVo purchaseRequestVo) {
        purchaseService.addPurchase(PurchaseRequestDto.toRequestDto(purchaseRequestVo), authorization);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "주문 성공",
                null
        );
    }
    @GetMapping("/get-purchaselist")
    public CommonResponseEntity<List<PurchaseResponseVo>> getPurchaseList(
            @RequestHeader ("Authorization") String authorization,
            @RequestParam(value = "start") String start, @RequestParam(value = "end") String end) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
        start = start+"-00:00:00";
        end = end+"-23:59:59";
        Date startDate = formatter.parse(start);
        Date endDate = formatter.parse(end);
        List<PurchaseResponseDto> purchaseResponseDto = purchaseService.getPurchaseList(startDate, endDate, authorization);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "주문 리스트 조회 성공",
                purchaseResponseDto.stream()
                        .map(PurchaseResponseDto::toResponseVo)
                        .toList()
        );
    }
    @GetMapping("/get-purchase")
    public CommonResponseEntity<PurchaseDetailResponseVo> getPurchase(
            @RequestParam(value = "purchaseCode") Long purchaseCode) throws ParseException {
        PurchaseDetailResponseDto purchaseDetailResponseDto = purchaseService.getPurchase(purchaseCode);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "주문 세부 정보 조회 성공",
                purchaseDetailResponseDto.toResponseVo()
        );
    }
}
