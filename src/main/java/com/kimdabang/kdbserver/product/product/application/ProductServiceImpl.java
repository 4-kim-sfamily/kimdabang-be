package com.kimdabang.kdbserver.product.product.application;


import com.kimdabang.kdbserver.product.product.domain.Product;
import com.kimdabang.kdbserver.product.product.dto.in.ProductRequestDto;
import com.kimdabang.kdbserver.product.product.dto.out.ProductResponseDto;
import com.kimdabang.kdbserver.product.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void addProduct(ProductRequestDto productDto) {
        String productUuid;
        String productCode;

//        uuid 생성
        productUuid = UUID.randomUUID().toString();
//        productCode 생성
        productCode = productUuid.substring(0, 8);

        productRepository.save(productDto.toProductEntity(productCode));
    }

    @Override
    public void updateProduct(ProductRequestDto productDto) {
        productRepository.findByProductCode(productDto.getProductCode())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        productRepository.save(productDto.toProductEntity(productDto.getProductCode()));
    }

    @Override
    public void deleteProduct(String productCode) {
        Product deleteProduct = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));
        productRepository.delete(deleteProduct);
    }

    @Override
    public ProductResponseDto getProduct(String productCode) {
        Product getProduct = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다."));

        return ProductResponseDto.builder()
                .productCode(getProduct.getProductCode())
                .productName(getProduct.getProductName())
                .description(getProduct.getDescription())
                .releaseDate(getProduct.getReleaseDate())
                .content(getProduct.getContent())
                .categoryId(getProduct.getCategoryId())
                .build();
    }

    @Override
    public List<ProductResponseDto> getProducts() {
        List<Product> products = productRepository.findAll();
        if (products != null) {
            return products.stream()
                    .map(product -> ProductResponseDto.builder()
                            .productCode(product.getProductCode())
                            .productName(product.getProductName())
                            .productCode(product.getProductCode())
                            .productName(product.getProductName())
                            .description(product.getDescription())
                            .releaseDate(product.getReleaseDate())
                            .content(product.getContent())
                            .categoryId(product.getCategoryId())
                            .build())
                    .toList();
        }
        return List.of();
    }

}
