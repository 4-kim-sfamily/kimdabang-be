package com.kimdabang.kdbserver.address.application;

import com.kimdabang.kdbserver.address.domain.Address;
import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.common.jwt.JwtTokenProvider;
import com.kimdabang.kdbserver.address.dto.AddressAddRequestDto;
import com.kimdabang.kdbserver.address.dto.AddressRequestDto;
import com.kimdabang.kdbserver.address.dto.AddressResponseDto;
import com.kimdabang.kdbserver.address.infrastructure.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AddressRepository addressRepository;

    @Override
    public AddressResponseDto getAddress(Long id, String authorization){
        String uuid = jwtTokenProvider.useToken(authorization);
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new CustomException(DATA_NOT_FOUND));
        log.info("address: {}", address);
        if(address.getUserUuid().equals(uuid)) {
            return AddressResponseDto.toResponseDto(address);
        }
        else { throw new CustomException(BAD_USER_REQUEST); }
    }
    @Override
    public List<AddressResponseDto> getAddressList(String authorization){
        List<Address> addresses = addressRepository.findByUserUuid(jwtTokenProvider.useToken(authorization));
        log.info("userAddresses: {}", addresses);
            return addresses.stream()
                    .map(AddressResponseDto::toResponseDto)
                    .toList();
    }
    @Override
    public AddressResponseDto getAddressDefault(String authorization){
        List<Address> addresses = addressRepository.findByUserUuid(jwtTokenProvider.useToken(authorization));
        Address defaultAddress = addresses.stream().filter(address  -> address.getIsDefault().equals(true)).findFirst()  // 첫 번째 값을 가져옵니다.
                .orElseThrow(() -> new CustomException(DATA_NOT_FOUND));  // 기본 주소가 없을 경우 예외 처리
            return AddressResponseDto.toResponseDto(defaultAddress);
    }
    @Override
    public void addAddress(AddressAddRequestDto addressAddRequestDto, String authorization) {
        String uuid = jwtTokenProvider.useToken(authorization);

        Address address = addressAddRequestDto.toEntity(addressAddRequestDto, uuid);
        Optional<Address> defaultAddress = addressRepository.findByUserUuidAndIsDefault(jwtTokenProvider.useToken(authorization), true);
        if(addressAddRequestDto.getIsDefault()) { //기본 주소 갱신 처리
            if(defaultAddress.isPresent()) {
                defaultAddress.get().updateIsDefault(false);
                addressRepository.save(defaultAddress.get());
            }
        }
        else {
            if(defaultAddress.isEmpty()) { //기본 주소가 없을 때 기본 주소 처리
                address.updateIsDefault(true);
            }
        }
        addressRepository.save(address);
    }
    @Override
    public void putAddress(AddressRequestDto addressRequestDto, String authorization) {
        String uuid = jwtTokenProvider.useToken(authorization);

        Address address = addressRepository.findById(addressRequestDto.getId()).orElseThrow();

        if(addressRequestDto.getIsDefault()) { //기본 주소 갱신 처리
            Optional<Address> defaultAddress = addressRepository.findByUserUuidAndIsDefault(jwtTokenProvider.useToken(authorization), true);

            if(defaultAddress.isPresent() && !(addressRequestDto.getId().equals(defaultAddress.get().getId()))) {
                defaultAddress.get().updateIsDefault(false);
                addressRepository.save(defaultAddress.get());
            }
        }
        else {
            if(address.getIsDefault()) { //기본 주소 비활성화 예외 처리
                throw new CustomException(ADDREESS_IS_DEFAULT);
            }
        }
            addressRepository.save(addressRequestDto.toEntity(addressRequestDto, uuid));
    }
    @Override
    public void deleteAddress(Long id, String authorization) {
        String uuid = jwtTokenProvider.useToken(authorization);
        Address deleteAddress = addressRepository.findById(id)
                .orElseThrow(() -> new CustomException(DATA_NOT_FOUND));
        if(deleteAddress.getIsDefault().equals(true))
        {
            //기본 주소 자동 갱신 처리
//            Address address = addressRepository.findByUserUuidAndIsDefault(uuid, false)
//                    .stream().findFirst().orElseThrow(null);
//            address.updateIsDefault(true);
//            addressRepository.save(address);

            //기본 주소 삭제 예외 처리
            throw new CustomException(ADDREESS_IS_DEFAULT);
        }
        if(deleteAddress.getUserUuid().equals(uuid)) {
            addressRepository.delete(deleteAddress);
        }
        else {
            throw new CustomException(BAD_USER_REQUEST);
        }
    }
}
