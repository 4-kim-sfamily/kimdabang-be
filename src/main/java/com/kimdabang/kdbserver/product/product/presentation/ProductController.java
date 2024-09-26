package com.kimdabang.kdbserver.product.product.presentation;

import co.elastic.clients.elasticsearch.nodes.Http;
import com.kimdabang.kdbserver.common.entity.CommonResponseEntity;
import com.kimdabang.kdbserver.product.product.application.ProductSearchService;
import com.kimdabang.kdbserver.product.product.application.ProductService;
import com.kimdabang.kdbserver.product.product.domain.ProductDocument;
import com.kimdabang.kdbserver.product.product.domain.ProductPartialDocument;
import com.kimdabang.kdbserver.product.product.dto.in.ProductRequestDto;
import com.kimdabang.kdbserver.product.product.dto.out.ProductResponseDto;
import com.kimdabang.kdbserver.product.product.vo.ProductRequestVo;
import com.kimdabang.kdbserver.product.product.vo.ProductResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;
    private final ProductSearchService productSearchService;

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

    @GetMapping("/category/{categoryId}")
    public CommonResponseEntity<Map<String, Object>> getAllProductByCategory(
            @PathVariable Long categoryId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<ProductResponseVo> products = productService.getProductsByCategory(categoryId, page, size);
        long totalPages = productService.getTotalPagesByCategory(categoryId, size);

        Map<String, Object> response = new HashMap<>();
        response.put("products", products);
        response.put("currentPage", page);
        response.put("totalPages", totalPages);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "카테고리별 상품 조회 성공",
                response
        );
    }

    @GetMapping("/search")
    public CommonResponseEntity<List<ProductPartialDocument>> searchProducts(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        List<ProductPartialDocument> productSearchList = productSearchService.searchProducts(keyword, pageable);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "제품 검색 성공",
                productSearchList
        );

    }

    @PostMapping("/elasticIndex")
    public CommonResponseEntity<Void> indexProducts() {

        productSearchService.indexProducts();

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                "엘라스틱 서치 인덱싱 성공",
                null
        );
    }


}
