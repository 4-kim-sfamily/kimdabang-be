package com.kimdabang.kdbserver.product.product.application;


import com.kimdabang.kdbserver.common.exception.CustomException;
import com.kimdabang.kdbserver.product.product.domain.Product;
import com.kimdabang.kdbserver.product.product.dto.in.ProductRequestDto;
import com.kimdabang.kdbserver.product.product.dto.out.ProductResponseDto;
import com.kimdabang.kdbserver.product.product.infrastructure.ProductRepository;
import com.kimdabang.kdbserver.product.product.vo.ProductResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.kimdabang.kdbserver.common.exception.ErrorCode.PRODUCT_NOT_FOUND;

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
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND));

        productRepository.save(productDto.toProductEntity(productDto.getProductCode()));
    }

    @Override
    public void deleteProduct(String productCode) {
        Product deleteProduct = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND));
        productRepository.delete(deleteProduct);
    }

    @Override
    public ProductResponseDto getProduct(String productCode) {
        Product getProduct = productRepository.findByProductCode(productCode)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND));

        return ProductResponseDto.builder()
                .productCode(getProduct.getProductCode())
                .productName(getProduct.getProductName())
                .productPrice(getProduct.getProductPrice())
                .description(getProduct.getDescription())
                .releaseDate(getProduct.getReleaseDate())
                .content(getProduct.getContent())
                .categoryId(getProduct.getCategoryId())
                .build();
    }

    @Override
    public List<ProductResponseVo> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products != null) {
            return products.stream()
                    .map(product -> ProductResponseDto.builder()
                            .productCode(product.getProductCode())
                            .productName(product.getProductName())
                            .productPrice(product.getProductPrice())
                            .description(product.getDescription())
                            .releaseDate(product.getReleaseDate())
                            .content(product.getContent())
                            .categoryId(product.getCategoryId())
                            .build().toProductResponseVo())
                    .toList();
        }
        return List.of();
    }

}
