package com.kimdabang.kdbserver.address.application;

import com.kimdabang.kdbserver.address.dto.AddressAddRequestDto;
import com.kimdabang.kdbserver.address.dto.AddressRequestDto;
import com.kimdabang.kdbserver.address.dto.AddressResponseDto;

import java.util.List;

public interface AddressService {
    AddressResponseDto getAddress(Long id, String Authorization);
    List<AddressResponseDto> getAddressList(String Authorization);
    AddressResponseDto getAddressDefault(String Authorization);
    void addAddress(AddressAddRequestDto addressAddRequestDto, String Authorization);
    void putAddress(AddressRequestDto addressRequestDto, String Authorization);
    void deleteAddress(Long id, String Authorization);
}
