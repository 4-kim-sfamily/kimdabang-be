package com.kimdabang.kdbserver.Address.application;

import com.kimdabang.kdbserver.Address.domain.Address;
import com.kimdabang.kdbserver.auth.infrastructure.AuthRepository;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.user.domain.User;
import com.kimdabang.kdbserver.Address.dto.AddressAddRequestDto;
import com.kimdabang.kdbserver.Address.dto.AddressRequestDto;
import com.kimdabang.kdbserver.Address.dto.AddressResponseDto;
import com.kimdabang.kdbserver.Address.infrastructure.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AuthRepository authRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AddressRepository addressRepository;

    @Override
    public List<AddressResponseDto> getUserAddress(String Authorization){
        List<Address> addresses = addressRepository.findByUserUuid(jwtTokenProvider.useToken(Authorization));
        log.info("userAddresses: {}", addresses);
        if (addresses != null) {
            return addresses.stream()
                    .map(userAddress -> AddressResponseDto.builder()
                            .id(userAddress.getId())
                            .address(userAddress.getAddress())
                            .isDefault(userAddress.getIsDefault())
                            .addressName(userAddress.getAddressName())
                            .build())
                    .toList();
        }
        return List.of();
    }
    @Override
    public void addUserAddress(AddressAddRequestDto addressAddRequestDto) {
        User user = authRepository.findByUuid(jwtTokenProvider.useToken(addressAddRequestDto.getAccessToken())).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );
        addressRepository.save(addressAddRequestDto.toEntity(addressAddRequestDto, user));
    }
    @Override
    public void putUserAddress(AddressRequestDto addressRequestDto) {
        User user = authRepository.findByUuid(jwtTokenProvider.useToken(addressRequestDto.getAccessToken())).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );

        addressRepository.save(addressRequestDto.toEntity(addressRequestDto, user));
    }
    @Override
    public void deleteUserAddress(AddressRequestDto addressRequestDto) {
        Address deleteAddress = addressRepository.findById(addressRequestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
        addressRepository.delete(deleteAddress);
    }

}
