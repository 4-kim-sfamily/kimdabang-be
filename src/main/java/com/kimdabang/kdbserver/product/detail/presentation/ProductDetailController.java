package com.kimdabang.kdbserver.product.detail.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.product.detail.application.ProductDetailService;
import com.kimdabang.kdbserver.product.detail.dto.ProductDetailRequestDto;
import com.kimdabang.kdbserver.product.detail.dto.ProductDetailResponseDto;
import com.kimdabang.kdbserver.product.detail.vo.ProductDetailResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    @GetMapping("/detail/{productCode}")
    public CommonResponseEntity<ProductDetailResponseVo> getProductDetail(
            @PathVariable String productCode) {
        ProductDetailResponseDto productDetailResponseDto = productDetailService.getProductDetail(productCode);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 상세 정보 조회 성공",
                productDetailResponseDto.toResponseVo()
        );
    }
    @PostMapping("/add-detail")
    public CommonResponseEntity<ProductDetailResponseVo> addProductDetail(
            @RequestBody ProductDetailRequestDto productDetailRequestDto) {
            productDetailService.addProductDetail(productDetailRequestDto);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 상세 정보 추가 성공",
                null
        );
    }
    @PutMapping("/put-detail")
    public CommonResponseEntity<ProductDetailResponseVo> putProductDetail(
            @RequestBody ProductDetailRequestDto productDetailRequestDto) {
        productDetailService.putProductDetail(productDetailRequestDto);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 상세 정보 갱신 성공",
                null
        );
    }

}
