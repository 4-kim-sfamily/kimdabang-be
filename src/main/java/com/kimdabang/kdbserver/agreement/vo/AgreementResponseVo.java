package com.kimdabang.kdbserver.agreement.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgreementResponseVo {
    private Boolean termsChecked;
    private Boolean privacyChecked;
    private Boolean cardChecked;
    private Boolean emailChecked;
    private Boolean smsChecked;
}
