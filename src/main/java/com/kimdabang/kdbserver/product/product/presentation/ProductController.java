package com.kimdabang.kdbserver.product.product.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.product.product.application.ProductService;
import com.kimdabang.kdbserver.product.product.dto.in.ProductRequestDto;
import com.kimdabang.kdbserver.product.product.dto.out.ProductResponseDto;
import com.kimdabang.kdbserver.product.product.vo.ProductRequestVo;
import com.kimdabang.kdbserver.product.product.vo.ProductResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public CommonResponseEntity<Void> createProduct(
            @RequestBody ProductRequestVo productRequestVo) {
        log.info("productRequestVo: {}", productRequestVo);
        ProductRequestDto productRequestDto = ProductRequestDto.builder()
                .productCode(productRequestVo.getProductCode())
                .productName(productRequestVo.getProductName())
                .description(productRequestVo.getDescription())
                .releaseDate(productRequestVo.getReleaseDate())
                .content(productRequestVo.getContent())
                .categoryId(productRequestVo.getCategoryId())
                .build();
        productService.addProduct(productRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 등록 성공",
                null
        );
    }

    @PutMapping
    public CommonResponseEntity<Void> updateProduct(
            @RequestBody ProductRequestVo productRequestVo) {
        log.info("productRequestVo: {}", productRequestVo);
        ProductRequestDto productRequestDto = ProductRequestDto.builder()
                .productCode(productRequestVo.getProductCode())
                .productName(productRequestVo.getProductName())
                .description(productRequestVo.getDescription())
                .releaseDate(productRequestVo.getReleaseDate())
                .content(productRequestVo.getContent())
                .categoryId(productRequestVo.getCategoryId())
                .build();
        productService.updateProduct(productRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 업데이트 성공",
                null
        );
    }

    @DeleteMapping
    public CommonResponseEntity<Void> deleteProduct(
            @RequestBody String productCode) {
        log.info("productCode: {}", productCode);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 삭제 성공",
                null
        );
    }

    @GetMapping("/{productCode}")
    public CommonResponseEntity<ProductResponseVo> getProduct(
            @PathVariable String productCode) {
        ProductResponseDto productResponseDto = productService.getProduct(productCode);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 조회 성공",
                productResponseDto.toProductResponseVo()
        );
    }

    @GetMapping("/all")
    public CommonResponseEntity<List<ProductResponseVo>> getAllProduct() {

        List<ProductResponseVo> productResponseVoList = productService.getAllProducts();

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 전체 조회 성공",
                productResponseVoList
        );

    }

    



}
