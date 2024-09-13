package com.kimdabang.kdbserver.address.application;

import com.kimdabang.kdbserver.address.domain.Address;
import com.kimdabang.kdbserver.auth.infrastructure.AuthRepository;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.user.domain.User;
import com.kimdabang.kdbserver.address.dto.AddressAddRequestDto;
import com.kimdabang.kdbserver.address.dto.AddressRequestDto;
import com.kimdabang.kdbserver.address.dto.AddressResponseDto;
import com.kimdabang.kdbserver.address.infrastructure.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AuthRepository authRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AddressRepository addressRepository;

    @Override
    public AddressResponseDto getAddress(Long id, String Authorization){
        String uuid = jwtTokenProvider.useToken(Authorization);
        Address address = addressRepository.findById(id).orElseThrow(null);
        log.info("address: {}", address);
        if(address.getUserUuid().equals(uuid)) {
            return AddressResponseDto.builder()
                    .id(address.getId())
                    .addressName(address.getAddressName())
                    .address(address.getAddress())
                    .phone(address.getPhone())
                    .isDefault(address.getIsDefault())
                    .build();
        }
        else { return null; }
    }
    @Override
    public List<AddressResponseDto> getAddressList(String Authorization){
        List<Address> addresses = addressRepository.findByUserUuid(jwtTokenProvider.useToken(Authorization));
        log.info("userAddresses: {}", addresses);
        if (addresses != null) {
            return addresses.stream()
                    .map(userAddress -> AddressResponseDto.builder()
                            .id(userAddress.getId())
                            .address(userAddress.getAddress())
                            .isDefault(userAddress.getIsDefault())
                            .addressName(userAddress.getAddressName())
                            .phone(userAddress.getPhone())
                            .build())
                    .toList();
        }
        return List.of();
    }
    @Override
    public AddressResponseDto getAddressDefault(String Authorization){
        List<Address> addresses = addressRepository.findByUserUuid(jwtTokenProvider.useToken(Authorization));
        Address defaultAddress = addresses.stream().filter(address  -> address.getIsDefault().equals(true)).findFirst()  // 첫 번째 값을 가져옵니다.
                .orElseThrow(() -> new RuntimeException("기본 주소가 없습니다."));  // 기본 주소가 없을 경우 예외 처리
            return AddressResponseDto.builder()
                            .id(defaultAddress.getId())
                            .address(defaultAddress.getAddress())
                            .isDefault(defaultAddress.getIsDefault())
                            .addressName(defaultAddress.getAddressName())
                            .phone(defaultAddress.getPhone())
                            .build();
    }
    @Override
    public void addAddress(AddressAddRequestDto addressAddRequestDto, String Authorization) {
        String uuid = jwtTokenProvider.useToken(Authorization);
        if(addressAddRequestDto.getIsDefault()) {
            Optional<Address> defaultAddress = addressRepository.findByUserUuidAndIsDefault(jwtTokenProvider.useToken(Authorization), true);

            if(defaultAddress.isPresent()) {
                Address setDefaultAddress = Address.builder()
                        .id(defaultAddress.get().getId())
                        .userUuid(defaultAddress.get().getUserUuid())
                        .phone(defaultAddress.get().getPhone())
                        .address(defaultAddress.get().getAddress())
                        .addressName(defaultAddress.get().getAddressName())
                        .isDefault(false)
                        .build();
                addressRepository.save(setDefaultAddress);
            }
        }
        addressRepository.save(addressAddRequestDto.toEntity(addressAddRequestDto, uuid));
    }
    @Override
    public void putAddress(AddressRequestDto addressRequestDto, String Authorization) {
        String uuid = jwtTokenProvider.useToken(Authorization);
        if(addressRequestDto.getIsDefault()) {
            Optional<Address> defaultAddress = addressRepository.findByUserUuidAndIsDefault(jwtTokenProvider.useToken(Authorization), true);

            if(defaultAddress.isPresent() && !(addressRequestDto.getId().equals(defaultAddress.get().getId()))) {
                Address setDefaultAddress = Address.builder()
                        .id(defaultAddress.get().getId())
                        .userUuid(defaultAddress.get().getUserUuid())
                        .phone(defaultAddress.get().getPhone())
                        .address(defaultAddress.get().getAddress())
                        .addressName(defaultAddress.get().getAddressName())
                        .isDefault(false)
                        .build();
                addressRepository.save(setDefaultAddress);
            }
        }
            addressRepository.save(addressRequestDto.toEntity(addressRequestDto, uuid));
    }
    @Override
    public void deleteAddress(Long id, String Authorization) {
        String uuid = jwtTokenProvider.useToken(Authorization);
        Address deleteAddress = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 주소가 존재하지 않습니다."));
        if(deleteAddress.getIsDefault().equals(true))
        {
            Address address = addressRepository.findByUserUuidAndIsDefault(uuid, false).orElseThrow(null);
            Address setDefaultAddress = Address.builder()
                    .id(address.getId())
                    .userUuid(address.getUserUuid())
                    .phone(address.getPhone())
                    .address(address.getAddress())
                    .addressName(address.getAddressName())
                    .isDefault(true)
                    .build();
            addressRepository.save(setDefaultAddress);
        }
        if(deleteAddress.getUserUuid().equals(uuid)) {
            addressRepository.delete(deleteAddress);
        }
    }
}
