package com.kimdabang.kdbserver.address.dto;

import com.kimdabang.kdbserver.address.domain.Address;
import com.kimdabang.kdbserver.address.vo.AddressResponseVo;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDto {
    private Long id;
    private String address;
    private Boolean isDefault;
    private String addressName;
    private String phone;

    public static AddressResponseDto toResponseDto(Address address) {
        return AddressResponseDto.builder()
                .id(address.getId())
                .addressName(address.getAddressName())
                .address(address.getAddress())
                .isDefault(address.getIsDefault())
                .phone(address.getPhone())
                .build();

    }

    public AddressResponseVo toResponseVo() {
        return AddressResponseVo.builder()
                .id(id)
                .address(address)
                .addressName(addressName)
                .isDefault(isDefault)
                .phone(phone)
                .build();
    }
}
