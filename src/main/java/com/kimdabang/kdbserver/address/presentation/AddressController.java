package com.kimdabang.kdbserver.address.presentation;


import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.common.entity.CommonResponseMessage;
import com.kimdabang.kdbserver.address.application.AddressService;
import com.kimdabang.kdbserver.address.dto.AddressAddRequestDto;
import com.kimdabang.kdbserver.address.dto.AddressRequestDto;
import com.kimdabang.kdbserver.address.dto.AddressResponseDto;
import com.kimdabang.kdbserver.address.vo.AddressAddRequestVo;
import com.kimdabang.kdbserver.address.vo.AddressRequestVo;
import com.kimdabang.kdbserver.address.vo.AddressResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public CommonResponseEntity<Void> addAddress(
            @RequestHeader ("Authorization") String Authorization,
            @RequestBody AddressAddRequestVo addressAddRequestVo) {
        addressService.addAddress(AddressAddRequestDto.RequestDto(addressAddRequestVo), Authorization);
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }

    @Operation(summary = "UserAddressListGet API", description = "UserAddressListGet API 입니다.", tags = {"useraddress-controller"})
    @GetMapping("/get-useraddresslist")
    public CommonResponseEntity<List<AddressResponseVo>> getAddressList(
            @RequestHeader ("Authorization") String Authorization) {
        List<AddressResponseDto> addressResponseDtoList =
                addressService.getAddressList(Authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "useraddress 조회 성공",
                addressResponseDtoList.stream()
                        .map(AddressResponseDto::toResponseVo)
                        .toList()
        );
    }
    @Operation(summary = "UserAddressDefaultGet API", description = "UserAddressDefaultGet API 입니다.", tags = {"useraddress-controller"})
    @GetMapping("/get-useraddressdefault")
    public CommonResponseEntity<AddressResponseVo> getAddressDefault(
            @RequestHeader ("Authorization") String Authorization) {
        AddressResponseDto addressResponseDto = addressService.getAddressDefault(Authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "useraddress 조회 성공",
                addressResponseDto.toResponseVo());
    }
    @Operation(summary = "UserAddressGet API", description = "UserAddressGet API 입니다.", tags = {"useraddress-controller"})
    @GetMapping("/get-useraddress")
    public CommonResponseEntity<AddressResponseVo> getAddress(
            @RequestHeader ("Authorization") String Authorization,
            @RequestParam(value = "id") Long id ) {
        AddressResponseDto addressResponseDto = addressService.getAddress(id, Authorization);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "useraddress 조회 성공",
                addressResponseDto.toResponseVo());
    }

    @Operation(summary = "UserAddressPut API", description = "UserAddressPut API 입니다.", tags = {"useraddress-controller"})
    @PutMapping("/put-useraddress")
    public CommonResponseEntity<Void> putAddress(
            @RequestHeader ("Authorization") String Authorization,
            @RequestBody AddressRequestVo addressRequestVo) {
        addressService.putAddress(AddressRequestDto.RequestDto(addressRequestVo), Authorization);
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }
    @Operation(summary = "UserAddressDelete API", description = "UserAddressDelete API 입니다.", tags = {"useraddress-controller"})
    @DeleteMapping("/delete-useraddress")
    public CommonResponseEntity<Void> deleteAddress(
            @RequestHeader ("Authorization") String Authorization,
            @RequestParam(value = "id") Long id) {
        addressService.deleteAddress(id, Authorization);
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }
}
