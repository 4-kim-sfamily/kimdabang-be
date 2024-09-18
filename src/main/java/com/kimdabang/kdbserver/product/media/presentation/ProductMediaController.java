package com.kimdabang.kdbserver.product.media.presentation;

import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.product.media.application.ProductMediaService;
import com.kimdabang.kdbserver.product.media.dto.in.ProductMediaRequestDto;
import com.kimdabang.kdbserver.product.media.dto.out.ProductMediaResponseDto;
import com.kimdabang.kdbserver.product.media.vo.ProductMediaRequestVo;
import com.kimdabang.kdbserver.product.media.vo.ProductMediaResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product-media")
public class ProductMediaController {

    private final ProductMediaService productMediaService;

    @PostMapping
    public CommonResponseEntity<Void> createProductMedia(
            @RequestBody ProductMediaRequestVo productMediaRequestVo) {
        log.info("productMediaRequestVo: {}", productMediaRequestVo);
        ProductMediaRequestDto productMediaRequestDto = ProductMediaRequestDto.builder()
                .productId(productMediaRequestVo.getProductId())
                .mediaName(productMediaRequestVo.getMediaName())
                .mediaType(productMediaRequestVo.getMediaType())
                .mediaURL(productMediaRequestVo.getMediaURL())
                .build();
        productMediaService.addProductMedia(productMediaRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 미디어 등록 성공",
                null
        );
    }

    @PutMapping
    public CommonResponseEntity<Void> updateProductMedia(
            @RequestBody ProductMediaRequestVo productMediaRequestVo) {
        log.info("productMediaRequestVo: {}", productMediaRequestVo);
        ProductMediaRequestDto productMediaRequestDto = ProductMediaRequestDto.builder()
                .productId(productMediaRequestVo.getProductId())
                .mediaName(productMediaRequestVo.getMediaName())
                .mediaType(productMediaRequestVo.getMediaType())
                .mediaURL(productMediaRequestVo.getMediaURL())
                .build();
        productMediaService.updateProductMedia(productMediaRequestDto);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 미디어 업데이트 성공",
                null
        );
    }

    @DeleteMapping
    public CommonResponseEntity<Void> deleteProductMedia(
            @RequestBody String productMediaId) {
        log.info("productMediaId: {}", productMediaId);
        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 미디어 삭제 성공",
                null
        );
    }

//    @GetMapping("/{productMediaId}")
//    public CommonResponseEntity<ProductMediaResponseVo> getProductMedia(
//            @PathVariable Long productMediaId) {
//        ProductMediaResponseDto productMediaResponseDto = productMediaService.getProductMedia(productMediaId);
//
//        return new CommonResponseEntity<>(
//                HttpStatus.OK,
//                "상품 미디어 조회 성공",
//                productMediaResponseDto.toProductMediaResponseVo()
//        );
//    }

    @GetMapping("{productCode}/all")
    public CommonResponseEntity<List<ProductMediaResponseVo>> getAllProductMedia(
            @PathVariable String productCode) {
        List<ProductMediaResponseDto> productMediaResponseDtoList = productMediaService.getAllProductMedia(productCode);

        List<ProductMediaResponseVo> productMediaResponseVoList
                = productMediaResponseDtoList.stream()
                .map(ProductMediaResponseDto::toProductMediaResponseVo)
                .toList();

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "상품 미디어 리스트 조회 성공",
                productMediaResponseVoList
        );
    }
}
