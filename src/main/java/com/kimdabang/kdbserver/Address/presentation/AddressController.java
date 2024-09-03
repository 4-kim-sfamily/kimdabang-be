package com.kimdabang.kdbserver.Address.presentation;


import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.common.entity.CommonResponseMessage;
import com.kimdabang.kdbserver.Address.application.AddressService;
import com.kimdabang.kdbserver.Address.dto.AddressAddRequestDto;
import com.kimdabang.kdbserver.Address.dto.AddressRequestDto;
import com.kimdabang.kdbserver.Address.dto.AddressResponseDto;
import com.kimdabang.kdbserver.Address.vo.AddressAddRequestVo;
import com.kimdabang.kdbserver.Address.vo.AddressRequestVo;
import com.kimdabang.kdbserver.Address.vo.AddressResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/useraddress")
public class AddressController {
    private final AddressService addressService;

    @Operation(summary = "UserAddressAdd API", description = "UserAddressAdd API 입니다.", tags = {"useraddress-controller"})
    @PostMapping("/add-useraddress")
    public CommonResponseEntity<Void> addUserAddress(
            @RequestBody AddressAddRequestVo addressAddRequestVo) {
        addressService.addUserAddress(new ModelMapper().map(addressAddRequestVo, AddressAddRequestDto.class));
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }

    @Operation(summary = "UserAddressGet API", description = "UserAddressGet API 입니다.", tags = {"useraddress-controller"})
    @GetMapping("/get-useraddress")
    public CommonResponseEntity<List<AddressResponseVo>> getUserAddress(
            @RequestHeader ("Authorization") String Authorization) {
        List<AddressResponseDto> addressResponseDtoList =
                addressService.getUserAddress(Authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "useraddress 조회 성공",
                addressResponseDtoList.stream()
                        .map(AddressResponseDto::toResponseVo)
                        .toList()
        );
    }
    @Operation(summary = "UserAddressPut API", description = "UserAddressPut API 입니다.", tags = {"useraddress-controller"})
    @PutMapping("/put-useraddress")
    public CommonResponseEntity<Void> putUserAddress(
            @RequestBody AddressRequestVo addressRequestVo) {
        addressService.putUserAddress(new ModelMapper().map(addressRequestVo, AddressRequestDto.class));
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }
    @Operation(summary = "UserAddressDelete API", description = "UserAddressDelete API 입니다.", tags = {"useraddress-controller"})
    @DeleteMapping("/delete-useraddress")
    public CommonResponseEntity<Void> deleteUserAddress(
            @RequestBody AddressRequestVo addressRequestVo) {
        addressService.deleteUserAddress(new ModelMapper().map(addressRequestVo, AddressRequestDto.class));
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }
}
