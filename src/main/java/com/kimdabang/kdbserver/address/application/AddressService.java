package com.kimdabang.kdbserver.address.application;

import com.kimdabang.kdbserver.address.dto.AddressAddRequestDto;
import com.kimdabang.kdbserver.address.dto.AddressRequestDto;
import com.kimdabang.kdbserver.address.dto.AddressResponseDto;

import java.util.List;

public interface AddressService {
    List<AddressResponseDto> getAddress(String Authorization);
    void addAddress(AddressAddRequestDto addressAddRequestDto);
    void putAddress(AddressRequestDto addressRequestDto);
    void deleteAddress(AddressRequestDto addressRequestDto);
}
