package com.kimdabang.kdbserver.address.dto;

import com.kimdabang.kdbserver.address.domain.Address;
import com.kimdabang.kdbserver.address.vo.AddressRequestVo;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {
    private Long id;
    private String address;
    private Boolean isDefault;
    private String addressName;
    private String phone;

    public static AddressRequestDto RequestDto(AddressRequestVo addressRequestVo) {
        return AddressRequestDto.builder()
                .id(addressRequestVo.getId())
                .addressName(addressRequestVo.getAddressName())
                .address(addressRequestVo.getAddress())
                .isDefault(addressRequestVo.getIsDefault())
                .phone(addressRequestVo.getPhone())
                .build();

    }

    public Address toEntity(AddressRequestDto addressRequestDto, String uuid) {
        return Address.builder()
                .id(addressRequestDto.getId())
                .address(addressRequestDto.getAddress())
                .isDefault(addressRequestDto.getIsDefault())
                .addressName(addressRequestDto.getAddressName())
                .phone(addressRequestDto.getPhone())
                .userUuid(uuid)
                .build();
    }
}
