package com.kimdabang.kdbserver.agreement.presentation;

import com.kimdabang.kdbserver.agreement.application.AgreementService;
import com.kimdabang.kdbserver.agreement.dto.AgreementRequestDto;
import com.kimdabang.kdbserver.agreement.dto.AgreementResponseDto;
import com.kimdabang.kdbserver.agreement.vo.AgreementRequestVo;
import com.kimdabang.kdbserver.agreement.vo.AgreementResponseVo;
import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.common.entity.CommonResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class AgreementController {

    private final AgreementService agreementService;

    @Operation(summary = "AgreementGet API", description = "AgreementGet API 입니다.", tags = {"user-controller"})
    @GetMapping("/get-Agreement")
    public CommonResponseEntity<AgreementResponseVo> getAgreement(
            @RequestHeader("Authorization") String Authorization) {

        AgreementResponseDto agreementResponseDto = agreementService.getAgreement(Authorization);
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), agreementResponseDto.toResponseVo());
    }

    @Operation(summary = "AgreementPut API", description = "AgreementPut API 입니다.", tags = {"user-controller"})
    @PutMapping("/put-Agreement")
    public CommonResponseEntity<Void> putAgreement(
            @RequestHeader("Authorization") String Authorization,
            @RequestBody AgreementRequestVo agreementRequestVo) {

        agreementService.putAgreement(AgreementRequestDto.toRequestDto(agreementRequestVo), Authorization);
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }
}
