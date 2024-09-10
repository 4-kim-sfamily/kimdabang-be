package com.kimdabang.kdbserver.address.dto;

import com.kimdabang.kdbserver.address.domain.Address;
import com.kimdabang.kdbserver.user.domain.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDto {
    private String accessToken;
    private Long id;
    private String address;
    private Boolean isDefault;
    private String addressName;

    public Address toEntity(AddressRequestDto addressRequestDto, User user) {
        return Address.builder()
                .id(addressRequestDto.getId())
                .address(addressRequestDto.getAddress())
                .isDefault(addressRequestDto.getIsDefault())
                .addressName(addressRequestDto.getAddressName())
                .user(user)
                .build();
    }
}
