package com.kimdabang.kdbserver.agreement.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AgreementRequestVo {
    private Boolean termsChecked;
    private Boolean privacyChecked;
    private Boolean cardChecked;
    private Boolean emailChecked;
    private Boolean smsChecked;
}
