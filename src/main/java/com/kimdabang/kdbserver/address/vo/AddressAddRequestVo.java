package com.kimdabang.kdbserver.address.vo;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AddressAddRequestVo {
    private String address;
    private Boolean isDefault;
    private String addressName;
    private String phone;
}
