package com.kimdabang.kdbserver.address.dto;

import com.kimdabang.kdbserver.address.vo.AddressResponseVo;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDto {
    private Long id;
    private String address;
    private Boolean isDefault;
    private String addressName;

    public AddressResponseVo toResponseVo() {
        return AddressResponseVo.builder()
                .id(id)
                .address(address)
                .addressName(addressName)
                .isDefault(isDefault)
                .build();
    }
}
