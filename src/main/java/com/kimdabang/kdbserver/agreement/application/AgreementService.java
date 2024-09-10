package com.kimdabang.kdbserver.agreement.application;

import com.kimdabang.kdbserver.agreement.dto.AgreementRequestDto;
import com.kimdabang.kdbserver.agreement.dto.AgreementResponseDto;

public interface AgreementService {
    AgreementResponseDto getAgreement(String accessToken);
    void putAgreement(AgreementRequestDto agreementRequestDto, String accessToken);
}
