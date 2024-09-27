package com.kimdabang.kdbserver.address.dto;

import com.kimdabang.kdbserver.address.domain.Address;
import com.kimdabang.kdbserver.address.vo.AddressAddRequestVo;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressAddRequestDto {
    private String address;
    private Boolean isDefault;
    private String addressName;
    private String phone;

    public static AddressAddRequestDto RequestDto(AddressAddRequestVo addressAddRequestVo) {
        return AddressAddRequestDto.builder()
                .addressName(addressAddRequestVo.getAddressName())
                .address(addressAddRequestVo.getAddress())
                .isDefault(addressAddRequestVo.getIsDefault())
                .phone(addressAddRequestVo.getPhone())
                .build();

    }

    public Address toEntity(AddressAddRequestDto addressAddRequestDto, String uuid) {
        return Address.builder()
                .address(addressAddRequestDto.getAddress())
                .isDefault(addressAddRequestDto.getIsDefault())
                .addressName(addressAddRequestDto.getAddressName())
                .userUuid(uuid)
                .phone(addressAddRequestDto.getPhone())
                .build();
    }
}
