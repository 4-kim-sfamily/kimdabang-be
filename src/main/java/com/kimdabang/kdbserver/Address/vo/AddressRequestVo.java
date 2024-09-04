package com.kimdabang.kdbserver.Address.vo;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AddressRequestVo {
    private String accessToken;
    private Long id;
    private String address;
    private Boolean isDefault;
    private String addressName;
}
