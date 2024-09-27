package com.kimdabang.kdbserver.address.vo;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseVo {
    private Long id;
    private String address;
    private Boolean isDefault;
    private String addressName;
    private String phone;
}
