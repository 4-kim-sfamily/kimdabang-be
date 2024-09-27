package com.kimdabang.kdbserver.agreement.dto;

import com.kimdabang.kdbserver.agreement.domain.Agreement;
import com.kimdabang.kdbserver.agreement.vo.AgreementResponseVo;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AgreementResponseDto {
    private Boolean termsChecked;
    private Boolean privacyChecked;
    private Boolean cardChecked;
    private Boolean emailChecked;
    private Boolean smsChecked;

    public AgreementResponseVo toResponseVo() {
        return AgreementResponseVo.builder()
                .termsChecked(termsChecked)
                .privacyChecked(privacyChecked)
                .cardChecked(cardChecked)
                .emailChecked(emailChecked)
                .smsChecked(smsChecked)
                .build();
    }
    public static AgreementResponseDto toResponseDto(Agreement agreement) {
        return AgreementResponseDto.builder()
                .termsChecked(agreement.getTermsChecked())
                .privacyChecked(agreement.getPrivacyChecked())
                .cardChecked(agreement.getCardChecked())
                .emailChecked(agreement.getEmailChecked())
                .smsChecked(agreement.getSmsChecked())
                .build();
    }
}
