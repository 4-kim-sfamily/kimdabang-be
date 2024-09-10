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
    public CommonResponseEntity<Void> addAddress(
            @RequestBody AddressAddRequestVo addressAddRequestVo) {
        addressService.addAddress(new ModelMapper().map(addressAddRequestVo, AddressAddRequestDto.class));
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }

    @Operation(summary = "UserAddressGet API", description = "UserAddressGet API 입니다.", tags = {"useraddress-controller"})
    @GetMapping("/get-useraddress")
    public CommonResponseEntity<List<AddressResponseVo>> getAddress(
            @RequestHeader ("Authorization") String Authorization) {
        String token = Authorization.replace("Bearer ", "");
        List<AddressResponseDto> addressResponseDtoList =
                addressService.getAddress(token);
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
    public CommonResponseEntity<Void> putAddress(
            @RequestBody AddressRequestVo addressRequestVo) {
        addressService.putAddress(new ModelMapper().map(addressRequestVo, AddressRequestDto.class));
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }
    @Operation(summary = "UserAddressDelete API", description = "UserAddressDelete API 입니다.", tags = {"useraddress-controller"})
    @DeleteMapping("/delete-useraddress")
    public CommonResponseEntity<Void> deleteAddress(
            @RequestBody AddressRequestVo addressRequestVo) {
        addressService.deleteAddress(new ModelMapper().map(addressRequestVo, AddressRequestDto.class));
        return new CommonResponseEntity<>(HttpStatus.OK, CommonResponseMessage.SUCCESS.getMessage(), null);
    }
}
