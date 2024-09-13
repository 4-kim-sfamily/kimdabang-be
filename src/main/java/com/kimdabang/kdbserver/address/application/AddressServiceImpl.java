package com.kimdabang.kdbserver.address.application;

import com.kimdabang.kdbserver.address.domain.Address;
import com.kimdabang.kdbserver.auth.infrastructure.AuthRepository;
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

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AddressRepository addressRepository;

    @Override
    public AddressResponseDto getAddress(Long id, String Authorization){
        String uuid = jwtTokenProvider.useToken(Authorization);
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("주소를 찾을 수 없습니다."));
        log.info("address: {}", address);
        if(address.getUserUuid().equals(uuid)) {
            return AddressResponseDto.toResponseDto(address);
        }
        else { return null; }
    }
    @Override
    public List<AddressResponseDto> getAddressList(String Authorization){
        List<Address> addresses = addressRepository.findByUserUuid(jwtTokenProvider.useToken(Authorization));
        log.info("userAddresses: {}", addresses);
        if (addresses != null) {
            return addresses.stream()
                    .map(AddressResponseDto::toResponseDto)
                    .toList();
        }
        return List.of();
    }
    @Override
    public AddressResponseDto getAddressDefault(String Authorization){
        List<Address> addresses = addressRepository.findByUserUuid(jwtTokenProvider.useToken(Authorization));
        Address defaultAddress = addresses.stream().filter(address  -> address.getIsDefault().equals(true)).findFirst()  // 첫 번째 값을 가져옵니다.
                .orElseThrow(() -> new IllegalArgumentException("기본 주소가 없습니다."));  // 기본 주소가 없을 경우 예외 처리
            return AddressResponseDto.toResponseDto(defaultAddress);
    }
    @Override
    public void addAddress(AddressAddRequestDto addressAddRequestDto, String Authorization) {
        String uuid = jwtTokenProvider.useToken(Authorization);

        Address address = addressAddRequestDto.toEntity(addressAddRequestDto, uuid);
        Optional<Address> defaultAddress = addressRepository.findByUserUuidAndIsDefault(jwtTokenProvider.useToken(Authorization), true);
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
    public void putAddress(AddressRequestDto addressRequestDto, String Authorization) {
        String uuid = jwtTokenProvider.useToken(Authorization);

        Address address = addressRepository.findById(addressRequestDto.getId()).orElseThrow();

        if(addressRequestDto.getIsDefault()) { //기본 주소 갱신 처리
            Optional<Address> defaultAddress = addressRepository.findByUserUuidAndIsDefault(jwtTokenProvider.useToken(Authorization), true);

            if(defaultAddress.isPresent() && !(addressRequestDto.getId().equals(defaultAddress.get().getId()))) {
                defaultAddress.get().updateIsDefault(false);
                addressRepository.save(defaultAddress.get());
            }
        }
        else {
            if(address.getIsDefault()) { //기본 주소 비활성화 예외 처리
                throw new IllegalArgumentException("기본 주소는 비활성화 할 수 없습니다.");
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
            //기본 주소 자동 갱신 처리
//            Address address = addressRepository.findByUserUuidAndIsDefault(uuid, false)
//                    .stream().findFirst().orElseThrow(null);
//            address.updateIsDefault(true);
//            addressRepository.save(address);

            //기본 주소 삭제 예외 처리
            throw new IllegalArgumentException("기본 주소는 삭제 할 수 없습니다.");
        }
        if(deleteAddress.getUserUuid().equals(uuid)) {
            addressRepository.delete(deleteAddress);
        }
        else {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }
    }
}
