package com.kimdabang.kdbserver.agreement.dto;

import com.kimdabang.kdbserver.agreement.domain.Agreement;
import com.kimdabang.kdbserver.agreement.vo.AgreementRequestVo;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AgreementRequestDto {
    private Boolean termsChecked;
    private Boolean privacyChecked;
    private Boolean cardChecked;
    private Boolean emailChecked;
    private Boolean smsChecked;

    public Agreement toEntity(Agreement agreement) {
        return Agreement.builder()
                .id(agreement.getId())
                .termsChecked(termsChecked)
                .privacyChecked(privacyChecked)
                .cardChecked(cardChecked)
                .emailChecked(emailChecked)
                .smsChecked(smsChecked)
                .user(agreement.getUser())
                .build();
    }
    public static AgreementRequestDto toRequestDto(AgreementRequestVo agreementRequestVo) {
        return AgreementRequestDto.builder()
                .termsChecked(agreementRequestVo.getTermsChecked())
                .privacyChecked(agreementRequestVo.getPrivacyChecked())
                .cardChecked(agreementRequestVo.getCardChecked())
                .emailChecked(agreementRequestVo.getEmailChecked())
                .smsChecked(agreementRequestVo.getSmsChecked())
                .build();
    }
}
